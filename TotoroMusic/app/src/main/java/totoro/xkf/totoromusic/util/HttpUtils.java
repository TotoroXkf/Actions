package totoro.xkf.totoromusic.util;

import android.os.Build;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class HttpUtils {
    public static void sendRequest(String url, Callback callback) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).header("User-Agent",
                Build.BRAND + "/" + Build.MODEL + "/" + Build.VERSION.RELEASE).build();
        client.newCall(request).enqueue(callback);
    }
}
