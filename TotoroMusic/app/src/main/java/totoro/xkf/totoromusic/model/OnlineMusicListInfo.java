package totoro.xkf.totoromusic.model;

import java.util.List;

public class OnlineMusicListInfo {
    private List<Info> mInfoList;
    private String background1;
    private String background2;
    private String updateDate;
    private String typeName;
    private String comment;


    public String getBackground1() {
        return background1;
    }

    public void setBackground1(String background1) {
        this.background1 = background1;
    }

    public String getBackground2() {
        return background2;
    }

    public void setBackground2(String background2) {
        this.background2 = background2;
    }

    public class Info {
        private String authorId;
        private String author;
        private String title;
        private String cover;
        private String songId;

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

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getSongId() {
            return songId;
        }

        public void setSongId(String songId) {
            this.songId = songId;
        }

        public String getAuthorId() {
            return authorId;
        }

        public void setAuthorId(String authorId) {
            this.authorId = authorId;
        }
    }

    public List<Info> getInfoList() {
        return mInfoList;
    }

    public void setInfoList(List<Info> infoList) {
        this.mInfoList = infoList;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
