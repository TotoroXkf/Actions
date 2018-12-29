package totoro.application.xkf.totoroweather.listener;


import java.util.List;

import totoro.application.xkf.totoroweather.model.City;

public interface OnSearchFinishListener {
    void onSearchSuccess(List<City> cityList);

    void onSearchFail();
}
