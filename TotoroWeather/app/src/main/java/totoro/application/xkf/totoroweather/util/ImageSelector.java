package totoro.application.xkf.totoroweather.util;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

import totoro.application.xkf.totoroweather.R;

public class ImageSelector {
    public static int selectHeadImage(String dayCode, String nightCode, String sunRise, String sunSet) {
        //先判断是白天还是晚上
        //再判断天气的类型
        int dCode = Integer.parseInt(dayCode);
        int nCode = Integer.parseInt(nightCode);
        int code = nCode;
        boolean isDay = isDay(sunRise, sunSet);
        if (isDay) {
            code = dCode;
        }
        return selectHeadImage(isDay, code);
    }

    private static int selectHeadImage(boolean isDay, int code) {
        int image;
        if (code == 100) {
            //晴
            image = R.mipmap.header_weather_night_sunny;
            if (isDay) {
                image = R.mipmap.header_weather_day_sunny;
            }
        } else if (code >= 101 && code <= 213) {
            //多云
            image = R.mipmap.header_weather_night_cloudy;
            if (isDay) {
                image = R.mipmap.header_weather_day_cloudy;
            }
        } else if (code >= 300 && code <= 313) {
            //雨
            image = R.mipmap.header_weather_night_rain;
            if (isDay) {
                image = R.mipmap.header_weather_day_rain;
            }
        } else if (code >= 400 && code <= 407) {
            //雪
            image = R.mipmap.header_weather_night_snow;
            if (isDay) {
                image = R.mipmap.header_weather_day_snow;
            }
        } else if (code >= 500 && code <= 508) {
            //雾
            image = R.mipmap.header_weather_day_fog;
        } else {
            image = R.mipmap.header_image_weather;
        }
        return image;
    }

    private static boolean isDay(String sunRise, String sunSet) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH");
        String hour = sdf.format(new Date());
        int time = Integer.parseInt(hour);
        String sunRiseTime = sunRise.substring(0, sunRise.length() - 3);
        String sunSetTime = sunSet.substring(0, sunSet.length() - 3);
        int rTime = Integer.parseInt(sunRiseTime);
        int sTime = Integer.parseInt(sunSetTime);
        if (time > sTime || time < rTime) {
            return false;
        }
        return true;
    }

    public static int selectNavitionHeadImage(String sunRise, String sunSet) {
        if (isDay(sunRise, sunSet)) {
            return R.mipmap.header_sunrise;
        } else {
            return R.mipmap.header_sunset;
        }
    }

    public static int selectWeatherIcon(String weatherCode) {
        int code = Integer.parseInt(weatherCode);
        int image = 0;
        if (code == 100) {
            image = R.mipmap.ic_weather_icon_100;
        } else if (code == 101) {
            image = R.mipmap.ic_weather_icon_101;
        } else if (code == 102) {
            image = R.mipmap.ic_weather_icon_102;
        } else if (code == 103) {
            image = R.mipmap.ic_weather_icon_103;
        } else if (code == 104) {
            image = R.mipmap.ic_weather_icon_104;
        } else if (code == 200 || code == 202) {
            image = R.mipmap.ic_weather_icon_200;
        } else if (code == 201) {
            image = R.mipmap.ic_weather_icon_201;
        } else if (code >= 202 && code <= 204) {
            image = R.mipmap.ic_weather_icon_202;
        } else if (code >= 205 && code <= 207) {
            image = R.mipmap.ic_weather_icon_205;
        } else if (code >= 203 && code <= 213) {
            image = R.mipmap.ic_weather_icon_203;
        } else if (code == 300) {
            image = R.mipmap.ic_weather_icon_300;
        } else if (code == 301) {
            image = R.mipmap.ic_weather_icon_301;
        } else if (code == 302) {
            image = R.mipmap.ic_weather_icon_302;
        } else if (code == 303) {
            image = R.mipmap.ic_weather_icon_303;
        } else if (code == 304) {
            image = R.mipmap.ic_weather_icon_304;
        } else if (code == 305) {
            image = R.mipmap.ic_weather_icon_305;
        } else if (code == 306) {
            image = R.mipmap.ic_weather_icon_306;
        } else if (code == 307) {
            image = R.mipmap.ic_weather_icon_307;
        } else if (code == 308) {
            image = R.mipmap.ic_weather_icon_308;
        } else if (code == 309) {
            image = R.mipmap.ic_weather_icon_309;
        } else if (code == 310) {
            image = R.mipmap.ic_weather_icon_310;
        } else if (code == 311) {
            image = R.mipmap.ic_weather_icon_311;
        } else if (code == 312) {
            image = R.mipmap.ic_weather_icon_312;
        } else if (code == 313) {
            image = R.mipmap.ic_weather_icon_313;
        } else if (code == 400) {
            image = R.mipmap.ic_weather_icon_400;
        } else if (code == 401) {
            image = R.mipmap.ic_weather_icon_401;
        } else if (code == 402) {
            image = R.mipmap.ic_weather_icon_402;
        } else if (code == 403) {
            image = R.mipmap.ic_weather_icon_403;
        } else if (code == 404) {
            image = R.mipmap.ic_weather_icon_404;
        } else if (code == 405) {
            image = R.mipmap.ic_weather_icon_405;
        } else if (code == 406) {
            image = R.mipmap.ic_weather_icon_406;
        } else if (code == 407) {
            image = R.mipmap.ic_weather_icon_407;
        } else if (code == 500) {
            image = R.mipmap.ic_weather_icon_500;
        } else if (code == 501) {
            image = R.mipmap.ic_weather_icon_501;
        } else if (code == 502) {
            image = R.mipmap.ic_weather_icon_502;
        } else if (code == 503) {
            image = R.mipmap.ic_weather_icon_503;
        } else if (code == 504) {
            image = R.mipmap.ic_weather_icon_504;
        } else if (code == 507) {
            image = R.mipmap.ic_weather_icon_507;
        } else if (code == 508) {
            image = R.mipmap.ic_weather_icon_508;
        } else if (code == 900) {
            image = R.mipmap.ic_weather_icon_900;
        } else if (code == 901) {
            image = R.mipmap.ic_weather_icon_901;
        } else {
            image = R.mipmap.ic_weather_icon_999;
        }
        return image;
    }

    public static int selectSuggestionIcon(int position) {
        int image = 0;
        switch (position) {
            case 0:
                image = R.mipmap.ic_weather_icon_208;
                break;
            case 1:
                image = R.mipmap.ic_suggestion_comfort;
                break;
            case 2:
                image = R.mipmap.ic_suggestion_car;
                break;
            case 3:
                image = R.mipmap.ic_suggestion_clothe;
                break;
            case 4:
                image = R.mipmap.ic_suggestion_flu;
                break;
            case 5:
                image = R.mipmap.ic_suggestion_sport;
                break;
            case 6:
                image = R.mipmap.ic_suggestion_travel;
                break;
            case 7:
                image = R.mipmap.ic_suggestion_uv;
                break;
        }
        return image;
    }
}
