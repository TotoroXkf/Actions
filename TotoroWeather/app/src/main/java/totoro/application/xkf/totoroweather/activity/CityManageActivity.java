package totoro.application.xkf.totoroweather.activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;

import totoro.application.xkf.totoroweather.R;
import totoro.application.xkf.totoroweather.adapter.CityManageListAdapter;
import totoro.application.xkf.totoroweather.application.AppCache;
import totoro.application.xkf.totoroweather.listener.OnCityChangeListener;
import totoro.application.xkf.totoroweather.model.City;

public class CityManageActivity extends AppCompatActivity implements OnCityChangeListener {
    private FloatingActionButton fabAdd;
    private RecyclerView rvCityList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_manage);
        initViews();
    }

    private void initViews() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        fabAdd = (FloatingActionButton) findViewById(R.id.fab_add);
        rvCityList = (RecyclerView) findViewById(R.id.rv_city_list);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        CityManageListAdapter adapter = new CityManageListAdapter(this);
        rvCityList.setAdapter(adapter);
        rvCityList.setLayoutManager(manager);

        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CityManageActivity.this, SearchActivity.class));
                finish();
            }
        });
    }

    @Override
    public void onCityChange(City city) {
        AppCache.getDataService().onCityChange(city);
        finish();
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
