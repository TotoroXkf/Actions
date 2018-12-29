package totoro.application.xkf.totoroweather.util;

public class UrlBuilder {
    private static final String KEY = "b172b66828f44348818e44e94bf76ce1";

    public static String getSearchUrl(String city) {
        return "https://api.heweather.com/v5/search?city=" + city + "&key=" + KEY;
    }

    public static String getWeatherInfoUrl(String city) {
        return "https://free-api.heweather.com/v5/weather?city=" + city + "&key=" + KEY;
    }

}
