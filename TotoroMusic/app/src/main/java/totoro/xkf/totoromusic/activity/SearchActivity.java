package totoro.xkf.totoromusic.activity;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import totoro.xkf.totoromusic.R;
import totoro.xkf.totoromusic.adapter.SearchMusicAdapter;
import totoro.xkf.totoromusic.broadcast.DownloadFinishReceiver;
import totoro.xkf.totoromusic.json.JsonForSearchList;
import totoro.xkf.totoromusic.listener.OnMoreClickListener;
import totoro.xkf.totoromusic.model.Music;
import totoro.xkf.totoromusic.model.SearchList;
import totoro.xkf.totoromusic.util.DownloadUtils;
import totoro.xkf.totoromusic.util.FileUtils;
import totoro.xkf.totoromusic.util.HttpUtils;
import totoro.xkf.totoromusic.util.LogUtils;
import totoro.xkf.totoromusic.util.UrlBuilder;

public class SearchActivity extends AppCompatActivity implements EditText.OnEditorActionListener, OnMoreClickListener {
    private EditText etSearch;
    private RecyclerView rvSearchList;
    private TextView tvSearchNoMusic;
    private ProgressDialog mProgressDialog;
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSystemBarTransparent();
        setContentView(R.layout.activity_search);
        initViews();
    }

    private void initViews() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        rvSearchList = (RecyclerView) findViewById(R.id.rv_search_list);
        tvSearchNoMusic = (TextView) findViewById(R.id.tv_search_no_music);
        etSearch = (EditText) findViewById(R.id.et_search);
        etSearch.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
        etSearch.setOnEditorActionListener(this);
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setTitle("搜索中");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvSearchList.setLayoutManager(linearLayoutManager);
    }

    private void setSystemBarTransparent() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // LOLLIPOP解决方案
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // KITKAT解决方案
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    @Override
    public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
        if ((keyEvent != null && keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER
                && keyEvent.getAction() == KeyEvent.ACTION_DOWN)) {
            //隐藏键盘
            ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE))
                    .hideSoftInputFromWindow(SearchActivity.this.getCurrentFocus()
                            .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            if (textView.getText().toString().isEmpty()) {
                return false;
            } else {
                mProgressDialog.show();
                HttpUtils.sendRequest(UrlBuilder.getSearchUrl(textView.getText().toString()), new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String json = response.body().string();
                        Gson gson = new Gson();
                        JsonForSearchList jsonForSearchList = gson.fromJson(json, JsonForSearchList.class);
                        final SearchList searchList = jsonForSearchList.changeToSearchList();
                        if (searchList == null) {
                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    mProgressDialog.cancel();
                                    rvSearchList.setVisibility(View.GONE);
                                    tvSearchNoMusic.setVisibility(View.VISIBLE);
                                }
                            });
                        } else {
                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    mProgressDialog.cancel();
                                    tvSearchNoMusic.setVisibility(View.GONE);
                                    rvSearchList.setVisibility(View.VISIBLE);
                                    SearchMusicAdapter adapter = new SearchMusicAdapter(SearchActivity.this, searchList);
                                    adapter.setOnMoreClickListener(SearchActivity.this);
                                    rvSearchList.setAdapter(adapter);
                                }
                            });
                        }
                    }
                });
            }
            return true;
        }
        return false;
    }

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
                                        DownloadUtils.downloadMusic(SearchActivity.this, music);
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
