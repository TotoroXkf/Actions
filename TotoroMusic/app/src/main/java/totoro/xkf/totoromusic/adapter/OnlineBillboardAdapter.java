package totoro.xkf.totoromusic.adapter;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
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
import totoro.xkf.totoromusic.model.OnlineMusicListInfo;
import totoro.xkf.totoromusic.model.OnlinePlayMusic;
import totoro.xkf.totoromusic.util.HttpUtils;
import totoro.xkf.totoromusic.util.ImageUtils;
import totoro.xkf.totoromusic.util.LogUtils;
import totoro.xkf.totoromusic.util.NetUtils;
import totoro.xkf.totoromusic.util.PreferenceUtils;
import totoro.xkf.totoromusic.util.UrlBuilder;

public class OnlineBillboardAdapter extends RecyclerView.Adapter<OnlineBillboardAdapter.ViewHolder>
        implements OnLoadOnlineMusicFinishListener {
    private static final int HEADER_VIEW = 0;
    private static final int CONTENT_VIEW = 1;
    private static final int FOOT_VIEW = 2;
    private ProgressDialog mProgressDialog;
    private Context mContext;
    private OnlineMusicListInfo mOnlineMusicListInfo;
    private OnMoreClickListener mOnMoreClickListener;
    private int lastSize = 0;
    private Handler mHandler = new Handler(Looper.myLooper());

    public void setOnlineMusicListInfo(OnlineMusicListInfo onlineMusicListInfo) {
        this.mOnlineMusicListInfo = onlineMusicListInfo;
        notifyDataSetChanged();
    }

    public void setOnMoreClickListener(OnMoreClickListener onMoreClickListener) {
        this.mOnMoreClickListener = onMoreClickListener;
    }

    public OnlineBillboardAdapter(Context context) {
        super();
        mContext = context;
        AppCache.getMusicService().setOnLoadOnlineMusicFinishListener(this);
        mProgressDialog = new ProgressDialog(mContext);
        mProgressDialog.setTitle(mContext.getString(R.string.load));
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return HEADER_VIEW;
        } else if (position == getItemCount() - 1) {
            return FOOT_VIEW;
        } else {
            return CONTENT_VIEW;
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        switch (viewType) {
            case HEADER_VIEW:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.online_billboard_header, parent, false);
                break;
            case CONTENT_VIEW:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.online_billboard_list_item, parent, false);
                break;
            case FOOT_VIEW:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.online_billboard_foot, parent, false);
                break;
        }
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case HEADER_VIEW:
                createHeaderView(holder, position);
                break;
            case CONTENT_VIEW:
                createContentView(holder, position);
                break;
            case FOOT_VIEW:
                createFootView(holder, position);
                break;
        }
    }

    private void createFootView(ViewHolder holder, int position) {
        if (lastSize != getItemCount()) {
            lastSize = getItemCount();
            holder.llFootLoad.setVisibility(View.VISIBLE);
            holder.tvFootNoMore.setVisibility(View.GONE);
        } else {
            holder.llFootLoad.setVisibility(View.GONE);
            holder.tvFootNoMore.setVisibility(View.VISIBLE);
        }
    }

    private void createContentView(ViewHolder holder, int position) {
        if (mOnlineMusicListInfo != null) {
            //这里注意由于头占了一个位置
            //所以要减一
            final OnlineMusicListInfo.Info info = mOnlineMusicListInfo.getInfoList().get(position - 1);
            Glide.with(mContext).load(info.getCover()).into(holder.ivBillboardListMusicIcon);
            holder.tvBillboardListMusicName.setText(info.getTitle());
            holder.tvBillboardListArtist.setText(info.getAuthor());
            holder.flBillboardListContent.setOnClickListener(new View.OnClickListener() {
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
            holder.ivBillboardListMore.setOnClickListener(new View.OnClickListener() {
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
    }

    private void createHeaderView(final ViewHolder holder, int position) {
        if (mOnlineMusicListInfo != null) {
            Glide.with(mContext).asBitmap().load(mOnlineMusicListInfo.getBackground2())
                    .into(new SimpleTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                            Bitmap background = ImageUtils.blur(resource);
                            holder.ivBackground.setImageBitmap(background);
                        }
                    });
            Glide.with(mContext).load(mOnlineMusicListInfo.getBackground2()).into(holder.ivPoster);
            holder.tvDescribe.setText(mOnlineMusicListInfo.getComment());
            holder.tvUpdateTime.setText(mOnlineMusicListInfo.getUpdateDate());
            holder.tvBillboardName.setText(mOnlineMusicListInfo.getTypeName());
        }
    }

    @Override
    public int getItemCount() {
        if (mOnlineMusicListInfo == null) {
            return 2;
        }
        return mOnlineMusicListInfo.getInfoList().size() + 2;
    }

    private Music parseToMusic(String json) {
        Gson gson = new Gson();
        JsonForOnlinePlayMusic jsonForOnlinePlayMusic = gson.fromJson(json, JsonForOnlinePlayMusic.class);
        OnlinePlayMusic onlinePlayMusic = jsonForOnlinePlayMusic.changeToOnlinePlayMusic();
        Music music = onlinePlayMusic.changeToMusic();
        return music;
    }

    @Override
    public void onLoadFinish() {
        mProgressDialog.cancel();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivBackground;
        private ImageView ivPoster;
        private TextView tvBillboardName;
        private TextView tvUpdateTime;
        private TextView tvDescribe;

        private FrameLayout flBillboardListContent;
        private ImageView ivBillboardListMusicIcon;
        private TextView tvBillboardListMusicName;
        private TextView tvBillboardListArtist;
        private ImageView ivBillboardListMore;

        private LinearLayout llFootLoad;
        private TextView tvFootNoMore;

        public ViewHolder(View itemView) {
            super(itemView);
            ivBackground = itemView.findViewById(R.id.iv_obh_background);
            ivPoster = itemView.findViewById(R.id.iv_obh_poster);
            tvBillboardName = itemView.findViewById(R.id.tv_obh_billboard_name);
            tvUpdateTime = itemView.findViewById(R.id.tv_obh_update_time);
            tvDescribe = itemView.findViewById(R.id.tv_obh_describe);

            flBillboardListContent = itemView.findViewById(R.id.fl_billboard_list_content);
            ivBillboardListMusicIcon = itemView.findViewById(R.id.iv_billboard_list_music_icon);
            tvBillboardListMusicName = itemView.findViewById(R.id.tv_billboard_list_music_name);
            tvBillboardListArtist = itemView.findViewById(R.id.tv_billboard_list_artist);
            ivBillboardListMore = itemView.findViewById(R.id.iv_billboard_list_more);

            llFootLoad = itemView.findViewById(R.id.ll_foot_load);
            tvFootNoMore = itemView.findViewById(R.id.tv_foot_no_more);
        }
    }
}
