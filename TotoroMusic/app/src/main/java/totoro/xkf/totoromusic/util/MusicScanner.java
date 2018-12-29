package totoro.xkf.totoromusic.util;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.provider.MediaStore;

import java.io.File;
import java.util.List;

import totoro.xkf.totoromusic.model.Music;

public class MusicScanner {
    public static void scanMusic(Context context, List<Music> musicList) {
        musicList.clear();
        Cursor cursor = context.getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                null, null, null, MediaStore.Audio.Media.DEFAULT_SORT_ORDER);

        long minSize = 100 * 1024;
        long minTime = 20 * 1000;

        while (cursor.moveToNext()) {
            long fileSize = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.SIZE));
            long fileTime = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION));
            if (fileSize > minSize && fileTime > minTime) {
                Music music = new Music();
                long id = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media._ID));
                String title = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));
                String artist = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
                String album = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM));
                String path = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
                String fileName = cursor.getString
                        (cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME));
                music.setAlbum(album);
                music.setId(id);
                music.setTitle(title);
                File file = new File(FileUtils.getExternalStorageDirectory() + FileUtils.getDownloadLrcPath() + title+".lrc");
                if (file.exists()) {
                    music.setLrc(file.getPath());
                }
                music.setArtist(artist);
                music.setPath(path);
                music.setFileName(fileName);
                music.setFileSize(fileSize);
                music.setDuration(fileTime);
                music.setType(Music.TYPE_LOCAL);
                musicList.add(music);
            }
        }
    }

}
