package totoro.xkf.totoromusic.activity;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.NotificationCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import totoro.xkf.totoromusic.R;
import totoro.xkf.totoromusic.adapter.FragmentAdapter;
import totoro.xkf.totoromusic.adapter.LocalMusicAdapter;
import totoro.xkf.totoromusic.application.AppCache;
import totoro.xkf.totoromusic.fragment.LocalMusicFragment;
import totoro.xkf.totoromusic.fragment.OnlineMusicFragment;
import totoro.xkf.totoromusic.listener.MusicEventListener;
import totoro.xkf.totoromusic.model.Music;
import totoro.xkf.totoromusic.service.MusicService;
import totoro.xkf.totoromusic.service.NotificationService;
import totoro.xkf.totoromusic.util.CoverLoader;
import totoro.xkf.totoromusic.util.HttpUtils;
import totoro.xkf.totoromusic.util.LogUtils;
import totoro.xkf.totoromusic.util.NotificationUtils;
import totoro.xkf.totoromusic.util.PreferenceUtils;

public class MusicActivity extends AppCompatActivity implements View.OnClickListener,
        MusicEventListener, NavigationView.OnNavigationItemSelectedListener, ViewPager.OnPageChangeListener {
    private ImageView ivMenu;
    private TextView tvLocalMusic;
    private TextView tvOnlineMusic;
    private ImageView ivSearch;
    private ViewPager vpViewPager;
    private LinearLayout llPlayBarMessage;
    private ImageView ivPlayBarMusicIcon;
    private TextView tvPlayBarArtist;
    private TextView tvPlayBarMusicName;
    private ImageButton ibPlayBarStartOrPause;
    private ImageButton ibPlayBarNext;
    private NavigationView nvNavigationView;
    private DrawerLayout dlDrawerLayout;
    private ProgressBar pbPlayBarProgress;

    private MusicService mMusicService;
    private List<Music> mMusicList;
    private Handler mHandler;
    private LocalMusicFragment mLocalMusicFragment;
    private OnlineMusicFragment mOnlineMusicFragment;
    private NotificationService mNotificationService;

    private boolean canExit = false;
    public static final int UPDATE_TIME = 500;
    private static final int EXIT_TIME = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (PreferenceUtils.getIsNightMode()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        mMusicService = AppCache.getMusicService();
        mMusicList = AppCache.getMusicList();
        mMusicService.setMusicEventListener(this);
        initViews();
        setListener();
        bindNotificationService();
    }

    private void bindNotificationService() {
        Intent intent = new Intent(this, NotificationService.class);
        bindService(intent, notificationServiceConnect, BIND_AUTO_CREATE);
    }

    private void setListener() {
        llPlayBarMessage.setOnClickListener(this);
        ibPlayBarStartOrPause.setOnClickListener(this);
        ibPlayBarNext.setOnClickListener(this);
        ivMenu.setOnClickListener(this);
        ivSearch.setOnClickListener(this);
        tvLocalMusic.setOnClickListener(this);
        tvOnlineMusic.setOnClickListener(this);
        vpViewPager.setOnPageChangeListener(this);
        nvNavigationView.setNavigationItemSelectedListener(this);
    }

    private void initViews() {
        mMusicService = AppCache.getMusicService();
        ivMenu = (ImageView) findViewById(R.id.iv_menu);
        tvLocalMusic = (TextView) findViewById(R.id.tv_local_music);
        tvOnlineMusic = (TextView) findViewById(R.id.tv_online_music);
        ivSearch = (ImageView) findViewById(R.id.iv_search);
        vpViewPager = (ViewPager) findViewById(R.id.vp_viewpager);
        llPlayBarMessage = (LinearLayout) findViewById(R.id.ll_play_bar_message);
        ivPlayBarMusicIcon = (ImageView) findViewById(R.id.iv_play_bar_music_icon);
        tvPlayBarArtist = (TextView) findViewById(R.id.tv_play_bar_artist);
        tvPlayBarMusicName = (TextView) findViewById(R.id.tv_play_bar_music_name);
        ibPlayBarStartOrPause = (ImageButton) findViewById(R.id.ib_play_bar_start_or_pause);
        ibPlayBarNext = (ImageButton) findViewById(R.id.ib_play_bar_next);
        nvNavigationView = (NavigationView) findViewById(R.id.nv_NavigationView);
        dlDrawerLayout = (DrawerLayout) findViewById(R.id.dl_drawer_layout);
        pbPlayBarProgress = (ProgressBar) findViewById(R.id.pb_play_bar_progress);
        tvLocalMusic.setSelected(true);
        vpViewPager.setCurrentItem(0);
        long lastId = PreferenceUtils.getLastMusicId();
        Music music = null;
        boolean isPlaying = false;
        if (lastId == -1) {
            if (!mMusicList.isEmpty()) {
                PreferenceUtils.saveLastMusicId(mMusicList.get(0).getId());
                music = mMusicService.findMusicById(mMusicList.get(0).getId());
            }
        } else {
            music = mMusicService.findMusicById(lastId);
        }
        if (mMusicService.getPlayState() == MusicService.PLAY_STATE_PLAYING) {
            isPlaying = true;
        }
        mMusicService.setCurrentPlayingMusic(music);
        updatePlayBar(music, isPlaying);
        FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager());
        mLocalMusicFragment = new LocalMusicFragment();
        //存起来，之后下载用
        AppCache.setData(mLocalMusicFragment);
        mOnlineMusicFragment = new OnlineMusicFragment();
        adapter.addFragment(mLocalMusicFragment);
        adapter.addFragment(mOnlineMusicFragment);
        vpViewPager.setAdapter(adapter);
    }

    ServiceConnection notificationServiceConnect = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mNotificationService = ((NotificationService.SingleHolder) service).getNotificationService();
            AppCache.setNotificationService(mNotificationService);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(notificationServiceConnect);
    }

    public void updatePlayBar(Music music, boolean isPlaying) {
        if (music != null) {
            tvPlayBarArtist.setText(music.getArtist());
            tvPlayBarMusicName.setText(music.getTitle());
            if (music.getType() == Music.TYPE_LOCAL) {
                ivPlayBarMusicIcon.setImageBitmap(CoverLoader.createLocalCoverBitmap(music.getPath(), music.getTitle()));
            } else {
                ivPlayBarMusicIcon.setImageResource(R.mipmap.default_cover);
                //这里必须使用application的context
                //原因在于，如果使用当前的activity的话，那么一旦activity退到后台，就可能不更新图
                Glide.with(this.getApplicationContext()).load(music.getCoverPath()).into(ivPlayBarMusicIcon);
            }
        }
        if (isPlaying) {
            ibPlayBarStartOrPause.setImageResource(R.mipmap.ic_play_bar_btn_pause);
        } else {
            ibPlayBarStartOrPause.setImageResource(R.mipmap.ic_play_bar_btn_play);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_search:
                startActivity(new Intent(this, SearchActivity.class));
                break;
            case R.id.iv_menu:
                dlDrawerLayout.openDrawer(Gravity.START);
                break;
            case R.id.ib_play_bar_start_or_pause:
                mMusicService.playOrPause();
                break;
            case R.id.ib_play_bar_next:
                mMusicService.next();
                break;
            case R.id.tv_local_music:
                tvLocalMusic.setSelected(true);
                tvOnlineMusic.setSelected(false);
                vpViewPager.setCurrentItem(0);
                break;
            case R.id.tv_online_music:
                tvLocalMusic.setSelected(false);
                tvOnlineMusic.setSelected(true);
                vpViewPager.setCurrentItem(1);
                break;
            case R.id.ll_play_bar_message:
                startActivity(new Intent(this, PlayActivity.class));
                break;
        }
    }

    @Override
    public void onMusicPlay(Music music) {
        if (mHandler == null) {
            mHandler = new Handler();
            mHandler.post(onPublish);
        }
        updatePlayBar(music, true);
        mNotificationService.showNotification(music, true);
    }

    @Override
    public void onMusicPause() {
        mHandler.removeCallbacks(onPublish);
        mHandler = null;
        updatePlayBar(null, false);
        mNotificationService.showNotification(mMusicService.getCurrentPlayingMusic(), false);
    }

    private Runnable onPublish = new Runnable() {
        @Override
        public void run() {
            int rate = mMusicService.getCurrentPlayingRate();
            pbPlayBarProgress.setProgress(rate);
            mHandler.postDelayed(onPublish, UPDATE_TIME);
        }
    };

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        dlDrawerLayout.closeDrawers();
        switch (item.getItemId()) {
            case R.id.menu_exit:
                new AlertDialog.Builder(this).setTitle(getString(R.string.exit)).setMessage(getString(R.string.areYouSure))
                        .setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                System.exit(0);
                            }
                        }).setNegativeButton(getString(R.string.no), null)
                        .show();
                break;
            case R.id.menu_night_mode:
                boolean isNightMode = PreferenceUtils.getIsNightMode();
                if (isNightMode) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                }
                PreferenceUtils.saveIsNightMode(!isNightMode);
                recreate();
                break;
            case R.id.menu_setting:
                Intent intent = new Intent(this, SettingActivity.class);
                startActivity(intent);
                break;
            case R.id.menu_share:
                shareApp();
                break;
        }
        return true;
    }

    private void shareApp() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, "超轻量级的音乐APP-----龙猫音乐"+"http://fir.im/TotoroMusic");
        startActivity(Intent.createChooser(intent, getString(R.string.shareTo)));
    }

    @Override
    public void onBackPressed() {
        if (dlDrawerLayout.isDrawerOpen(Gravity.START)) {
            dlDrawerLayout.closeDrawers();
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (position == 0) {
            tvLocalMusic.setSelected(true);
            tvOnlineMusic.setSelected(false);
        } else {
            tvLocalMusic.setSelected(false);
            tvOnlineMusic.setSelected(true);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            moveTaskToBack(true);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
