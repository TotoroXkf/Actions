package totoro.xkf.totoromusic.service;

import android.app.DownloadManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import totoro.xkf.totoromusic.activity.MusicActivity;
import totoro.xkf.totoromusic.application.AppCache;
import totoro.xkf.totoromusic.broadcast.DownloadFinishReceiver;
import totoro.xkf.totoromusic.listener.MusicEventListener;
import totoro.xkf.totoromusic.listener.OnLoadOnlineMusicFinishListener;
import totoro.xkf.totoromusic.listener.OnPositionChangeListener;
import totoro.xkf.totoromusic.listener.OnScanFinnishListener;
import totoro.xkf.totoromusic.listener.PlayEventListener;
import totoro.xkf.totoromusic.model.Music;
import totoro.xkf.totoromusic.util.FileUtils;
import totoro.xkf.totoromusic.util.LogUtils;
import totoro.xkf.totoromusic.util.MusicScanner;
import totoro.xkf.totoromusic.util.NotificationUtils;
import totoro.xkf.totoromusic.util.PreferenceUtils;

public class MusicService extends Service {
    private Music currentPlayingMusic;
    private List<Music> mMusicList;
    public static final int PLAY_MODE_LOOP = 0;
    public static final int PLAY_MODE_SINGLE = 1;
    public static final int PLAY_MODE_RANDOM = 2;
    public static final int PLAY_STATE_PLAYING = 0;
    public static final int PLAY_STATE_PAUSE = 1;
    public static final int PLAY_STATE_STOP = 2;
    public static final int UPDATE_TIME = 150;
    private int currentPlayingPosition;
    private int playState = PLAY_STATE_STOP;
    private boolean completion = false;

    private LinkedList<Music> downloadQueue = new LinkedList<>();
    private MediaPlayer mMediaPlayer = new MediaPlayer();

    private MusicEventListener mMusicEventListener;
    private OnPositionChangeListener mOnPositionChangeListener;
    private OnLoadOnlineMusicFinishListener mOnLoadOnlineMusicFinishListener;
    private PlayEventListener mPlayEventListener;

    public MusicService() {

    }

    @Override
    public void onCreate() {
        super.onCreate();
        mMusicList = AppCache.getMusicList();
        mMediaPlayer.setOnCompletionListener(mOnCompletionListener);

    }



    @Override
    public IBinder onBind(Intent intent) {
        return new SingleHolder();
    }


    public class SingleHolder extends Binder {

        public MusicService getMusicService() {
            return MusicService.this;
        }
    }

    public void setPlayEventListener(PlayEventListener playEventListener) {
        this.mPlayEventListener = playEventListener;
    }

    public int getPlayState() {
        return playState;
    }

    public LinkedList<Music> getDownloadQueue() {
        return downloadQueue;
    }

    public void setMusicEventListener(MusicEventListener musicEventListener) {
        mMusicEventListener = musicEventListener;
    }

    public void setOnLoadOnlineMusicFinishListener(OnLoadOnlineMusicFinishListener onLoadOnlineMusicFinishListener) {
        this.mOnLoadOnlineMusicFinishListener = onLoadOnlineMusicFinishListener;
    }

    public void setOnPositionChangeListener(OnPositionChangeListener onPositionChangeListener) {
        mOnPositionChangeListener = onPositionChangeListener;
    }

    public void scanMusic(final OnScanFinnishListener onScanFinnishListener) {
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... params) {
                List<Music> musicList = AppCache.getMusicList();
                MusicScanner.scanMusic(MusicService.this, musicList);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                onScanFinnishListener.onScanFinish();
            }
        }.execute();

    }

    public void setPlayingPosition(int playingPosition) {
        currentPlayingPosition = playingPosition;
    }

    public Music getCurrentPlayingMusic() {
        return currentPlayingMusic;
    }

    public void setCurrentPlayingMusic(Music currentPlayingMusic) {
        this.currentPlayingMusic = currentPlayingMusic;
    }

    public Music findMusicById(long id) {
        if (mMusicList.isEmpty() && mMusicList == null) {
            return null;
        } else {
            for (Music music : mMusicList) {
                if (music.getId() == id) {
                    return music;
                }
            }
        }
        return null;
    }

    public int findPositionById(long id) {
        if (mMusicList.isEmpty() && mMusicList == null) {
            return -1;
        } else {
            int position = -1;
            for (Music music : mMusicList) {
                position++;
                if (music.getId() == id) {
                    return position;
                }
            }
            return position;
        }
    }

    public int getCurrentSchedule() {
        return mMediaPlayer.getCurrentPosition();
    }

    public int getCurrentPlayingRate() {
        int currentTime = mMediaPlayer.getCurrentPosition();
        int allTime = mMediaPlayer.getDuration();
        int rate = ((int) ((((float) currentTime) / ((float) (allTime))) * 100));
        return rate;
    }

    public String getCurrentTime() {
        return getFormatTime(mMediaPlayer.getCurrentPosition());
    }

    public String getTotalTime() {
        return getFormatTime(mMediaPlayer.getDuration());
    }

    public String getFormatTime(long allTime) {
        allTime /= 1000;
        String minutes = "" + allTime / 60;
        String seconds = "" + allTime % 60;
        if (Integer.parseInt(seconds) < 10) {
            seconds = "0" + seconds;
        }
        String time = minutes + ":" + seconds;
        return time;
    }

    public void delete(Music music) {
        int deletePosition = findPositionById(music.getId());
        //由于歌曲可能是自己下载的，必须删除歌曲，略缩图，和歌词
        File musicFile = new File(music.getPath());
        if (musicFile.exists()) {
            musicFile.delete();
        }
        File coverFile = new File(FileUtils.getExternalStorageDirectory() + FileUtils.getDownloadCoverPath()
                + music.getTitle() + ".jpg");
        if (coverFile.exists()) {
            coverFile.delete();
        }
        File lrcFile = new File(FileUtils.getExternalStorageDirectory() + FileUtils.getDownloadLrcPath() + music.getTitle() + ".lrc");
        if (lrcFile.exists()) {
            lrcFile.delete();
        }
        // 刷新媒体库
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + music.getPath()));
        sendBroadcast(intent);
        if (deletePosition < currentPlayingPosition) {
            currentPlayingPosition--;
        }
        mMusicList.remove(deletePosition);
    }

    MediaPlayer.OnCompletionListener mOnCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            //必须这么写，这个接口不知道为啥这么智障
            if (playState != MusicService.PLAY_STATE_STOP
                    && (mMediaPlayer.getDuration() - mediaPlayer.getCurrentPosition()) <= 500
                    && mediaPlayer.getCurrentPosition() != 0) {
                next();
            }
        }
    };

    public void play(final Music music) {
        if (music == null) {
            return;
        }
        if (music.getType() == Music.TYPE_LOCAL) {
            PreferenceUtils.saveLastMusicId(music.getId());
        }
        playState = PLAY_STATE_PLAYING;
        currentPlayingMusic = music;
        mMediaPlayer.reset();
        try {
            mMediaPlayer.setDataSource(music.getPath());
            mMediaPlayer.prepareAsync();
            mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mMediaPlayer.start();
                    if (mMusicEventListener != null) {
                        mMusicEventListener.onMusicPlay(music);
                    }
                    if (mPlayEventListener != null) {
                        mPlayEventListener.onMusicPlay(music);
                    }
                    if (mOnLoadOnlineMusicFinishListener != null) {
                        mOnLoadOnlineMusicFinishListener.onLoadFinish();
                    }
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void playOrPause() {
        switch (playState) {
            case PLAY_STATE_PAUSE:
                mMediaPlayer.start();
                playState = PLAY_STATE_PLAYING;
                if (mMusicEventListener != null) {
                    mMusicEventListener.onMusicPlay(currentPlayingMusic);
                }
                if (mPlayEventListener != null) {
                    mPlayEventListener.onMusicPlay(currentPlayingMusic);
                }
                break;
            case PLAY_STATE_PLAYING:
                playState = PLAY_STATE_PAUSE;
                mMediaPlayer.pause();
                if (mMusicEventListener != null) {
                    mMusicEventListener.onMusicPause();
                }
                if (mPlayEventListener != null) {
                    mPlayEventListener.onMusicPause();
                }
                break;
            case PLAY_STATE_STOP:
                play(currentPlayingMusic);
                break;
        }
    }

    public void next() {
        int mode = PreferenceUtils.getPlayMode();
        switch (mode) {
            case PLAY_MODE_SINGLE:
            case PLAY_MODE_LOOP:
                currentPlayingPosition++;
                if (currentPlayingPosition == mMusicList.size()) {
                    currentPlayingPosition = 0;
                }
                play(mMusicList.get(currentPlayingPosition));
                break;
            case PLAY_MODE_RANDOM:
                currentPlayingPosition = (int) (Math.random() * mMusicList.size());
                if (currentPlayingPosition == mMusicList.size()) {
                    currentPlayingPosition = mMusicList.size() - 1;
                }
                play(mMusicList.get(currentPlayingPosition));
                break;
        }
        if (mOnPositionChangeListener != null) {
            mOnPositionChangeListener.onPositionChange(currentPlayingPosition);
        }
    }

    public void prev() {
        int mode = PreferenceUtils.getPlayMode();
        switch (mode) {
            case PLAY_MODE_SINGLE:
            case PLAY_MODE_LOOP:
                currentPlayingPosition--;
                if (currentPlayingPosition == -1) {
                    currentPlayingPosition = mMusicList.size() - 1;
                }
                play(mMusicList.get(currentPlayingPosition));
                break;
            case PLAY_MODE_RANDOM:
                currentPlayingPosition = (int) (Math.random() * mMusicList.size());
                if (currentPlayingPosition == mMusicList.size()) {
                    currentPlayingPosition = mMusicList.size() - 1;
                }
                play(mMusicList.get(currentPlayingPosition));
                break;
        }
        if (mOnPositionChangeListener != null) {
            mOnPositionChangeListener.onPositionChange(currentPlayingPosition);
        }

    }

    public void seekTo(int rate) {
        float nowRate = ((float) rate) / 100;
        int currentPosition = (int) ((mMediaPlayer.getDuration()) * nowRate);
        mMediaPlayer.seekTo(currentPosition);
        mMediaPlayer.start();
        playState = PLAY_STATE_PLAYING;
        if (mMusicEventListener != null) {
            mMusicEventListener.onMusicPlay(currentPlayingMusic);
        }
        if (mPlayEventListener != null) {
            mPlayEventListener.onMusicPlay(currentPlayingMusic);
        }
    }
}
