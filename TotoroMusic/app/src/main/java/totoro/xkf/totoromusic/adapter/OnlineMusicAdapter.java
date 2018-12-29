package totoro.xkf.totoromusic.adapter;

import android.content.Context;
import android.content.Intent;
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

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import totoro.xkf.totoromusic.R;
import totoro.xkf.totoromusic.activity.OnlineMusicActivity;
import totoro.xkf.totoromusic.json.JsonForOnlineMusic;
import totoro.xkf.totoromusic.model.OnlineMusicListInfo;
import totoro.xkf.totoromusic.util.HttpUtils;
import totoro.xkf.totoromusic.util.UrlBuilder;

public class OnlineMusicAdapter extends RecyclerView.Adapter<OnlineMusicAdapter.ViewHolder> {
    private String[] onlineMusicType;
    private int[] onlineMusicTypeId;
    private Context mContext;
    private OnlineMusicListInfo[] mOnlineMusicListInfos;
    private Handler mHandler = new Handler(Looper.getMainLooper());

    public OnlineMusicAdapter(Context context) {
        super();
        mContext = context;
        onlineMusicType = mContext.getResources().getStringArray(R.array.online_music_type);
        onlineMusicTypeId = mContext.getResources().getIntArray(R.array.online_music_top_id);
        mOnlineMusicListInfos = new OnlineMusicListInfo[onlineMusicTypeId.length];
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.online_music_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        if (position == getItemCount() - 1) {
            holder.vOnlineDivider.setVisibility(View.INVISIBLE);
        }
        holder.tvSongType.setText(onlineMusicType[position]);
        holder.tvSongType.setTag(position);
        if (mOnlineMusicListInfos[position] == null) {
            //第一次需要加载，之后存到数组里直接用
            HttpUtils.sendRequest(UrlBuilder.getListUrl(onlineMusicTypeId[position], 3), new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String json = response.body().string();
                    Gson gson = new Gson();
                    JsonForOnlineMusic jsonForOnlineMusic = gson.fromJson(json, JsonForOnlineMusic.class);
                    final OnlineMusicListInfo onlineMusicListInfo = jsonForOnlineMusic.changeToOnlineMusicInfo();
                    Runnable updateItem = new Runnable() {
                        @Override
                        public void run() {
                            if ((int) holder.tvSongType.getTag() == position) {
                                updateMessage(holder, onlineMusicListInfo);
                                mOnlineMusicListInfos[position] = onlineMusicListInfo;
                            }
                        }
                    };
                    mHandler.post(updateItem);
                }
            });
        } else {
            updateMessage(holder, mOnlineMusicListInfos[position]);
        }
        holder.flItemContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, OnlineMusicActivity.class);
                intent.putExtra("Position", position);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return onlineMusicType.length;
    }

    public void updateMessage(ViewHolder holder, OnlineMusicListInfo onlineMusicListInfo) {
        Glide.with(mContext.getApplicationContext()).load(onlineMusicListInfo.getBackground1()).into(holder.ivPoster);
        List<OnlineMusicListInfo.Info> list = onlineMusicListInfo.getInfoList();
        holder.tvMusicName1.setText("1.  " + list.get(0).getTitle() + "-" + list.get(0).getAuthor());
        holder.tvMusicName2.setText("2.  " + list.get(1).getTitle() + "-" + list.get(1).getAuthor());
        holder.tvMusicName3.setText("3.  " + list.get(2).getTitle() + "-" + list.get(2).getAuthor());
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private FrameLayout flItemContent;
        private TextView tvSongType;
        private ImageView ivPoster;
        private TextView tvMusicName1;
        private TextView tvMusicName2;
        private TextView tvMusicName3;
        private View vOnlineDivider;

        public ViewHolder(View itemView) {
            super(itemView);
            flItemContent = itemView.findViewById(R.id.fl_item_content);
            tvSongType = itemView.findViewById(R.id.tv_song_type);
            ivPoster = itemView.findViewById(R.id.iv_poster);
            tvMusicName1 = itemView.findViewById(R.id.tv_music_name1);
            tvMusicName2 = itemView.findViewById(R.id.tv_music_name2);
            tvMusicName3 = itemView.findViewById(R.id.tv_music_name3);
            vOnlineDivider = itemView.findViewById(R.id.v_online_divider);
        }
    }
}
