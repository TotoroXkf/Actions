package totoro.application.xkf.totoroweather.model;

import java.util.List;

public class HourlyForecast {
    private List<Item> list;

    public class Item {
        private String time;
        private String code;
        private String txt;
        private String temperature;
        private String windType;
        private String windDegree;

        public String getTemperature() {
            return temperature;
        }

        public void setTemperature(String temperature) {
            this.temperature = temperature;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getTxt() {
            return txt;
        }

        public void setTxt(String txt) {
            this.txt = txt;
        }

        public String getWindType() {
            return windType;
        }

        public void setWindType(String windType) {
            this.windType = windType;
        }

        public String getWindDegree() {
            return windDegree;
        }

        public void setWindDegree(String windDegree) {
            this.windDegree = windDegree;
        }
    }

    public List<Item> getList() {
        return list;
    }

    public void setList(List<Item> list) {
        this.list = list;
    }


}
