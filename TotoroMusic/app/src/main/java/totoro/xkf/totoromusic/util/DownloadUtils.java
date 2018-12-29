package totoro.xkf.totoromusic.util;

import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Environment;
import android.widget.Toast;

import java.io.File;
import java.util.LinkedList;

import totoro.xkf.totoromusic.application.AppCache;
import totoro.xkf.totoromusic.broadcast.DownloadFinishReceiver;
import totoro.xkf.totoromusic.model.Music;

import static android.content.Context.DOWNLOAD_SERVICE;

public class DownloadUtils {
    public static void downloadMusic(Context context, Music music) {
        if (!NetUtils.isNetConnectivity()) {
            Toast.makeText(context, "没有网", Toast.LENGTH_SHORT).show();
            return;
        }
        boolean canDownloadByMobileNet = PreferenceUtils.getCanMobileNetworkDownload();
        if (NetUtils.isWifi() || canDownloadByMobileNet) {
            //自己维护一个队列，保证不会重复下载
            LinkedList<Music> downloadQueue = AppCache.getMusicService().getDownloadQueue();
            for (Music song : downloadQueue) {
                if (music.getId() == song.getId()) {
                    Toast.makeText(context, "已经在下载队列了", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
            //查看是不是有了，保证不会下载两首同样的歌
            File file = new File(FileUtils.getExternalStorageDirectory() + FileUtils.getDownloadMusicPath() + music.getTitle() + ".mp3");
            if (file.exists()) {
                Toast.makeText(context, "已存在歌曲", Toast.LENGTH_SHORT).show();
                return;
            }
            //这里的逻辑是一起下载.mp3文件，歌词和略缩图
            DownloadManager downloadManager = (DownloadManager) context.getSystemService(DOWNLOAD_SERVICE);
            //下载略缩图
            DownloadManager.Request downloadCoverRequest = new DownloadManager.Request(Uri.parse(music.getCoverPath()));
            downloadCoverRequest.setNotificationVisibility(DownloadManager.Request.VISIBILITY_HIDDEN);
            downloadCoverRequest.setDestinationInExternalPublicDir(FileUtils.getDownloadCoverPath(), music.getTitle() + ".jpg");
            downloadManager.enqueue(downloadCoverRequest);

            //下载歌词
            DownloadManager.Request downloadLrcRequest = new DownloadManager.Request(Uri.parse(music.getLrc()));
            downloadLrcRequest.setDestinationInExternalPublicDir(FileUtils.getDownloadLrcPath(), music.getTitle() + ".lrc");
            downloadCoverRequest.setNotificationVisibility(DownloadManager.Request.VISIBILITY_HIDDEN);
            downloadManager.enqueue(downloadLrcRequest);

            //下载.MP3
            DownloadManager.Request downloadMusicRequest = new DownloadManager.Request(Uri.parse(music.getPath()));
            downloadMusicRequest.allowScanningByMediaScanner();
            music.setPath(FileUtils.getExternalStorageDirectory() + FileUtils.getDownloadMusicPath() + music.getTitle() + ".mp3");
            downloadMusicRequest.setDestinationInExternalPublicDir(FileUtils.getDownloadMusicPath(), music.getTitle() + ".mp3");
            downloadMusicRequest.setTitle("正在下载" + music.getTitle());
            Toast.makeText(context, "开始下载", Toast.LENGTH_SHORT).show();
            long id = downloadManager.enqueue(downloadMusicRequest);
            music.setDownloadId(id);
            downloadQueue.addLast(music);

        } else {
            new AlertDialog.Builder(context).setTitle("下载确认")
                    .setMessage("当前不允许使用数据流量下载，是否允许操作")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            PreferenceUtils.saveCanMobileNetworkDownload(true);
                        }
                    }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            }).show();
        }
    }
}
