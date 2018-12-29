package totoro.application.xkf.totoroweather.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import totoro.application.xkf.totoroweather.activity.WeatherActivity;
import totoro.application.xkf.totoroweather.json.JsonForCity;
import totoro.application.xkf.totoroweather.json.JsonForWeather;
import totoro.application.xkf.totoroweather.listener.OnCityChangeListener;
import totoro.application.xkf.totoroweather.listener.OnLoadFinishListener;
import totoro.application.xkf.totoroweather.listener.OnSearchFinishListener;
import totoro.application.xkf.totoroweather.model.City;
import totoro.application.xkf.totoroweather.model.Weather;
import totoro.application.xkf.totoroweather.util.HttpUtil;
import totoro.application.xkf.totoroweather.util.PreferenceUtil;
import totoro.application.xkf.totoroweather.util.UrlBuilder;

public class DataService extends Service {
    private Handler mainThread = new Handler(Looper.getMainLooper());
    private String mCurrentCityId = null;
    private Weather mCurrentWeather;
    private OnCityChangeListener mCityChangeListener;

    public DataService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new SingleHolder();
    }


    public class SingleHolder extends Binder {
        public DataService getDataService() {
            return DataService.this;
        }
    }

    public void loadWeatherInfo(String city, final OnLoadFinishListener loadFinishListener) {
        String url = UrlBuilder.getWeatherInfoUrl(city);
        HttpUtil.sendRequest(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                mainThread.post(new Runnable() {
                    @Override
                    public void run() {
                        loadFinishListener.loadFail();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //加载成功就处理数据
                String json = response.body().string();
                Gson gson = new Gson();
                JsonForWeather jsonWeather = gson.fromJson(json, JsonForWeather.class);
                final Weather weather = jsonWeather.changeToWeather();
                mCurrentWeather = weather;
                mainThread.post(new Runnable() {
                    @Override
                    public void run() {
                        loadFinishListener.loadSuccess(weather);
                    }
                });
                //加载谁，谁就是当前要显示的城市数据
                mCurrentCityId = weather.getNowWeather().getId();
                //存储当前城市到城市列表，为了之后管理城市使用
                PreferenceUtil.addCity(weather.getNowWeather().getName(), weather.getNowWeather().getId());
            }
        });
    }

    public void searchCity(String city, final boolean isLocal, final OnSearchFinishListener listener) {
        String url = UrlBuilder.getSearchUrl(city);
        HttpUtil.sendRequest(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (listener != null) {
                    listener.onSearchFail();
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                Gson gson = new Gson();
                JsonForCity jsonForCity = gson.fromJson(json, JsonForCity.class);
                if (isLocal) {
                    City city = jsonForCity.changeToFirstCity();
                    PreferenceUtil.saveLocalCityId(city.getId());
                } else {
                    final List<City> cityList = jsonForCity.changeToCityList();
                    mainThread.post(new Runnable() {
                        @Override
                        public void run() {
                            listener.onSearchSuccess(cityList);
                        }
                    });
                }
            }
        });
    }

    public void setCityChangeListener(OnCityChangeListener listener) {
        mCityChangeListener = listener;
    }

    public void onCityChange(City city) {
        mCurrentCityId=city.getId();
        if (mCityChangeListener != null) {
            mCityChangeListener.onCityChange(city);
        }
    }

    public String getCurrentCityId() {
        return mCurrentCityId;
    }

    public Weather getCurrentWeather() {
        return mCurrentWeather;
    }
}
