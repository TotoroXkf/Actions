package totoro.xkf.totoromusic.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import totoro.xkf.totoromusic.R;
import totoro.xkf.totoromusic.adapter.OnlineBillboardAdapter;
import totoro.xkf.totoromusic.json.JsonForOnlineMusic;
import totoro.xkf.totoromusic.listener.OnMoreClickListener;
import totoro.xkf.totoromusic.model.Music;
import totoro.xkf.totoromusic.model.OnlineMusicListInfo;
import totoro.xkf.totoromusic.util.DownloadUtils;
import totoro.xkf.totoromusic.util.HttpUtils;
import totoro.xkf.totoromusic.util.LogUtils;
import totoro.xkf.totoromusic.util.UrlBuilder;

public class OnlineMusicActivity extends AppCompatActivity implements OnMoreClickListener {
    private int typeId = -1;
    private int listSize = 20;
    private OnlineBillboardAdapter mAdapter;
    private RecyclerView rvOnlineMusicList;
    private LinearLayout llOnlineMusicLoading;
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private int lastPosition = -1;
    private boolean canLoad = true;
    private int lastSize = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_music);
        initViews();
    }

    public void initViews() {
        String[] typeName = getResources().getStringArray(R.array.online_music_type);
        int position = getIntent().getIntExtra("Position", -1);
        typeId = getResources().getIntArray(R.array.online_music_top_id)[position];
        rvOnlineMusicList = (RecyclerView) findViewById(R.id.rv_oma_list);
        llOnlineMusicLoading = (LinearLayout) findViewById(R.id.ll_oma_loading);
        rvOnlineMusicList.setVisibility(View.INVISIBLE);
        llOnlineMusicLoading.setVisibility(View.VISIBLE);
        Toolbar toolbar = (Toolbar) findViewById(R.id.tb_oma_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(typeName[position]);
        }
        mAdapter = new OnlineBillboardAdapter(this);
        mAdapter.setOnMoreClickListener(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvOnlineMusicList.setAdapter(mAdapter);
        rvOnlineMusicList.setLayoutManager(linearLayoutManager);
        rvOnlineMusicList.addOnScrollListener(mOnScrollListener);
        loadList();
    }

    public void loadList() {
        HttpUtils.sendRequest(UrlBuilder.getListUrl(typeId, listSize), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                Gson gson = new Gson();
                JsonForOnlineMusic jsonForOnlineMusic = gson.fromJson(json, JsonForOnlineMusic.class);
                final OnlineMusicListInfo onlineMusicListInfo = jsonForOnlineMusic.changeToOnlineMusicInfo();
                if (lastSize == onlineMusicListInfo.getInfoList().size()) {
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            canLoad = false;
                            mAdapter.setOnlineMusicListInfo(onlineMusicListInfo);
                        }
                    });
                    return;
                }
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        llOnlineMusicLoading.setVisibility(View.INVISIBLE);
                        rvOnlineMusicList.setVisibility(View.VISIBLE);
                        mAdapter.setOnlineMusicListInfo(onlineMusicListInfo);
                        lastSize = onlineMusicListInfo.getInfoList().size();
                        listSize += 20;
                        canLoad = true;
                    }
                });
            }
        });
    }

    private RecyclerView.OnScrollListener mOnScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (canLoad && newState == RecyclerView.SCROLL_STATE_IDLE &&
                    lastPosition >= recyclerView.getAdapter().getItemCount() - 1) {
                canLoad = false;
                loadList();
            }
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            lastPosition = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastVisibleItemPosition();
        }
    };

    @Override
    public void onMoreClick(final Music music) {
        new AlertDialog.Builder(this).setTitle(getString(R.string.more))
                .setItems(new String[]{getString(R.string.share), getString(R.string.download)},
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which) {
                                    case 0:
                                        share(music);
                                        break;
                                    case 1:
                                        dialog.cancel();
                                        DownloadUtils.downloadMusic(OnlineMusicActivity.this, music);
                                        break;
                                }
                            }
                        }).show();
    }

    private void share(Music music) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, getString(R.string.shareMusic) + "《" + music.getTitle() + "》" + music.getPath());
        startActivity(Intent.createChooser(intent, getString(R.string.shareTo)));
    }
}
