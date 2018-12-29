package totoro.xkf.totoromusic.application;

import android.app.Application;
import android.app.Notification;
import android.content.IntentFilter;
import android.preference.Preference;

import totoro.xkf.totoromusic.util.CoverLoader;
import totoro.xkf.totoromusic.util.NetUtils;
import totoro.xkf.totoromusic.util.NotificationUtils;
import totoro.xkf.totoromusic.util.PreferenceUtils;
import totoro.xkf.totoromusic.util.ScreenUtils;

public class MusicApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        PreferenceUtils.init(this);
        ScreenUtils.init(this);
        CoverLoader.init(this);
        NetUtils.init(this);
    }
}
