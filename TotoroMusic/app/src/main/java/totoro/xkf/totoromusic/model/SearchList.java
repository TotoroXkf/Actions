package totoro.xkf.totoromusic.model;

import java.util.ArrayList;
import java.util.List;

public class SearchList {
    private List<Info> mInfoList = new ArrayList<>();

    public class Info {
        private String songName;
        private String songId;
        private String artistName;

        public String getSongName() {
            return songName;
        }

        public void setSongName(String songName) {
            this.songName = songName;
        }

        public String getSongId() {
            return songId;
        }

        public void setSongId(String songId) {
            this.songId = songId;
        }

        public String getArtistName() {
            return artistName;
        }

        public void setArtistName(String artistName) {
            this.artistName = artistName;
        }
    }

    public List<Info> getmInfoList() {
        return mInfoList;
    }

    public void setmInfoList(List<Info> mInfoList) {
        this.mInfoList = mInfoList;
    }
}
