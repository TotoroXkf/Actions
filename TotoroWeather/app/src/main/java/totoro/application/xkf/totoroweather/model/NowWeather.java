package totoro.application.xkf.totoroweather.model;

public class NowWeather {
    //地区名
    private String name;
    //地区id
    private String id;
    //地区天气的代码
    private String code;
    //地区天气描述
    private String description;
    //体感温度
    private String feel;
    //实际温度
    private String temperature;
    //风的种类
    private String windType;
    //风的大小
    private String windDegree;

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public String getFeel() {
        return feel;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getWindType() {
        return windType;
    }

    public String getWindDegree() {
        return windDegree;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setFeel(String feel) {
        this.feel = feel;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public void setWindType(String windType) {
        this.windType = windType;
    }

    public void setWindDegree(String windDegree) {
        this.windDegree = windDegree;
    }
}
