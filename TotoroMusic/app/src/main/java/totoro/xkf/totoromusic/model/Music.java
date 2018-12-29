package totoro.xkf.totoromusic.model;

import android.graphics.Bitmap;

public class Music {
    public static final int TYPE_LOCAL = 0;
    public static final int TYPE_ONLINE = 1;
    private long id;
    private String lrc;
    private String title;
    private String artist;
    private String album;
    private long duration;
    private String path;
    private String fileName;
    private long fileSize;
    private String coverPath;
    private int type;
    private long downloadId;

    public String getCoverPath() {
        return coverPath;
    }

    public void setCoverPath(String coverPath) {
        this.coverPath = coverPath;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public String getLrc() {
        return lrc;
    }

    public void setLrc(String lrc) {
        this.lrc = lrc;
    }

    public long getDownloadId() {
        return downloadId;
    }

    public void setDownloadId(long downloadId) {
        this.downloadId = downloadId;
    }

    public String getTime() {
        long allTime = getDuration();
        allTime /= 1000;
        String minutes = "" + allTime / 60;
        String seconds = "" + allTime % 60;
        if (Integer.parseInt(seconds) < 10) {
            seconds = "0" + seconds;
        }
        String time = minutes + ":" + seconds;
        return time;
    }

    public String getSize() {
        float allSize = (float) getFileSize();
        allSize /= (1024 * 1024);
        allSize *= 100;
        int sizeOfMB = (int) allSize;
        String size = "" + sizeOfMB / 100 + "." + sizeOfMB % 100 + "MB";
        return size;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
