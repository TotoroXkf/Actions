package totoro.xkf.totoromusic.broadcast;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;

import totoro.xkf.totoromusic.application.AppCache;
import totoro.xkf.totoromusic.fragment.LocalMusicFragment;
import totoro.xkf.totoromusic.model.Music;
import totoro.xkf.totoromusic.util.LogUtils;

public class DownloadFinishReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        LinkedList<Music> downloadQueue = AppCache.getMusicService().getDownloadQueue();
        if (intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1) == downloadQueue.getFirst().getDownloadId()) {
            AppCache.getMusicList().add(downloadQueue.removeFirst());
            ((LocalMusicFragment) AppCache.getData()).refreshData();
            Toast.makeText(context, "下载完成", Toast.LENGTH_SHORT).show();
        }
    }
}
