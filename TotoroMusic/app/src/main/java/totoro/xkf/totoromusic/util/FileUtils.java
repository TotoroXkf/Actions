package totoro.xkf.totoromusic.util;

import android.os.Environment;

public class FileUtils {
    public static String getExternalStorageDirectory() {
        return Environment.getExternalStorageDirectory() + "/";
    }

    public static String getDownloadMusicPath() {
        return "TotoroMusic/music/";
    }

    public static String getDownloadCoverPath() {
        return "TotoroMusic/cover/";
    }

    public static String getDownloadLrcPath() {
        return "TotoroMusic/lrc/";
    }
}
