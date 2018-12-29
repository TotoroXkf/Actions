package totoro.xkf.totoromusic.util;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.support.v4.util.LruCache;

import java.io.File;

import totoro.xkf.totoromusic.R;
import totoro.xkf.totoromusic.model.Music;

public class CoverLoader {
    private static final String KEY_NULL = "null";
    private static Context sContext;
    private static LruCache<String, Bitmap> mThumbNail;

    public static void init(Context context) {
        sContext = context.getApplicationContext();
        long cacheSize = Runtime.getRuntime().maxMemory() / 1024;
        mThumbNail = new LruCache<String, Bitmap>((int) cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getByteCount() / 1024;
            }
        };
    }

    public static Bitmap createLocalCoverBitmap(String path, String title) {
        Bitmap bitmap;
        if (mThumbNail.get(path) == null) {
            bitmap = loadBitmap(path, title, ScreenUtils.getScreenWidth() / 10);
            if (bitmap == null) {
                if (mThumbNail.get(KEY_NULL) == null) {
                    bitmap = BitmapFactory.decodeResource(sContext.getResources(), R.mipmap.default_cover);
                    mThumbNail.put(KEY_NULL, bitmap);
                } else {
                    bitmap = mThumbNail.get(KEY_NULL);
                }
            } else {
                mThumbNail.put(path, bitmap);
            }
        } else {
            bitmap = mThumbNail.get(path);
        }
        return bitmap;
    }

    private static Bitmap loadBitmap(String path, String title, int length) {
        Bitmap bitmap = null;
        //两种情况找略缩图
        //如果本地文件直接被扫描出来 MediaMetadataRetriever直接加载它
        //如果是自己下载的，在自己指定的文件夹下面的话，就到自己的文件夹加载
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        File file = new File(FileUtils.getExternalStorageDirectory() + FileUtils.getDownloadCoverPath() + title + ".jpg");
        try {
            retriever.setDataSource(path);
            byte[] embedPic = retriever.getEmbeddedPicture();
            if (embedPic != null && embedPic.length != 0) {
                BitmapFactory.Options options = getBitmapOption(null, embedPic, length);
                bitmap = BitmapFactory.decodeByteArray(embedPic, 0, embedPic.length, options);
            } else if (file.exists()) {
                BitmapFactory.Options options = getBitmapOption(file, null, length);
                bitmap = BitmapFactory.decodeFile(file.getPath(), options);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                retriever.release();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return bitmap;
    }

    private static BitmapFactory.Options getBitmapOption(File file, byte[] embedPic, int length) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        // 仅获取大小
        options.inJustDecodeBounds = true;
        //压缩内存，不写这个容易溢出
        if (file != null) {
            BitmapFactory.decodeFile(file.getPath(), options);
        } else {
            BitmapFactory.decodeByteArray(embedPic, 0, embedPic.length, options);
        }
        int maxLength = Math.max(options.outWidth, options.outHeight);
        // 压缩尺寸，避免卡顿
        int inSampleSize = maxLength / length;
        if (inSampleSize < 1) {
            inSampleSize = 1;
        }
        options.inSampleSize = inSampleSize;
        // 获取bitmap
        options.inJustDecodeBounds = false;
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        return options;
    }

    public static Bitmap createPlayPageBackground(Music music) {
        Bitmap bitmap = loadBitmap(music.getPath(), music.getTitle(), ScreenUtils.getScreenWidth() / 5);
        if (bitmap == null) {
            bitmap = BitmapFactory.decodeResource(sContext.getResources(), R.mipmap.play_page_default_bg);
            return bitmap;
        }
        return bitmap;
    }

    public static Bitmap createPlayPageCover(Music music) {
        Bitmap bitmap = loadBitmap(music.getPath(), music.getTitle(), ScreenUtils.getScreenWidth() / 5);
        if (bitmap == null) {
            bitmap = BitmapFactory.decodeResource(sContext.getResources(), R.mipmap.play_page_default_cover);
        }
        return bitmap;
    }
}

