package totoro.application.xkf.totoroweather.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import totoro.application.xkf.totoroweather.R;
import totoro.application.xkf.totoroweather.application.AppCache;
import totoro.application.xkf.totoroweather.listener.OnSearchFinishListener;
import totoro.application.xkf.totoroweather.model.City;
import totoro.application.xkf.totoroweather.service.DataService;
import totoro.application.xkf.totoroweather.util.LogUtil;
import totoro.application.xkf.totoroweather.util.TextUtil;

public class SearchActivity extends AppCompatActivity implements TextView.OnEditorActionListener,
        OnSearchFinishListener {
    private EditText etSearchMessage;
    private ListView lvCityList;
    private DataService mDataService;
    private TextView tvNoCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        mDataService = AppCache.getDataService();
        initViews();
    }

    private void initViews() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        etSearchMessage = (EditText) findViewById(R.id.et_search_message);
        lvCityList = (ListView) findViewById(R.id.lv_city_list);
        tvNoCity = (TextView) findViewById(R.id.tv_noCity);
        etSearchMessage.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
        etSearchMessage.setOnEditorActionListener(this);

    }

    @Override
    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        if ((keyEvent != null && keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER
                && keyEvent.getAction() == KeyEvent.ACTION_DOWN)) {
            //隐藏键盘
            ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE))
                    .hideSoftInputFromWindow(SearchActivity.this.getCurrentFocus()
                            .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            if (TextUtils.isEmpty(textView.getText().toString())) {
                return true;
            }
            mDataService.searchCity(textView.getText().toString(), false, this);
            return true;
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }

    @Override
    public void onSearchSuccess(final List<City> cityList) {
        if (cityList == null || cityList.isEmpty()) {
            tvNoCity.setVisibility(View.VISIBLE);
            lvCityList.setVisibility(View.GONE);
            return;
        } else {
            lvCityList.setVisibility(View.VISIBLE);
            tvNoCity.setVisibility(View.GONE);
        }
        String[] cityInfo = new String[cityList.size()];
        for (int i = 0; i < cityInfo.length; i++) {
            City city = cityList.get(i);
            cityInfo[i] = city.getCity() + " - " + city.getProvince();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, cityInfo);
        lvCityList.setAdapter(adapter);
        lvCityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                City city = cityList.get(position);
                mDataService.onCityChange(city);
                finish();
            }
        });
    }

    @Override
    public void onSearchFail() {
        Toast.makeText(this, getString(R.string.loadFail), Toast.LENGTH_SHORT).show();
    }

}


