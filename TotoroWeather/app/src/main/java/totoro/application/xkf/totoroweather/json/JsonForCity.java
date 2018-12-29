package totoro.application.xkf.totoroweather.json;

import java.util.ArrayList;
import java.util.List;

import totoro.application.xkf.totoroweather.model.City;

public class JsonForCity {
    private List<Info> HeWeather5;

    private class Info {
        private Basic basic;

        private class Basic {
            private String city;
            private String prov;
            private String id;
        }

        private String status;
    }

    public City changeToFirstCity() {
        City city = new City();
        Info info = HeWeather5.get(0);
        city.setId(info.basic.id);
        city.setProvince(info.basic.prov);
        city.setCity(info.basic.city);
        return city;
    }

    public List<City> changeToCityList() {
        if (HeWeather5.get(0).status.equals("unknown city")) {
            return null;
        }
        List<City> cityList = new ArrayList<>();
        for (Info info : HeWeather5) {
            City city = new City();
            city.setId(info.basic.id);
            city.setProvince(info.basic.prov);
            city.setCity(info.basic.city);
            cityList.add(city);
        }
        return cityList;
    }
}
