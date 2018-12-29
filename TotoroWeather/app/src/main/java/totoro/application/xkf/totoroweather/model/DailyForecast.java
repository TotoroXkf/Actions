package totoro.application.xkf.totoroweather.model;

import java.util.List;

public class DailyForecast {
    //未来几天的预测
    private List<Item> dailyForecast;

    public class Item {
        //日出时间
        private String sunRise;
        //日落时间
        private String sunSet;
        //白天的天气代码
        private String codeDay;
        //夜晚的天气代码
        private String codeNight;
        //白天天气描述
        private String txtDay;
        //晚上天气描述
        private String txtNight;
        //最高温度
        private String maxTemperature;
        //最低温度
        private String minTemperature;
        //风的种类
        private String windType;
        //风力大小
        private String windDegree;

        public String getCodeNight() {
            return codeNight;
        }

        public void setCodeNight(String codeNight) {
            this.codeNight = codeNight;
        }

        public String getSunRise() {
            return sunRise;
        }

        public void setSunRise(String sunRise) {
            this.sunRise = sunRise;
        }

        public String getSunSet() {
            return sunSet;
        }

        public void setSunSet(String sunSet) {
            this.sunSet = sunSet;
        }

        public String getCodeDay() {
            return codeDay;
        }

        public void setCodeDay(String codeDay) {
            this.codeDay = codeDay;
        }

        public String getTxtDay() {
            return txtDay;
        }

        public void setTxtDay(String txtDay) {
            this.txtDay = txtDay;
        }

        public String getTxtNight() {
            return txtNight;
        }

        public void setTxtNight(String textNight) {
            this.txtNight = textNight;
        }

        public String getMaxTemperature() {
            return maxTemperature;
        }

        public void setMaxTemperature(String maxTemperature) {
            this.maxTemperature = maxTemperature;
        }

        public String getMinTemperature() {
            return minTemperature;
        }

        public void setMinTemperature(String minTemperature) {
            this.minTemperature = minTemperature;
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

    public List<Item> getDailyForecast() {
        return dailyForecast;
    }

    public void setDailyForecast(List<Item> dailyForecast) {
        this.dailyForecast = dailyForecast;
    }


}
