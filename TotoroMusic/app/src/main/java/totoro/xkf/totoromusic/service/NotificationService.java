package totoro.xkf.totoromusic.service;

import android.app.DownloadManager;
import android.app.Notification;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import totoro.xkf.totoromusic.application.AppCache;
import totoro.xkf.totoromusic.broadcast.DownloadFinishReceiver;
import totoro.xkf.totoromusic.model.Music;
import totoro.xkf.totoromusic.util.HttpUtils;
import totoro.xkf.totoromusic.util.NotificationUtils;

public class NotificationService extends Service {
    NotificationUtils.NotificationControl mNotificationControl = new NotificationUtils().new NotificationControl();
    private DownloadFinishReceiver mDownloadFinishReceiver;

    public NotificationService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new SingleHolder();
    }

    public class SingleHolder extends Binder {
        public NotificationService getNotificationService() {
            return NotificationService.this;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        TelephonyManager tmgr = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        tmgr.listen(mPhoneStateListener, PhoneStateListener.LISTEN_CALL_STATE);
        mDownloadFinishReceiver = new DownloadFinishReceiver();
        IntentFilter intentFilter = new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE);
        registerReceiver(mDownloadFinishReceiver, intentFilter);
        registerReceiver(mNotificationControl, new IntentFilter(NotificationUtils.BUTTON_ACTION));
        if (AppCache.getMusicService().getCurrentPlayingMusic() != null) {
            startForeground(NotificationUtils.MUSIC_NOTIFICATION_ID,
                    NotificationUtils.createMusicNotification(this, AppCache.getMusicService().getCurrentPlayingMusic(), false, null));
        }
    }

    private PhoneStateListener mPhoneStateListener = new PhoneStateListener() {
        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
            if (state == TelephonyManager.CALL_STATE_RINGING) {
                AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
                int ringvolume = audioManager
                        .getStreamVolume(AudioManager.STREAM_RING);
                if (ringvolume > 0) {
                    if (AppCache.getMusicService().getPlayState() == MusicService.PLAY_STATE_PLAYING) {
                        AppCache.getMusicService().playOrPause();
                    }
                }
            }
            super.onCallStateChanged(state, incomingNumber);
        }
    };

    public void showNotification(final Music music, final boolean isPlaying) {
        if (music.getType() == Music.TYPE_ONLINE) {
            Glide.with(this).asBitmap().load(music.getCoverPath()).into(new SimpleTarget<Bitmap>() {
                @Override
                public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                    startForeground(NotificationUtils.MUSIC_NOTIFICATION_ID,
                            NotificationUtils.createMusicNotification(NotificationService.this, music, isPlaying, resource));
                }
            });
        } else {
            startForeground(NotificationUtils.MUSIC_NOTIFICATION_ID,
                    NotificationUtils.createMusicNotification(this, music, isPlaying, null));
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mDownloadFinishReceiver);
        unregisterReceiver(mNotificationControl);
        TelephonyManager tmgr = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        tmgr.listen(mPhoneStateListener, 0);
    }
}
