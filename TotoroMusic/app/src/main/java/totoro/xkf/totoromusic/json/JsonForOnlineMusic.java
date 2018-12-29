package totoro.xkf.totoromusic.json;

import java.util.ArrayList;
import java.util.List;

import totoro.xkf.totoromusic.model.Music;
import totoro.xkf.totoromusic.model.OnlineMusicListInfo;

public class JsonForOnlineMusic {

    private BillboardBean billboard;
    private List<SongListBean> song_list;

    public BillboardBean getBillboard() {
        return billboard;
    }

    public List<SongListBean> getSong_list() {
        return song_list;
    }

    public static class BillboardBean {

        private String update_date;
        private String name;
        private String comment;
        private String pic_s192;
        private String pic_s640;

        public String getUpdate_date() {
            return update_date;
        }

        public void setUpdate_date(String update_date) {
            this.update_date = update_date;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public String getPic_s192() {
            return pic_s192;
        }

        public void setPic_s192(String pic_s192) {
            this.pic_s192 = pic_s192;
        }

        public String getPic_s640() {
            return pic_s640;
        }

        public void setPic_s640(String pic_s640) {
            this.pic_s640 = pic_s640;
        }
    }

    public static class SongListBean {
        private String ting_uid;
        private String pic_big;
        private String song_id;
        private String title;
        private String author;

        public String getPic_big() {
            return pic_big;
        }

        public void setPic_big(String pic_big) {
            this.pic_big = pic_big;
        }

        public String getSong_id() {
            return song_id;
        }

        public void setSong_id(String song_id) {
            this.song_id = song_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getTing_uid() {
            return ting_uid;
        }

        public void setTing_uid(String ting_uid) {
            this.ting_uid = ting_uid;
        }
    }

    public OnlineMusicListInfo changeToOnlineMusicInfo() {
        OnlineMusicListInfo onlineMusicListInfo = new OnlineMusicListInfo();
        List<OnlineMusicListInfo.Info> infoList = new ArrayList<>();
        for (SongListBean bean : song_list) {
            OnlineMusicListInfo.Info info = onlineMusicListInfo.new Info();
            info.setAuthor(bean.getAuthor());
            info.setCover(bean.getPic_big());
            info.setSongId(bean.getSong_id());
            info.setTitle(bean.getTitle());
            info.setAuthorId(bean.ting_uid);
            infoList.add(info);
        }
        onlineMusicListInfo.setInfoList(infoList);
        onlineMusicListInfo.setBackground1(billboard.getPic_s192());
        onlineMusicListInfo.setBackground2(billboard.getPic_s640());
        onlineMusicListInfo.setTypeName(billboard.name);
        onlineMusicListInfo.setUpdateDate(billboard.update_date);
        onlineMusicListInfo.setComment(billboard.comment);
        return onlineMusicListInfo;
    }
}
