package totoro.application.xkf.totoroweather.util;

import android.util.Log;

public class LogUtil {
    public static void show() {
        Log.e("totoro", "This");
    }

    public static void show(String message) {
        Log.e("totoro", "This" + "   " + message);
    }
}
