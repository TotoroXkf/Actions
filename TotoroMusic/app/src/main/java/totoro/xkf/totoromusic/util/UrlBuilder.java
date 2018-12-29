package totoro.xkf.totoromusic.util;

public class UrlBuilder {
    private static final String BASE_URL = "http://tingapi.ting.baidu.com/v1/restserver/ting?";
    private static final String METHOD = "method=";

    public static String getListUrl(int type, int size) {
        return BASE_URL + METHOD + "baidu.ting.billboard.billList" + "&type=" + type + "&size=" + size;
    }

    public static String getSearchUrl(String message) {
        return BASE_URL + METHOD + "baidu.ting.search.catalogSug&query=" + message;
    }

    public static String getOnlinePlayUrl(String songId) {
        return BASE_URL + METHOD + "baidu.ting.song.play&songid=" + songId;
    }

    public static String getLrcUrl(String songId) {
        return BASE_URL + METHOD + "baidu.ting.song.lry&songid=" + songId;
    }
}
