package totoro.application.xkf.totoroweather.listener;

import totoro.application.xkf.totoroweather.model.Weather;

public interface OnLoadFinishListener {
    void loadSuccess(Weather weather);

    void loadFail();
}
