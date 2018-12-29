package totoro.xkf.totoromusic.util;

import android.util.Log;

public class LogUtils {
    public static final String TAG = "Totoro";

    public static void show() {
        Log.e(TAG, "this");
    }

    public static void show(String message) {
        Log.e(TAG, message);
    }
}
