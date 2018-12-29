package totoro.xkf.totoromusic.util;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.NotificationCompat;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;

import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import totoro.xkf.totoromusic.R;
import totoro.xkf.totoromusic.activity.MusicActivity;
import totoro.xkf.totoromusic.application.AppCache;
import totoro.xkf.totoromusic.model.Music;

public class NotificationUtils {
    public static final int MUSIC_NOTIFICATION_ID = 74123;
    public static final String NOTIFICATION_NEXT = "Next";
    public static final String NOTIFICATION_START_OR_PAUSE = "PlayOrPause";
    public static final String BUTTON_ACTION = "NotificationButtonAction";

    public static Notification createMusicNotification(Context context, Music music, boolean isPlaying, Bitmap onlinePic) {
        final NotificationCompat.Builder builder = new NotificationCompat.Builder(context);

        final RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.notification_layout);
        remoteViews.setTextViewText(R.id.tv_nfc_music_name, music.getTitle());
        remoteViews.setTextViewText(R.id.tv_nfc_artist, music.getArtist());
        if (isPlaying) {
            remoteViews.setImageViewResource(R.id.ib_nfc_start_or_pause, R.mipmap.ic_status_bar_pause_dark);
        } else {
            remoteViews.setImageViewResource(R.id.ib_nfc_start_or_pause, R.mipmap.ic_status_bar_play_dark);
        }
        if (onlinePic != null) {
            remoteViews.setImageViewBitmap(R.id.iv_nfc_music_icon, onlinePic);
        } else {
            Bitmap bitmap = CoverLoader.createLocalCoverBitmap(music.getPath(), music.getTitle());
            remoteViews.setImageViewBitmap(R.id.iv_nfc_music_icon, bitmap);
        }
        Intent playOrPause = new Intent(BUTTON_ACTION);
        playOrPause.putExtra(NOTIFICATION_START_OR_PAUSE, NOTIFICATION_START_OR_PAUSE);
        Intent next = new Intent(BUTTON_ACTION);
        next.putExtra(NOTIFICATION_NEXT, NOTIFICATION_NEXT);
        Intent musicActivity = new Intent(context, MusicActivity.class);
        PendingIntent playOrPauseIntent = PendingIntent.getBroadcast
                (context, 0, playOrPause, PendingIntent.FLAG_UPDATE_CURRENT);
        PendingIntent nextIntent = PendingIntent.getBroadcast
                (context, 1, next, PendingIntent.FLAG_UPDATE_CURRENT);
        PendingIntent musicActivityIntent = PendingIntent.getActivity
                (context, 2, musicActivity, PendingIntent.FLAG_UPDATE_CURRENT);
        remoteViews.setOnClickPendingIntent(R.id.ib_nfc_start_or_pause, playOrPauseIntent);
        remoteViews.setOnClickPendingIntent(R.id.ib_nfc_next, nextIntent);
        builder.setContentIntent(musicActivityIntent);

        builder.setContent(remoteViews);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        return builder.build();
    }

    public class NotificationControl extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String message = intent.getStringExtra(NOTIFICATION_START_OR_PAUSE);
            if (message == null) {
                AppCache.getMusicService().next();
            } else {
                AppCache.getMusicService().playOrPause();
            }
        }
    }
}
