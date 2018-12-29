package totoro.xkf.totoromusic.activity;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import totoro.xkf.totoromusic.R;
import totoro.xkf.totoromusic.application.AppCache;
import totoro.xkf.totoromusic.listener.OnScanFinnishListener;
import totoro.xkf.totoromusic.service.MusicService;
import totoro.xkf.totoromusic.util.ImageUtils;


public class SplashActivity extends AppCompatActivity {
    private static final int REQUEST_CODE = 1;
    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            final MusicService musicService = ((MusicService.SingleHolder) service).getMusicService();
            AppCache.setMusicService(musicService);
            musicService.scanMusic(new OnScanFinnishListener() {
                @Override
                public void onScanFinish() {
                    startActivity(new Intent(SplashActivity.this, MusicActivity.class));
                    finish();
                }
            });
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                checkPermissions();
            }
        }, 1000);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ImageView ivShowImage = (ImageView) findViewById(R.id.iv_show_image);
        Glide.with(this).load(R.mipmap.splash).into(ivShowImage);
    }

    private void checkPermissions() {
        List<String> permissions = new ArrayList<>();
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            permissions.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            permissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !permissions.isEmpty()) {
            requestPermissions(permissions.toArray(new String[permissions.size()]), REQUEST_CODE);
        } else {
            Intent intent = new Intent(this, MusicService.class);
            bindService(intent, mConnection, BIND_AUTO_CREATE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE:
                for (int result : grantResults) {
                    if (result != PackageManager.PERMISSION_GRANTED) {
                        System.exit(0);
                    }
                }
                Intent intent = new Intent(this, MusicService.class);
                bindService(intent, mConnection, BIND_AUTO_CREATE);
                break;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(mConnection);
    }
}
