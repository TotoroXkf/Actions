package totoro.application.xkf.totoroweather.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetUtil {
    public static boolean isNetConnectivity(Context context) {
        ConnectivityManager mConnectivity = (ConnectivityManager) context.getSystemService
                (Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = mConnectivity.getActiveNetworkInfo();
        if (info == null || !mConnectivity.getBackgroundDataSetting()) {
            return false;
        }
        return true;
    }
}
