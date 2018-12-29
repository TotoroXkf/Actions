package totoro.xkf.totoromusic.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

import me.wcy.lrcview.LrcView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import totoro.xkf.totoromusic.R;
import totoro.xkf.totoromusic.application.AppCache;
import totoro.xkf.totoromusic.model.Music;
import totoro.xkf.totoromusic.service.MusicService;
import totoro.xkf.totoromusic.util.HttpUtils;
import totoro.xkf.totoromusic.util.LogUtils;
import totoro.xkf.totoromusic.util.UrlBuilder;

public class LrcFragment extends Fragment {
    private LrcView lvLrcView;
    private Handler mHandler = new Handler();
    private static final int UPDATE_TIME = 300;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.lrc_layout, container, false);
        lvLrcView = view.findViewById(R.id.lv_lrc_view);
        loadLrc(AppCache.getMusicService().getCurrentPlayingMusic());
        if (AppCache.getMusicService().getPlayState() == MusicService.PLAY_STATE_PLAYING) {
            startScroll();
        }
        return view;
    }

    public void loadLrc(Music music) {
        if (music.getType() == Music.TYPE_ONLINE) {
            HttpUtils.sendRequest(UrlBuilder.getLrcUrl("" + music.getId()), new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String json = response.body().string();
                    try {
                        final String lrcContent = new JSONObject(json).optString("lrcContent");
                        lvLrcView.post(new Runnable() {
                            @Override
                            public void run() {
                                lvLrcView.loadLrc(lrcContent);
                            }
                        });
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        } else {
            if (music.getLrc() != null) {
                File file = new File(music.getLrc());
                lvLrcView.loadLrc(file);
                lvLrcView.updateTime(0);
            }
        }
    }

    public void startScroll() {
        mHandler.removeCallbacks(scrollLrc);
        mHandler.post(scrollLrc);
    }

    public void pauseScroll() {
        mHandler.removeCallbacks(scrollLrc);
    }

    private Runnable scrollLrc = new Runnable() {
        @Override
        public void run() {
            int position = AppCache.getMusicService().getCurrentSchedule();
            lvLrcView.updateTime(position);
            lvLrcView.computeScroll();
            mHandler.postDelayed(scrollLrc, UPDATE_TIME);
        }
    };
}
