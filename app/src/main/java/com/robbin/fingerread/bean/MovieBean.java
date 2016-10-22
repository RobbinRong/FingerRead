package com.robbin.fingerread.bean;

import android.graphics.Movie;

import java.util.List;

/**
 * Created by Administrator on 2016/10/17.
 */
public class MovieBean {
    //public String control;
    public int status;
    public MovieBean.Data data;

    @Override
    public String toString() {
        return "MovieBean{" +
                "status=" + status +
                ", data=" + data +
                '}';
    }

    //public class Control{}
    public class Data{
        public  boolean hasNext;
        public List<Movie> movies;

        @Override
        public String toString() {
            return "Data{" +
                    "hasNext=" + hasNext +
                    ", movies=" + movies +
                    '}';
        }
    }
    public  class  Movie{
        public int cnms;
        public boolean late;
        public int sn ;
        public String showInfo;
        public String src;//产地
        public int preSale;
        public int pn;
        public String vd;
        public String dir;//导演
        public String star;//主演
        public String cat;//分类
        public int wish;//想看的人数
        private boolean threed;
        public String scm;//梗概
        public String showDate;
        public boolean imax ;
        public int snum;
        public String nm;//movie name
        public int dur ;
        public String img;
        public float sc;//评分
        public String ver;//eg.3D/IMAX 3D
        public String rt;//上映日期
        public String time;
        public int id;

        public boolean get3d() {
            return threed;
        }

        public void set3d(boolean threed) {
            this.threed = threed;
        }

        @Override
        public String toString() {
            return "Movie{" +
                    "cnms=" + cnms +
                    ", late=" + late +
                    ", sn=" + sn +
                    ", showInfo='" + showInfo + '\'' +
                    ", src='" + src + '\'' +
                    ", preSale=" + preSale +
                    ", pn=" + pn +
                    ", vd='" + vd + '\'' +
                    ", dir='" + dir + '\'' +
                    ", star='" + star + '\'' +
                    ", cat='" + cat + '\'' +
                    ", wish=" + wish +
                    ", threed=" + threed +
                    ", scm='" + scm + '\'' +
                    ", showDate='" + showDate + '\'' +
                    ", imax=" + imax +
                    ", snum=" + snum +
                    ", nm='" + nm + '\'' +
                    ", dur=" + dur +
                    ", img='" + img + '\'' +
                    ", sc=" + sc +
                    ", ver='" + ver + '\'' +
                    ", rt='" + rt + '\'' +
                    ", time='" + time + '\'' +
                    ", id='" + id + '\'' +
                    '}';
        }
    }
}
