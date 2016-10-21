package com.robbin.fingerread.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/10/20.
 */

public class MovieCommonsZY {
    public List<Data> data;
    public Paging paging;

    @Override
    public String toString() {
        return "MovieCommonsZY{" +
                "data=" + data +
                ", paging=" + paging +
                '}';
    }

    public class Paging
    {
        public boolean hasMore;
        public int limit;
        public int offset;
        public int total;

        @Override
        public String toString() {
            return "Paging{" +
                    "hasMore=" + hasMore +
                    ", limit=" + limit +
                    ", offset=" + offset +
                    ", total=" + total +
                    '}';
        }
    }
    public class Data
    {
        public int approve;
        public boolean approved;
        public String authInfo;
        public String avatarurl;
        public String cityName;
        public String content;
        public long created;
        public boolean filmView;
        public int id;
        public boolean isMajor;
        public int juryLevel;
        public int movieId;
        public String nick;
        public String nickName;
        public int oppose;
        public boolean pro;
        public int reply;
        public double score;
        public int spoiler;
        public String startTime;
        public boolean supportComment;
        public boolean supportLike;
        public int sureViewed;
        public String time;
        public int userId;
        public int userLevel;
        public String vipInfo;
        public int vipType;

        @Override
        public String toString() {
            return "Data{" +
                    "approve=" + approve +
                    ", approved=" + approved +
                    ", authInfo='" + authInfo + '\'' +
                    ", avatarurl='" + avatarurl + '\'' +
                    ", cityName='" + cityName + '\'' +
                    ", content='" + content + '\'' +
                    ", created=" + created +
                    ", filmView=" + filmView +
                    ", id=" + id +
                    ", isMajor=" + isMajor +
                    ", juryLevel=" + juryLevel +
                    ", movieId=" + movieId +
                    ", nick='" + nick + '\'' +
                    ", nickName='" + nickName + '\'' +
                    ", oppose=" + oppose +
                    ", pro=" + pro +
                    ", reply=" + reply +
                    ", score=" + score +
                    ", spoiler=" + spoiler +
                    ", startTime='" + startTime + '\'' +
                    ", supportComment=" + supportComment +
                    ", supportLike=" + supportLike +
                    ", sureViewed=" + sureViewed +
                    ", time='" + time + '\'' +
                    ", userId=" + userId +
                    ", userLevel=" + userLevel +
                    ", vipInfo='" + vipInfo + '\'' +
                    ", vipType=" + vipType +
                    '}';
        }
    }
}
