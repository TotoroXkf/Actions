package totoro.xkf.totoromusic.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetUtils {
    private static Context sContext;

    public static void init(Context context) {
        sContext = context.getApplicationContext();
    }

    public static boolean isNetConnectivity() {
        ConnectivityManager mConnectivity = (ConnectivityManager) sContext.getSystemService
                (Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = mConnectivity.getActiveNetworkInfo();
        if (info == null || !mConnectivity.getBackgroundDataSetting()) {
            return false;
        }
        return true;
    }

    public static boolean isWifi() {
        ConnectivityManager mConnectivity = (ConnectivityManager) sContext.getSystemService
                (Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = mConnectivity.getActiveNetworkInfo();
        if (info != null && info.getType() == ConnectivityManager.TYPE_WIFI) {
            return true;
        }
        return false;
    }
}
