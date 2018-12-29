package totoro.application.xkf.totoroweather.model;

import java.util.Map;
import java.util.TreeMap;

public class Suggestion {
    private String[] types = new String[]{
            "气象", "舒适", "洗车", "穿衣", "感冒", "运动", "旅游", "射线"
    };
    private Map<String, String> map = new TreeMap<>();

    public Suggestion() {
        for (String key : types) {
            map.put(key, "");
        }
    }

    public String[] getTypes() {
        return types;
    }

    public Map<String, String> getMap() {
        return map;
    }
}
