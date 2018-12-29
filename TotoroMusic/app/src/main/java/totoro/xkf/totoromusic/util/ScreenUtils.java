package totoro.xkf.totoromusic.util;

import android.content.Context;
import android.view.WindowManager;

public class ScreenUtils {
    private static Context sContext;

    public static void init(Context context) {
        sContext = context.getApplicationContext();
    }

    public static int getScreenWidth() {
        WindowManager wm = (WindowManager) sContext.getSystemService(Context.WINDOW_SERVICE);
        return wm.getDefaultDisplay().getWidth();
    }
}
