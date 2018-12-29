package totoro.xkf.totoromusic.activity;

import android.graphics.Bitmap;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import totoro.xkf.totoromusic.R;
import totoro.xkf.totoromusic.adapter.FragmentAdapter;
import totoro.xkf.totoromusic.application.AppCache;
import totoro.xkf.totoromusic.fragment.LrcFragment;
import totoro.xkf.totoromusic.fragment.RecordFragment;
import totoro.xkf.totoromusic.listener.PlayEventListener;
import totoro.xkf.totoromusic.model.Music;
import totoro.xkf.totoromusic.service.MusicService;
import totoro.xkf.totoromusic.util.CoverLoader;
import totoro.xkf.totoromusic.util.ImageUtils;
import totoro.xkf.totoromusic.util.LogUtils;
import totoro.xkf.totoromusic.util.PreferenceUtils;

public class PlayActivity extends AppCompatActivity implements PlayEventListener
        , View.OnClickListener, SeekBar.OnSeekBarChangeListener {
    private ImageView ivPlayPageBackground;
    private ImageView ivPlayPageBack;
    private TextView tvPlayPageArtistAndTitle;
    private TextView tvPlayPageTimeCurrent;
    private TextView tvPlayPageTimeTotal;
    private SeekBar sbPlayPageProgress;
    private ImageView ivPlayPageMode;
    private ImageView ivPlayPagePrev;
    private ImageView ivPlayPagePlayOrPause;
    private ImageView ivPlayPageNext;
    private ViewPager vpPlayPageViewPager;
    private RecordFragment mRecordFragment = new RecordFragment();
    private LrcFragment mLrcFragment = new LrcFragment();

    public static final int UPDATE_TIME = 500;
    private MusicService mMusicService;
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        mMusicService = AppCache.getMusicService();
        mMusicService.setPlayEventListener(this);
        initViews();
        setListener();
    }

    private void setListener() {
        ivPlayPageMode.setOnClickListener(this);
        ivPlayPageBack.setOnClickListener(this);
        ivPlayPageNext.setOnClickListener(this);
        ivPlayPagePlayOrPause.setOnClickListener(this);
        ivPlayPagePrev.setOnClickListener(this);
        sbPlayPageProgress.setOnSeekBarChangeListener(this);
    }

    private void initViews() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ivPlayPageBackground = (ImageView) findViewById(R.id.iv_play_page_background);
        ivPlayPageBack = (ImageView) findViewById(R.id.iv_play_page_back);
        tvPlayPageArtistAndTitle = (TextView) findViewById(R.id.tv_play_page_artist_and_title);
        tvPlayPageTimeCurrent = (TextView) findViewById(R.id.tv_play_page_time_current);
        tvPlayPageTimeTotal = (TextView) findViewById(R.id.tv_play_page_time_total);
        sbPlayPageProgress = (SeekBar) findViewById(R.id.sb_play_page_progress);
        ivPlayPageMode = (ImageView) findViewById(R.id.iv_play_page_mode);
        ivPlayPagePrev = (ImageView) findViewById(R.id.iv_play_page_prev);
        ivPlayPagePlayOrPause = (ImageView) findViewById(R.id.iv_play_page_play_or_pause);
        ivPlayPageNext = (ImageView) findViewById(R.id.iv_play_page_next);

        vpPlayPageViewPager = (ViewPager) findViewById(R.id.vp_play_page_view_pager);
        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager());
        fragmentAdapter.addFragment(mRecordFragment);
        fragmentAdapter.addFragment(mLrcFragment);
        vpPlayPageViewPager.setAdapter(fragmentAdapter);

        ivPlayPageMode.setImageLevel(selectPlayMode(false));
        Music music = mMusicService.getCurrentPlayingMusic();
        boolean isPlaying = false;
        if (mMusicService.getPlayState() == MusicService.PLAY_STATE_PLAYING) {
            isPlaying = true;
        }
        if (isPlaying) {
            mHandler = new Handler();
            mHandler.post(onPublish);
        }
        updateViews(music, isPlaying);
    }

    private Runnable onPublish = new Runnable() {
        @Override
        public void run() {
            tvPlayPageTimeCurrent.setText(mMusicService.getCurrentTime());
            sbPlayPageProgress.setProgress(mMusicService.getCurrentPlayingRate());
            mHandler.postDelayed(onPublish, UPDATE_TIME);
        }
    };

    @Override
    public void onMusicPlay(Music music) {
        if (mHandler == null) {
            mHandler = new Handler();
            mHandler.post(onPublish);
        }
        updateViews(music, true);
        mLrcFragment.loadLrc(music);
        if (music.getType() == Music.TYPE_LOCAL) {
            mRecordFragment.setCover(CoverLoader.createPlayPageCover(music));
        } else {
            Glide.with(this).asBitmap().load(music.getCoverPath()).into(new SimpleTarget<Bitmap>() {
                @Override
                public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                    mRecordFragment.setCover(resource);
                }
            });
        }
        mLrcFragment.startScroll();
        mRecordFragment.startAnimation();
    }

    private void updateViews(Music music, boolean isPlaying) {
        if (music != null) {
            tvPlayPageArtistAndTitle.setText(music.getTitle() + "\n" + music.getArtist());
            if (mMusicService.getPlayState() == MusicService.PLAY_STATE_STOP) {
                tvPlayPageTimeTotal.setText(music.getTime());
            } else {
                tvPlayPageTimeTotal.setText(mMusicService.getTotalTime());
            }
            if (music.getType() == Music.TYPE_LOCAL) {
                Bitmap bitmap = CoverLoader.createPlayPageBackground(music);
                bitmap = ImageUtils.blur(bitmap);
                ivPlayPageBackground.setImageBitmap(bitmap);
            } else {
                Glide.with(this).asBitmap().load(music.getCoverPath()).into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {

                        resource = ImageUtils.blur(resource);
                        ivPlayPageBackground.setImageBitmap(resource);
                    }
                });
            }
        }
        if (isPlaying) {
            ivPlayPagePlayOrPause.setImageResource(R.mipmap.ic_play_btn_pause);
        } else {
            if (mMusicService.getPlayState() == MusicService.PLAY_STATE_PAUSE) {
                tvPlayPageTimeCurrent.setText(mMusicService.getCurrentTime());
                sbPlayPageProgress.setProgress(mMusicService.getCurrentPlayingRate());
            } else {
                sbPlayPageProgress.setProgress(0);
            }
            ivPlayPagePlayOrPause.setImageResource(R.mipmap.ic_play_btn_play);
        }
    }

    @Override
    public void onMusicPause() {
        mHandler.removeCallbacks(onPublish);
        mHandler = null;
        updateViews(null, false);
        mLrcFragment.pauseScroll();
        mRecordFragment.pauseAnimation();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_play_page_mode:
                int mode = selectPlayMode(true);
                ivPlayPageMode.setImageLevel(mode);
                PreferenceUtils.savePlayMode(mode);
                break;
            case R.id.iv_play_page_back:
                onBackPressed();
                break;
            case R.id.iv_play_page_next:
                mMusicService.next();
                break;
            case R.id.iv_play_page_play_or_pause:
                mMusicService.playOrPause();
                break;
            case R.id.iv_play_page_prev:
                mMusicService.prev();
                break;
        }
    }

    public int selectPlayMode(boolean isNext) {
        switch (PreferenceUtils.getPlayMode()) {
            case MusicService.PLAY_MODE_LOOP:
                if (isNext) {
                    return MusicService.PLAY_MODE_SINGLE;
                } else {
                    return MusicService.PLAY_MODE_LOOP;
                }
            case MusicService.PLAY_MODE_SINGLE:
                if (isNext) {
                    return MusicService.PLAY_MODE_RANDOM;
                } else {
                    return MusicService.PLAY_MODE_SINGLE;
                }
            case MusicService.PLAY_MODE_RANDOM:
                if (isNext) {
                    return MusicService.PLAY_MODE_LOOP;
                } else {
                    return MusicService.PLAY_MODE_RANDOM;
                }
            default:
                return MusicService.PLAY_MODE_LOOP;
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        if (mMusicService.getPlayState() == MusicService.PLAY_STATE_STOP) {
            seekBar.setProgress(0);
        } else {
            mMusicService.seekTo(seekBar.getProgress());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMusicService.setPlayEventListener(null);
    }
}
