package com.robbin.fingerread.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/10/18.
 */
public class MovieDetail implements Serializable{
    public int status;
    public MovieDetail.Data data;

    @Override
    public String toString() {
        return "MovieBean{" +
                "status=" + status +
                ", data=" + data +
                '}';
    }

    //public class Control{}
    public class Data{
        public MovieDetailModel MovieDetailModel;
        public CommentResponseModel CommentResponseModel;

        @Override
        public String toString() {
            return "Data{" +
                    "MovieDetailModel=" + MovieDetailModel +
                    ", CommentResponseModel=" + CommentResponseModel +
                    '}';
        }
    }

    public  class  MovieDetailModel{
        public String cat;
        public int dealsum;
        public  String dir;
        public  String dra;
        public  int   dur;
        public  int id;
        public boolean imax;
        public  String img;
        public  boolean isShowing;
        public  boolean late;
        public  int mk;
        public String nm;
        public List<String> photos;
        public  int pn;
        public  int preSale;
        public String rt;
        public  float sc;
        public  String scm;
        public boolean showSnum;
        public int snum;
        public  String src;
        public String star;
        public String vd;
        public String ver;
        public int vnum;
        public  int wish;
        public  int wishst;

        @Override
        public String toString() {
            return "MovieDetailModel{" +
                    "cat='" + cat + '\'' +
                    ", dealsum=" + dealsum +
                    ", dir='" + dir + '\'' +
                    ", dra='" + dra + '\'' +
                    ", dur=" + dur +
                    ", id=" + id +
                    ", imax=" + imax +
                    ", img='" + img + '\'' +
                    ", isShowing=" + isShowing +
                    ", late=" + late +
                    ", mk=" + mk +
                    ", nm='" + nm + '\'' +
                    ", photos=" + photos +
                    ", pn=" + pn +
                    ", preSale=" + preSale +
                    ", rt='" + rt + '\'' +
                    ", sc=" + sc +
                    ", scm='" + scm + '\'' +
                    ", showSnum=" + showSnum +
                    ", snum=" + snum +
                    ", src='" + src + '\'' +
                    ", star='" + star + '\'' +
                    ", vd='" + vd + '\'' +
                    ", ver='" + ver + '\'' +
                    ", vnum=" + vnum +
                    ", wish=" + wish +
                    ", wishst=" + wishst +
                    '}';
        }
    }
    public class CommentResponseModel{
        public List<Cmt> cmts;
        public  String total;
        public boolean hasNext;

        @Override
        public String toString() {
            return "CommentResponseModel{" +
                    "cmts=" + cmts +
                    ", total='" + total + '\'' +
                    ", hasNext=" + hasNext +
                    '}';
        }
    }
    public  class  Cmt{
        public  String vipInfo;
        public String nickName;
        public float score;
        public long userId;
        public  String time;
        public  int reply;
        public  String avatarurl;
        public int approve;
        public  int oppose;
        public  String nick;
        public long id;
        public String content;

        @Override
        public String toString() {
            return "Cmt{" +
                    "vipInfo='" + vipInfo + '\'' +
                    ", nickName='" + nickName + '\'' +
                    ", score=" + score +
                    ", userId=" + userId +
                    ", time='" + time + '\'' +
                    ", reply=" + reply +
                    ", avatarurl='" + avatarurl + '\'' +
                    ", approve=" + approve +
                    ", oppose=" + oppose +
                    ", nick='" + nick + '\'' +
                    ", id=" + id +
                    ", content='" + content + '\'' +
                    '}';
        }
    }
}
