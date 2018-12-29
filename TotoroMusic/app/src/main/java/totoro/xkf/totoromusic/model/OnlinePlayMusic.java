package totoro.xkf.totoromusic.model;

public class OnlinePlayMusic {
    private long id;
    private String playUrl;
    private int duration;
    private String album;
    private String fileName;
    private long fileSize;
    private String lrcUrl;
    private String author;
    private String title;
    private String coverUrl;

    public String getPlayUrl() {
        return playUrl;
    }

    public void setPlayUrl(String playUrl) {
        this.playUrl = playUrl;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getLrcUrl() {
        return lrcUrl;
    }

    public void setLrcUrl(String lrcUrl) {
        this.lrcUrl = lrcUrl;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getFileNmae() {
        return fileName;
    }

    public void setFileNmae(String fileNmae) {
        this.fileName = fileNmae;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Music changeToMusic() {
        Music music = new Music();
        music.setId(id);
        music.setType(Music.TYPE_ONLINE);
        music.setTitle(getTitle());
        music.setFileSize(getFileSize());
        music.setFileName(getFileNmae());
        music.setArtist(getAuthor());
        music.setAlbum(getAlbum());
        music.setCoverPath(getCoverUrl());
        music.setPath(getPlayUrl());
        music.setDuration(getDuration() * 1000);
        music.setLrc(getLrcUrl());
        return music;
    }
}
