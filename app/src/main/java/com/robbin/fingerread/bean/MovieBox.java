package com.robbin.fingerread.bean;

/**
 * Created by Administrator on 2016/10/20.
    票房
 */


public class MovieBox {

    public boolean globalRelease;
    public Mbox mbox;
    public String url;

    @Override
    public String toString() {
        return "MovieBox{" +
                "globalRelease=" + globalRelease +
                ", mbox=" + mbox +
                ", url='" + url + '\'' +
                '}';
    }

    public class Mbox
    {
        public int firstWeekBox;
        public int firstWeekOverSeaBox;
        public int lastDayRank;
        public int sumBox;
        public int sumOverSeaBox;

        @Override
        public String toString() {
            return "Mbox{" +
                    "firstWeekBox=" + firstWeekBox +
                    ", firstWeekOverSeaBox=" + firstWeekOverSeaBox +
                    ", lastDayRank=" + lastDayRank +
                    ", sumBox=" + sumBox +
                    ", sumOverSeaBox=" + sumOverSeaBox +
                    '}';
        }
    }

}
