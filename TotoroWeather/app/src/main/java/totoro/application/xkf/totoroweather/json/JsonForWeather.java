package totoro.application.xkf.totoroweather.json;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import totoro.application.xkf.totoroweather.model.DailyForecast;
import totoro.application.xkf.totoroweather.model.HourlyForecast;
import totoro.application.xkf.totoroweather.model.NowWeather;
import totoro.application.xkf.totoroweather.model.Suggestion;
import totoro.application.xkf.totoroweather.model.Weather;
import totoro.application.xkf.totoroweather.util.LogUtil;

public class JsonForWeather {
    //Json数据的解析
    private List<Info> HeWeather5;

    private class Info {
        private Basic basic;
        private Now now;
        private List<DailyForecast> daily_forecast;
        private List<HourlyForecast> hourly_forecast;
        private Suggestion suggestion;

        private class Basic {
            public String city;
            public String id;
        }

        private class DailyForecast {
            private Astro astro;

            private class Astro {
                private String sr;
                private String ss;
            }

            private Cond cond;

            private class Cond {
                private String code_d;
                private String code_n;
                private String txt_d;
                private String txt_n;
            }

            private Tmp tmp;

            private class Tmp {
                private String max;
                private String min;
            }

            private Wind wind;

            private class Wind {
                private String dir;
                private String sc;
            }

        }

        private class HourlyForecast {
            private Cond cond;

            private class Cond {
                private String code;
                private String txt;
            }

            private String date;
            private String tmp;
            private Wind wind;

            private class Wind {
                private String dir;
                private String sc;
            }
        }

        private class Now {
            public Cond cond;

            public class Cond {
                public String code;
                public String txt;
            }

            public String fl;
            public String tmp;
            public Wind wind;

            public class Wind {
                public String dir;
                public String sc;
            }
        }

        private class Suggestion {
            private Air air;

            private class Air {
                private String brf;
                private String txt;
            }

            private Comf comf;

            private class Comf {
                private String brf;
                private String txt;
            }

            private Cw cw;

            private class Cw {
                private String brf;
                private String txt;
            }

            private Drsg drsg;

            private class Drsg {
                private String brf;
                private String txt;
            }

            private Flu flu;

            private class Flu {
                private String brf;
                private String txt;
            }

            private Sport sport;

            private class Sport {
                private String brf;
                private String txt;
            }

            private Trav trav;

            private class Trav {
                private String brf;
                private String txt;
            }

            private Uv uv;

            private class Uv {
                private String brf;
                private String txt;
            }
        }
    }

    public Weather changeToWeather() {
        Weather weather = new Weather();
        Info info = HeWeather5.get(0);
        NowWeather nowWeather = getNowWeather(info);
        weather.setNowWeather(nowWeather);
        DailyForecast dailyForecast = getDailyForecast(info);
        weather.setDailyForecast(dailyForecast);
        HourlyForecast hourlyForecast = getHourlyForecast(info);
        weather.setHourlyForecast(hourlyForecast);
        Suggestion suggestion = getSuggestion(info);
        weather.setSuggestion(suggestion);
        return weather;
    }

    private HourlyForecast getHourlyForecast(Info info) {
        HourlyForecast hourlyForecast = new HourlyForecast();
        List<HourlyForecast.Item> list = new ArrayList<>();
        for (Info.HourlyForecast hourly : info.hourly_forecast) {
            HourlyForecast.Item item = hourlyForecast.new Item();
            item.setCode(hourly.cond.code);
            item.setTxt(hourly.cond.txt);
            String time = hourly.date.substring(hourly.date.length() - 5);
            item.setTime(time);
            item.setWindDegree(hourly.wind.sc);
            item.setWindType(hourly.wind.dir);
            item.setTemperature(hourly.tmp);
            list.add(item);
        }
        hourlyForecast.setList(list);
        return hourlyForecast;
    }

    private NowWeather getNowWeather(Info info) {
        NowWeather nowWeather = new NowWeather();
        nowWeather.setName(info.basic.city);
        nowWeather.setId(info.basic.id);
        nowWeather.setCode(info.now.cond.code);
        nowWeather.setDescription(info.now.cond.txt);
        nowWeather.setFeel(info.now.fl);
        nowWeather.setTemperature(info.now.tmp);
        nowWeather.setWindDegree(info.now.wind.sc);
        nowWeather.setWindType(info.now.wind.dir);
        return nowWeather;
    }

    private DailyForecast getDailyForecast(Info info) {
        DailyForecast dailyForecast = new DailyForecast();
        List<DailyForecast.Item> list = new ArrayList<>();
        for (Info.DailyForecast daily : info.daily_forecast) {
            DailyForecast.Item item = dailyForecast.new Item();
            item.setCodeDay(daily.cond.code_d);
            item.setCodeNight(daily.cond.code_n);
            item.setMaxTemperature(daily.tmp.max);
            item.setMinTemperature(daily.tmp.min);
            item.setSunRise(daily.astro.sr);
            item.setSunSet(daily.astro.ss);
            item.setTxtNight(daily.cond.txt_n);
            item.setTxtDay(daily.cond.txt_d);
            item.setWindDegree(daily.wind.sc);
            item.setWindType(daily.wind.dir);
            list.add(item);
        }
        dailyForecast.setDailyForecast(list);
        return dailyForecast;
    }

    public Suggestion getSuggestion(Info info) {
        Suggestion suggestion = new Suggestion();
        for (int i = 0; i < suggestion.getTypes().length; i++) {
            Map<String, String> map = suggestion.getMap();
            switch (i) {
                case 0:
                    map.put(suggestion.getTypes()[i], info.suggestion.air.brf + "&" + info.suggestion.air.txt);
                    break;
                case 1:
                    map.put(suggestion.getTypes()[i], info.suggestion.comf.brf + "&" + info.suggestion.comf.txt);
                    break;
                case 2:
                    map.put(suggestion.getTypes()[i], info.suggestion.cw.brf + "&" + info.suggestion.cw.txt);
                    break;
                case 3:
                    map.put(suggestion.getTypes()[i], info.suggestion.drsg.brf + "&" + info.suggestion.drsg.txt);
                    break;
                case 4:
                    map.put(suggestion.getTypes()[i], info.suggestion.flu.brf + "&" + info.suggestion.flu.txt);
                    break;
                case 5:
                    map.put(suggestion.getTypes()[i], info.suggestion.sport.brf + "&" + info.suggestion.sport.txt);
                    break;
                case 6:
                    map.put(suggestion.getTypes()[i], info.suggestion.trav.brf + "&" + info.suggestion.trav.txt);
                    break;
                case 7:
                    map.put(suggestion.getTypes()[i], info.suggestion.uv.brf + "&" + info.suggestion.uv.txt);
                    break;
            }
        }
        return suggestion;
    }
}
