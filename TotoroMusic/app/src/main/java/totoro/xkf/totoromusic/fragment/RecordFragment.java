package totoro.xkf.totoromusic.fragment;

import android.animation.ObjectAnimator;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import de.hdodenhof.circleimageview.CircleImageView;
import totoro.xkf.totoromusic.R;
import totoro.xkf.totoromusic.application.AppCache;
import totoro.xkf.totoromusic.model.Music;
import totoro.xkf.totoromusic.service.MusicService;
import totoro.xkf.totoromusic.util.CoverLoader;

public class RecordFragment extends Fragment {
    private ImageView ivRecord;
    private ImageView ivNeedle;
    private CircleImageView ivCover;
    private Handler mHandler = new Handler();
    private static final int UPDATE_TIME = 40;
    private static final int ANGLE_CHANGE_VALUE = 1;
    public int angle = 0;
    private Music lastMusic;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.record_fragment, container, false);
        lastMusic = AppCache.getMusicService().getCurrentPlayingMusic();
        ivRecord = view.findViewById(R.id.iv_record);
        ivNeedle = view.findViewById(R.id.iv_needle);
        ivCover = view.findViewById(R.id.iv_cover);
        ivNeedle.setPivotX(10);
        ivNeedle.setPivotY(10);
        ivNeedle.setRotation(-25f);
        if (lastMusic.getType() == Music.TYPE_LOCAL) {
            ivCover.setImageBitmap(CoverLoader.createPlayPageCover(AppCache.getMusicService().getCurrentPlayingMusic()));
        } else {
            Glide.with(getContext()).asBitmap().load(lastMusic.getCoverPath()).into(new SimpleTarget<Bitmap>() {
                @Override
                public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                    ivCover.setImageBitmap(resource);
                }
            });
        }
        if (AppCache.getMusicService().getPlayState() == MusicService.PLAY_STATE_PLAYING) {
            startAnimation();
        }
        return view;
    }

    private Runnable coverRotation = new Runnable() {
        @Override
        public void run() {
            angle += ANGLE_CHANGE_VALUE;
            if (angle >= 360) {
                angle = 0;
            }
            ivCover.setRotation(angle);
            ivRecord.setRotation(angle);
            mHandler.postDelayed(coverRotation, UPDATE_TIME);
        }
    };

    public void startAnimation() {
        mHandler.removeCallbacks(coverRotation);
        mHandler.post(coverRotation);
        ObjectAnimator needleStart = ObjectAnimator.ofFloat(ivNeedle, "Rotation", -25f, 0);
        needleStart.setDuration(500);
        needleStart.start();
    }

    public void pauseAnimation() {
        mHandler.removeCallbacks(coverRotation);
        ObjectAnimator needlePause = ObjectAnimator.ofFloat(ivNeedle, "Rotation", 0, -25f);
        needlePause.setDuration(500);
        needlePause.start();
    }

    public void setCover(Bitmap bitmap) {
        ivCover.setImageBitmap(bitmap);
        if (lastMusic.getId() != AppCache.getMusicService().getCurrentPlayingMusic().getId()) {
            ivCover.setRotation(0);
            ivRecord.setRotation(0);
            angle = 0;
            lastMusic = AppCache.getMusicService().getCurrentPlayingMusic();
        }
    }
}
