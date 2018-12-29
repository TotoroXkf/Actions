package totoro.application.xkf.totoroweather.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;

import java.util.ArrayList;
import java.util.List;

import totoro.application.xkf.totoroweather.R;
import totoro.application.xkf.totoroweather.adapter.DailyForecastListAdapter;
import totoro.application.xkf.totoroweather.adapter.HourlyForecastListAdapter;
import totoro.application.xkf.totoroweather.adapter.SuggestionListAdapter;
import totoro.application.xkf.totoroweather.application.AppCache;
import totoro.application.xkf.totoroweather.listener.OnCityChangeListener;
import totoro.application.xkf.totoroweather.listener.OnLoadFinishListener;
import totoro.application.xkf.totoroweather.listener.OnSunChangeListener;
import totoro.application.xkf.totoroweather.model.City;
import totoro.application.xkf.totoroweather.model.DailyForecast;
import totoro.application.xkf.totoroweather.model.HourlyForecast;
import totoro.application.xkf.totoroweather.model.NowWeather;
import totoro.application.xkf.totoroweather.model.Suggestion;
import totoro.application.xkf.totoroweather.model.Weather;
import totoro.application.xkf.totoroweather.service.DataService;
import totoro.application.xkf.totoroweather.util.AlertDialogBuilder;
import totoro.application.xkf.totoroweather.util.ImageSelector;
import totoro.application.xkf.totoroweather.util.LocationUtil;
import totoro.application.xkf.totoroweather.util.LogUtil;
import totoro.application.xkf.totoroweather.util.NetUtil;
import totoro.application.xkf.totoroweather.util.PreferenceUtil;

public class WeatherActivity extends AppCompatActivity implements AMapLocationListener,
        OnLoadFinishListener, SwipeRefreshLayout.OnRefreshListener, OnSunChangeListener,
        NavigationView.OnNavigationItemSelectedListener, View.OnClickListener, OnCityChangeListener {
    private DrawerLayout dlDrawerLayout;
    private CollapsingToolbarLayout ctlCollapsingToolbarLayout;
    private ImageView ivHeaderImage;
    private Toolbar tbToolbar;
    private SwipeRefreshLayout srlRefreshLayout;
    private RecyclerView rvDailyForecastList;
    private RecyclerView rvSuggestionList;
    private RecyclerView rvHourlyList;
    private ImageView ivNowWeatherIcon;
    private TextView tvTmpAndWeather;
    private TextView tvFeelAndWind;
    private NavigationView nvNavigationView;
    private ImageView ivNavigationHeadImage;
    private FloatingActionButton fabSearch;

    private Handler mHandler = new Handler();
    private Snackbar mSnackbar;
    private DataService mDataService;
    private final int REQUEST_CODE = 1;
    private boolean canLocation = true;
    private boolean canFinish = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (PreferenceUtil.getIsNightMode()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        mDataService = AppCache.getDataService();
        mDataService.setCityChangeListener(this);
        checkPermissions();

    }

    private void checkPermissions() {
        //申请高德所需权限
        List<String> permissions = new ArrayList<>();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) !=
                    PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.ACCESS_COARSE_LOCATION);
            }
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
                    PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.ACCESS_FINE_LOCATION);
            }
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) !=
                    PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.READ_EXTERNAL_STORAGE);
            }
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) !=
                    PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.READ_PHONE_STATE);
            }
        }
        if (!permissions.isEmpty() && Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions.toArray(new String[permissions.size()]), REQUEST_CODE);
        } else {
            initViews();
        }
    }

    private void initViews() {
        //隐藏状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //加载组件
        dlDrawerLayout = (DrawerLayout) findViewById(R.id.dl_drawer_layout);
        ctlCollapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.ctl_collapsing_layout);
        tbToolbar = (Toolbar) findViewById(R.id.tb_toolbar);
        ivHeaderImage = (ImageView) findViewById(R.id.iv_header_image);
        ivNowWeatherIcon = (ImageView) findViewById(R.id.iv_now_weather_icon);
        tvTmpAndWeather = (TextView) findViewById(R.id.tv_tmp_and_weather);
        tvFeelAndWind = (TextView) findViewById(R.id.tv_feel_and_wind);
        srlRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.srl_refresh_layout);
        mSnackbar = Snackbar.make(ctlCollapsingToolbarLayout, "", Snackbar.LENGTH_SHORT);
        srlRefreshLayout.setOnRefreshListener(this);
        fabSearch = (FloatingActionButton) findViewById(R.id.fab_search);
        rvDailyForecastList = (RecyclerView) findViewById(R.id.rv_forecast_list);
        rvHourlyList = (RecyclerView) findViewById(R.id.rv_hourly_list);
        rvSuggestionList = (RecyclerView) findViewById(R.id.rv_suggestion_list);
        nvNavigationView = (NavigationView) findViewById(R.id.nv_navigation_view);
        nvNavigationView.setNavigationItemSelectedListener(this);
        View headLayout = nvNavigationView.getHeaderView(0);
        ivNavigationHeadImage = headLayout.findViewById(R.id.iv_navigation_head_image);
        srlRefreshLayout.setColorSchemeResources
                (R.color.colorAccent, R.color.red, R.color.blue);
        fabSearch.setOnClickListener(this);

        //显示菜单按钮
        setSupportActionBar(tbToolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.mipmap.ic_menu);

        if (NetUtil.isNetConnectivity(this)) {
            if (mDataService.getCurrentWeather() == null) {
                //有网就定位当前城市
                srlRefreshLayout.setRefreshing(true);
                LocationUtil.locationCity(getApplicationContext(), this);
            } else {
                Weather weather = mDataService.getCurrentWeather();
                updateNowWeather(weather.getNowWeather());
                updateDailyForecast(weather.getDailyForecast());
                updateHourlyForecast(weather.getHourlyForecast());
                updateSuggestion(weather.getSuggestion());
            }
        } else {
            mSnackbar.setText(getString(R.string.noNet)).show();
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            dlDrawerLayout.openDrawer(Gravity.START);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (dlDrawerLayout.isDrawerOpen(Gravity.START)) {
            dlDrawerLayout.closeDrawers();
        } else if (canFinish) {
            System.exit(0);
        } else {
            //连续点击两次退出的逻辑
            mSnackbar.setText(getString(R.string.finish_prompt));
            mSnackbar.show();
            canFinish = true;
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    canFinish = false;
                }
            }, 5000);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE:
                for (int value : grantResults) {
                    if (value != PackageManager.PERMISSION_GRANTED) {
                        //不给权限就直接退出
                        System.exit(0);
                    }
                }
                initViews();
                break;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (aMapLocation != null) {
            if (aMapLocation.getErrorCode() == 0) {
                //高德定位不知道为啥有的时候会连续定位两次
                //所以增加这个变量来控制反复加载
                if (canLocation) {
                    LocationUtil.stop();
                    String city = aMapLocation.getCity();
                    mDataService.searchCity(city, true, null);
                    mDataService.loadWeatherInfo(city, this);
                    canLocation = false;
                }
            } else {
                //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                LogUtil.show("AmapError" + "location Error, ErrCode:"
                        + aMapLocation.getErrorCode() + ", errInfo:"
                        + aMapLocation.getErrorInfo());
            }
        }
    }

    @Override
    public void loadSuccess(Weather weather) {
        mSnackbar.setText(getString(R.string.loadSuccess));
        mSnackbar.show();
        //更新一系列天气
        updateNowWeather(weather.getNowWeather());
        updateDailyForecast(weather.getDailyForecast());
        updateHourlyForecast(weather.getHourlyForecast());
        updateSuggestion(weather.getSuggestion());
        srlRefreshLayout.setRefreshing(false);
    }

    private void updateSuggestion(Suggestion suggestion) {
        SuggestionListAdapter adapter = new SuggestionListAdapter(suggestion);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rvSuggestionList.setLayoutManager(manager);
        rvSuggestionList.setAdapter(adapter);
    }

    public void updateNowWeather(NowWeather nowWeather) {
        ctlCollapsingToolbarLayout.setTitle(nowWeather.getName());
        String feelAndWnd = "体感温度  " + nowWeather.getFeel() + "°  " + nowWeather.getWindType() + "  " +
                nowWeather.getWindDegree() + "级";
        tvFeelAndWind.setText(feelAndWnd);
        String tmpAndWeather = nowWeather.getDescription() + "     " + nowWeather.getTemperature() + "°";
        tvTmpAndWeather.setText(tmpAndWeather);
        ivNowWeatherIcon.setImageResource(ImageSelector.selectWeatherIcon(nowWeather.getCode()));
    }

    public void updateDailyForecast(DailyForecast dailyForecast) {
        DailyForecastListAdapter adapter = new DailyForecastListAdapter(dailyForecast);
        adapter.setSunChangeListener(this);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rvDailyForecastList.setLayoutManager(manager);
        rvDailyForecastList.setAdapter(adapter);
    }

    public void updateHourlyForecast(HourlyForecast hourlyForecast) {
        HourlyForecastListAdapter adapter = new HourlyForecastListAdapter(hourlyForecast);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rvHourlyList.setLayoutManager(manager);
        rvHourlyList.setAdapter(adapter);
    }

    @Override
    public void loadFail() {
        mSnackbar.setText(getString(R.string.loadFail));
        mSnackbar.show();
        srlRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        srlRefreshLayout.setRefreshing(true);
        if (NetUtil.isNetConnectivity(this)) {
            String city = mDataService.getCurrentCityId();
            if (city == null) {
                LocationUtil.locationCity(this.getApplicationContext(), this);
            } else {
                mDataService.loadWeatherInfo(city, this);
            }
        } else {
            mSnackbar.setText(getString(R.string.noNet)).show();
            srlRefreshLayout.setRefreshing(false);
        }
    }


    @Override
    public void onSunChange(String dayCode, String nightCode, String sunRise, String sunSet) {
        ivHeaderImage.setImageResource(ImageSelector.selectHeadImage(dayCode, nightCode, sunRise, sunSet));
        ivNavigationHeadImage.setImageResource(ImageSelector.selectNavitionHeadImage(sunRise, sunSet));
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull final MenuItem item) {
        dlDrawerLayout.closeDrawers();
        switch (item.getItemId()) {
            case R.id.city_manage:
                startActivity(new Intent(this, CityManageActivity.class));
                break;
            case R.id.night_mode:
                if (PreferenceUtil.getIsNightMode()) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                }
                PreferenceUtil.saveIsNightMode(!PreferenceUtil.getIsNightMode());
                recreate();
                break;
            case R.id.location_again:
                if (NetUtil.isNetConnectivity(this)) {
                    srlRefreshLayout.setRefreshing(true);
                    canLocation = true;
                    LocationUtil.locationCity(getApplicationContext(), this);
                } else {
                    mSnackbar.setText(getString(R.string.noNet)).show();
                }
                break;
            case R.id.share:
                share(getString(R.string.shareApp).replace
                        ("@", mDataService.getCurrentWeather().getNowWeather().getName() +
                                mDataService.getCurrentWeather().getNowWeather().getTemperature()));
                break;
            case R.id.quit:
                AlertDialogBuilder.createDialog(this, item.getTitle().toString(),
                        getString(R.string.askQuit), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                System.exit(0);
                            }
                        });
                break;
        }
        return true;
    }

    public void share(String message) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, message);
        startActivity(Intent.createChooser(intent, "请选择"));
    }

    @Override
    public void onClick(View view) {
        startActivity(new Intent(this, SearchActivity.class));
    }

    @Override
    public void onCityChange(City city) {
        onRefresh();
    }
}
