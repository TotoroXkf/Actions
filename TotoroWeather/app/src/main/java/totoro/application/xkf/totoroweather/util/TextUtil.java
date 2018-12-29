package totoro.application.xkf.totoroweather.util;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

public class TextUtil {
    public static String addText(String oldText, String newText) {
        if (TextUtils.isEmpty(oldText)) {
            return newText;
        }
        return oldText + "&" + newText;
    }

    public static List<String> getCitys(String all) {
        if (TextUtils.isEmpty(all)) {
            return null;
        }
        List<String> list = new ArrayList<>();
        String[] names = all.split("&");
        for (String str : names) {
            String[] city = str.split("@");
            list.add(city[0]);
        }
        return list;
    }

    public static List<String> getIds(String all) {
        if (TextUtils.isEmpty(all)) {
            return null;
        }
        List<String> list = new ArrayList<>();
        String[] names = all.split("&");
        for (String str : names) {
            String[] city = str.split("@");
            list.add(city[1]);
        }
        return list;
    }

    public static boolean hasCommonCity(String cityId, List<String> allCityId) {
        if (allCityId == null) {
            return true;
        }
        for (String id : allCityId) {
            if (id.equals(cityId)) {
                return true;
            }
        }
        return false;
    }

    public static String deleteCity(String city, String id, String all) {
        int start = all.indexOf(city);
        int end = start + city.length() + 1 + id.length();
        StringBuilder builder = new StringBuilder(all);
        return builder.delete(start - 1, end).toString();
    }
}