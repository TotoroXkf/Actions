package totoro.xkf.totoromusic.json;

import totoro.xkf.totoromusic.model.OnlinePlayMusic;

public class JsonForOnlinePlayMusic {
    private SonginfoBean songinfo;
    private BitrateBean bitrate;

    public SonginfoBean getSonginfo() {
        return songinfo;
    }

    public void setSonginfo(SonginfoBean songinfo) {
        this.songinfo = songinfo;
    }

    public BitrateBean getBitrate() {
        return bitrate;
    }

    public void setBitrate(BitrateBean bitrate) {
        this.bitrate = bitrate;
    }

    public static class SonginfoBean {
        private String album_title;
        private String author;
        private String title;
        private String lrclink;
        private String pic_big;
        private String song_id;

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

        public String getLrclink() {
            return lrclink;
        }

        public void setLrclink(String lrclink) {
            this.lrclink = lrclink;
        }

        public String getPic_big() {
            return pic_big;
        }

        public void setPic_big(String pic_big) {
            this.pic_big = pic_big;
        }

    }

    public static class BitrateBean {
        private long file_size;
        private int file_duration;
        private String file_link;

        public int getFile_duration() {
            return file_duration;
        }

        public void setFile_duration(int file_duration) {
            this.file_duration = file_duration;
        }

        public String getFile_link() {
            return file_link;
        }

        public void setFile_link(String file_link) {
            this.file_link = file_link;
        }
    }

    public OnlinePlayMusic changeToOnlinePlayMusic() {
        OnlinePlayMusic onlinePlayMusic = new OnlinePlayMusic();
        onlinePlayMusic.setId(Long.parseLong(getSonginfo().song_id));
        onlinePlayMusic.setAuthor(getSonginfo().getAuthor());
        onlinePlayMusic.setCoverUrl(getSonginfo().getPic_big());
        onlinePlayMusic.setFileNmae(getSonginfo().getTitle() + ".mp3");
        onlinePlayMusic.setTitle(getSonginfo().getTitle());
        onlinePlayMusic.setFileSize(bitrate.file_size);
        onlinePlayMusic.setLrcUrl(getSonginfo().getLrclink());
        onlinePlayMusic.setAlbum(getSonginfo().album_title);
        onlinePlayMusic.setDuration(getBitrate().getFile_duration());
        onlinePlayMusic.setPlayUrl(getBitrate().getFile_link());
        return onlinePlayMusic;
    }
}
