package totoro.xkf.totoromusic.application;

import java.util.ArrayList;
import java.util.List;

import totoro.xkf.totoromusic.model.Music;
import totoro.xkf.totoromusic.service.MusicService;
import totoro.xkf.totoromusic.service.NotificationService;

public class AppCache {
    private static MusicService sMusicService;
    private static List<Music> sMusicList = new ArrayList<>();
    private static Object sObject;
    private static NotificationService sNotificationService;

    public static MusicService getMusicService() {
        return sMusicService;
    }

    public static void setMusicService(MusicService sMusicService) {
        AppCache.sMusicService = sMusicService;
    }

    public static List<Music> getMusicList() {
        return sMusicList;
    }

    public static void setData(Object object) {
        sObject = object;
    }

    public static Object getData() {
        return sObject;
    }

    public static NotificationService getNotificationService() {
        return sNotificationService;
    }

    public static void setNotificationService(NotificationService notificationService) {
        AppCache.sNotificationService = notificationService;
    }
}
