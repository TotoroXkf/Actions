package totoro.application.xkf.totoroweather.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.viksaa.sssplash.lib.activity.AwesomeSplash;
import com.viksaa.sssplash.lib.cnst.Flags;
import com.viksaa.sssplash.lib.model.ConfigSplash;

import totoro.application.xkf.totoroweather.R;
import totoro.application.xkf.totoroweather.application.AppCache;
import totoro.application.xkf.totoroweather.service.DataService;
import totoro.application.xkf.totoroweather.util.PreferenceUtil;

public class SplashActivity extends AwesomeSplash {

    @Override
    public void initSplash(ConfigSplash configSplash) {
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        PreferenceUtil.setContext(this.getApplicationContext());
        //一个开源库，用于加载一进来的界面
        if (PreferenceUtil.getIsNightMode()) {
            configSplash.setBackgroundColor(R.color.grey);
        } else {
            configSplash.setBackgroundColor(R.color.colorPrimary);
        }
        configSplash.setAnimCircularRevealDuration(1000);
        configSplash.setRevealFlagX(Flags.REVEAL_RIGHT);
        configSplash.setRevealFlagY(Flags.REVEAL_BOTTOM);

        configSplash.setLogoSplash(R.mipmap.ic_launcher);
        configSplash.setAnimLogoSplashDuration(1000);
        configSplash.setAnimLogoSplashTechnique(Techniques.Bounce);

        configSplash.setTitleSplash("龙猫天气");
        configSplash.setTitleTextColor(R.color.white);
        configSplash.setTitleTextSize(30f);
        configSplash.setAnimTitleDuration(1000);
        configSplash.setAnimTitleTechnique(Techniques.FlipInX);

        //保存dataservice,它作为全局的一个数据提供
        Intent intent = new Intent(this, DataService.class);
        bindService(intent, connection, BIND_AUTO_CREATE);
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            DataService dateService = ((DataService.SingleHolder) iBinder).getDataService();
            AppCache.setDataService(dateService);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    @Override
    public void animationsFinished() {
        unbindService(connection);
        Intent intent = new Intent(this, WeatherActivity.class);
        startActivity(intent);
        finish();
    }


}
