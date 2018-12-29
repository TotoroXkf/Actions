package totoro.xkf.totoromusic.json;

import java.util.ArrayList;
import java.util.List;

import totoro.xkf.totoromusic.model.SearchList;
import totoro.xkf.totoromusic.util.LogUtils;

public class JsonForSearchList {
    private java.util.List<SongBean> song;
    private String error_message;

    public static class SongBean {

        private String songname;
        private String songid;
        private String artistname;

        public String getSongname() {
            return songname;
        }

        public void setSongname(String songname) {
            this.songname = songname;
        }

        public String getSongid() {
            return songid;
        }

        public void setSongid(String songid) {
            this.songid = songid;
        }

        public String getArtistname() {
            return artistname;
        }

        public void setArtistname(String artistname) {
            this.artistname = artistname;
        }
    }

    public SearchList changeToSearchList() {
        if (error_message != null && error_message.equals("failed")) {
            return null;
        }
        SearchList searchList = new SearchList();
        List<SearchList.Info> list = new ArrayList<>();
        for (SongBean bean : song) {
            SearchList.Info info = searchList.new Info();
            info.setSongId(bean.getSongid());
            info.setArtistName(bean.getArtistname());
            info.setSongName(bean.getSongname());
            list.add(info);
        }
        if (list.isEmpty()) {
            return null;
        }
        searchList.setmInfoList(list);
        return searchList;
    }
}
