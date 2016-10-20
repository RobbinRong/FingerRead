package com.robbin.fingerread.bean;

/**
 * Created by Administrator on 2016/10/20.
 * 主创说
 */
import java.util.List;


public class MovieMajor
{
    public Data data;

    @Override
    public String toString() {
        return "MovieMajor{" +
                "data=" + data +
                '}';
    }

    public class Major
    {
        public int approve;
        public boolean approved;
        public String authInfo;
        public String avatarurl;
        public String cityName;
        public String content;
        public boolean filmView;
        public int gender;
        public int id;
        public boolean isMajor;
        public int juryLevel;
        public int movieId;
        public String nick;
        public String nickName;
        public int oppose;
        public boolean pro;
        public int reply;
        public int score;
        public int spoiler;
        public String startTime;
        public boolean supportComment;
        public boolean supportLike;
        public int sureViewed;
        public TagList tagList;
        public String time;
        public int userId;
        public int userLevel;
        public String vipInfo;
        public int vipType;

        @Override
        public String toString() {
            return "Major{" +
                    "approve=" + approve +
                    ", approved=" + approved +
                    ", authInfo='" + authInfo + '\'' +
                    ", avatarurl='" + avatarurl + '\'' +
                    ", cityName='" + cityName + '\'' +
                    ", content='" + content + '\'' +
                    ", filmView=" + filmView +
                    ", gender=" + gender +
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
                    ", tagList=" + tagList +
                    ", time='" + time + '\'' +
                    ", userId=" + userId +
                    ", userLevel=" + userLevel +
                    ", vipInfo='" + vipInfo + '\'' +
                    ", vipType=" + vipType +
                    '}';
        }
    }


    public class Data
    {
        public List<Major> major;
        public int total;

        @Override
        public String toString() {
            return "Data{" +
                    "major=" + major +
                    ", total=" + total +
                    '}';
        }
    }
    public class TagList {
        public List<Fixed> fixed;

        @Override
        public String toString() {
            return "TagList{" +
                    "fixed=" + fixed +
                    '}';
        }
    }
    public class Fixed
    {
        public int id;
        public String name;

        @Override
        public String toString() {
            return "Fixed{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }


}



