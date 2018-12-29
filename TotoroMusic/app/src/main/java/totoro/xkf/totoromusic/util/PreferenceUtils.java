package totoro.xkf.totoromusic.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import totoro.xkf.totoromusic.R;
import totoro.xkf.totoromusic.service.MusicService;

public class PreferenceUtils {
    private static Context sContext;

    private static final String LAST_MUSIC_ID = "LastMusicId";
    private static final String PLAY_MODE = "PlayMode";
    private static final String IS_NIGHT_MODE = "IsNightMode";
    private static final String CAN_MOBILE_NETWORK_PLAY = "CanMobileNetworkPlay";
    private static final String CAN_MOBILE_NETWORK_DOWNLOAD = "CanMobileNetworkDownload";

    public static void init(Context context) {
        sContext = context.getApplicationContext();
    }

    private static SharedPreferences getInstance() {
        return PreferenceManager.getDefaultSharedPreferences(sContext);
    }

    public static Long getLastMusicId() {
        return getInstance().getLong(LAST_MUSIC_ID, -1);
    }

    public static void saveLastMusicId(long lastId) {
        getInstance().edit().putLong(LAST_MUSIC_ID, lastId).apply();
    }

    public static void savePlayMode(int playMode) {
        getInstance().edit().putInt(PLAY_MODE, playMode).apply();
    }

    public static int getPlayMode() {
        return getInstance().getInt(PLAY_MODE, MusicService.PLAY_MODE_LOOP);
    }

    public static void saveIsNightMode(boolean isNightMode) {
        getInstance().edit().putBoolean(IS_NIGHT_MODE, isNightMode).apply();
    }

    public static boolean getIsNightMode() {
        return getInstance().getBoolean(IS_NIGHT_MODE, false);
    }

    public static boolean getCanMobileNetworkPlay() {
        return getInstance().getBoolean(CAN_MOBILE_NETWORK_PLAY, false);
    }

    public static boolean getCanMobileNetworkDownload() {
        return getInstance().getBoolean(CAN_MOBILE_NETWORK_DOWNLOAD, false);
    }

    public static void saveCanMobileNetworkDownload(boolean can) {
        getInstance().edit().putBoolean(CAN_MOBILE_NETWORK_DOWNLOAD, can).apply();
    }

    public static void saveCanMobileNetworkPlay(boolean can) {
        getInstance().edit().putBoolean(CAN_MOBILE_NETWORK_PLAY, can).apply();
    }
}
