package totoro.xkf.totoromusic.adapter;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import totoro.xkf.totoromusic.R;
import totoro.xkf.totoromusic.application.AppCache;
import totoro.xkf.totoromusic.json.JsonForOnlinePlayMusic;
import totoro.xkf.totoromusic.listener.OnLoadOnlineMusicFinishListener;
import totoro.xkf.totoromusic.listener.OnMoreClickListener;
import totoro.xkf.totoromusic.model.Music;
import totoro.xkf.totoromusic.model.OnlinePlayMusic;
import totoro.xkf.totoromusic.model.SearchList;
import totoro.xkf.totoromusic.util.HttpUtils;
import totoro.xkf.totoromusic.util.NetUtils;
import totoro.xkf.totoromusic.util.PreferenceUtils;
import totoro.xkf.totoromusic.util.UrlBuilder;

public class SearchMusicAdapter extends RecyclerView.Adapter<SearchMusicAdapter.ViewHolder>
        implements OnLoadOnlineMusicFinishListener {
    private SearchList mSearchList;
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private Context mContext;
    private ProgressDialog mProgressDialog;
    private OnMoreClickListener mOnMoreClickListener;

    public SearchMusicAdapter(Context context, SearchList searchList) {
        super();
        mSearchList = searchList;
        mContext = context;
        AppCache.getMusicService().setOnLoadOnlineMusicFinishListener(this);
        mProgressDialog = new ProgressDialog(mContext);
        mProgressDialog.setTitle(mContext.getString(R.string.load));
    }

    public void setOnMoreClickListener(OnMoreClickListener onMoreClickListener) {
        this.mOnMoreClickListener = onMoreClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (position == getItemCount() - 1) {
            holder.vSearchDivider.setVisibility(View.INVISIBLE);
        }
        final SearchList.Info info = mSearchList.getmInfoList().get(position);
        holder.tvName.setText(info.getSongName() + "-" + info.getArtistName());
        holder.flSearchListItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HttpUtils.sendRequest(UrlBuilder.getOnlinePlayUrl(info.getSongId()), new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        final String json = response.body().string();
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                if (NetUtils.isNetConnectivity()) {
                                    boolean canPlayByMobileNet = PreferenceUtils.getCanMobileNetworkPlay();
                                    if (NetUtils.isWifi() || canPlayByMobileNet) {
                                        mProgressDialog.show();
                                        Music music = parseToMusic(json);
                                        AppCache.getMusicService().play(music);
                                    } else {
                                        new AlertDialog.Builder(mContext).setTitle("下载确认")
                                                .setMessage("当前不允许使用数据流量播放，是否允许操作")
                                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        PreferenceUtils.saveCanMobileNetworkPlay(true);
                                                    }
                                                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {

                                            }
                                        }).show();
                                    }
                                } else {
                                    Toast.makeText(mContext, "没有网", Toast.LENGTH_SHORT).show();
                                    mProgressDialog.cancel();
                                }
                            }
                        });
                    }
                });
            }
        });
        holder.ivSearchMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                HttpUtils.sendRequest(UrlBuilder.getOnlinePlayUrl(info.getSongId()), new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        final String json = response.body().string();
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                Music music = parseToMusic(json);
                                mOnMoreClickListener.onMoreClick(music);
                            }
                        });
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return mSearchList.getmInfoList().size();
    }

    @Override
    public void onLoadFinish() {
        mProgressDialog.cancel();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private FrameLayout flSearchListItem;
        private TextView tvName;
        private ImageView ivSearchMore;
        private View vSearchDivider;

        public ViewHolder(View itemView) {
            super(itemView);
            vSearchDivider = itemView.findViewById(R.id.v_search_divider);
            flSearchListItem = itemView.findViewById(R.id.fl_search_list_item);
            tvName = itemView.findViewById(R.id.tv_name);
            ivSearchMore = itemView.findViewById(R.id.iv_search_more);
        }

    }

    private Music parseToMusic(String json) {
        Gson gson = new Gson();
        JsonForOnlinePlayMusic jsonForOnlinePlayMusic = gson.fromJson(json, JsonForOnlinePlayMusic.class);
        OnlinePlayMusic onlinePlayMusic = jsonForOnlinePlayMusic.changeToOnlinePlayMusic();
        Music music = onlinePlayMusic.changeToMusic();
        return music;
    }
}
