package totoro.xkf.totoromusic.adapter;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import totoro.xkf.totoromusic.R;
import totoro.xkf.totoromusic.application.AppCache;
import totoro.xkf.totoromusic.listener.OnMoreClickListener;
import totoro.xkf.totoromusic.model.Music;
import totoro.xkf.totoromusic.service.MusicService;
import totoro.xkf.totoromusic.util.CoverLoader;
import totoro.xkf.totoromusic.util.LogUtils;

public class LocalMusicAdapter extends RecyclerView.Adapter<LocalMusicAdapter.ViewHolder> {
    private OnMoreClickListener mOnMoreClickListener;
    private int lastPosition;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.local_music_list_item,
                parent, false);
        return new ViewHolder(view);
    }

    public void setOnMoreClickListener(OnMoreClickListener listener) {
        mOnMoreClickListener = listener;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Music music = AppCache.getMusicList().get(position);
        holder.ivLMFragmentMusicIcon.setTag(position);
        holder.ivLMFragmentMusicIcon.setImageResource(R.mipmap.default_cover);
        //略缩图必须放在后台加载，不然卡顿
        new AsyncTask<String, Void, Bitmap>() {
            @Override
            protected Bitmap doInBackground(String... params) {
                return CoverLoader.createLocalCoverBitmap(params[0], params[1]);
            }

            @Override
            protected void onPostExecute(Bitmap bitmap) {
                super.onPostExecute(bitmap);
                if ((int) holder.ivLMFragmentMusicIcon.getTag() == position) {
                    holder.ivLMFragmentMusicIcon.setImageBitmap(bitmap);
                }
            }
        }.execute(music.getPath(), music.getTitle());
        holder.tvLMFragmentMusicName.setText(music.getTitle());
        holder.tvLMFragmentArtist.setText(music.getArtist());
        if (AppCache.getMusicService().getCurrentPlayingMusic().getId() == music.getId()) {
            lastPosition = position;
            AppCache.getMusicService().setPlayingPosition(position);
            holder.vLMFragmentLabel.setVisibility(View.VISIBLE);
        } else {
            holder.vLMFragmentLabel.setVisibility(View.GONE);
        }
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCache.getMusicService().play(music);
                notifyItemChanged(lastPosition);
                AppCache.getMusicService().setPlayingPosition(position);
                lastPosition = position;
                holder.vLMFragmentLabel.setVisibility(View.VISIBLE);
            }
        };
        holder.flLMFragmentMessage.setOnClickListener(listener);
        holder.ivLMFragmentMusicIcon.setOnClickListener(listener);
        holder.ibLMFragmentMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnMoreClickListener != null) {
                    mOnMoreClickListener.onMoreClick(music);
                }
            }
        });
    }

    public void updatePlayLabel(int newPosition) {
        notifyItemChanged(newPosition);
        notifyItemChanged(lastPosition);
    }

    @Override
    public int getItemCount() {
        return AppCache.getMusicList().size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivLMFragmentMusicIcon;
        private FrameLayout flLMFragmentMessage;
        private TextView tvLMFragmentArtist;
        private TextView tvLMFragmentMusicName;
        private ImageButton ibLMFragmentMore;
        private View vLMFragmentLabel;

        public ViewHolder(View itemView) {
            super(itemView);
            ivLMFragmentMusicIcon = itemView.findViewById(R.id.iv_lmfragment_music_icon);
            flLMFragmentMessage = itemView.findViewById(R.id.fl_lmfragment_message);
            tvLMFragmentArtist = itemView.findViewById(R.id.tv_lmfragment_artist);
            tvLMFragmentMusicName = itemView.findViewById(R.id.tv_lmfragment_music_name);
            ibLMFragmentMore = itemView.findViewById(R.id.ib_lmfragment_more);
            vLMFragmentLabel = itemView.findViewById(R.id.v_lmfragment_label);
        }
    }

}
