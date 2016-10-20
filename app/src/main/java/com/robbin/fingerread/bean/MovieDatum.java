package com.robbin.fingerread.bean;

/**
 * Created by Administrator on 2016/10/20.
 */

import java.util.List;

/**
 * 影片资料
 */

public class MovieDatum {

    public List<Data> data;

    @Override
    public String toString() {
        return "MovieDatum{" +
                "data=" + data +
                '}';
    }

    public class Data
    {
        public String content;
        public String img;
        public String name;
        public String title;
        public String url;

        @Override
        public String toString() {
            return "Data{" +
                    "content='" + content + '\'' +
                    ", img='" + img + '\'' +
                    ", name='" + name + '\'' +
                    ", title='" + title + '\'' +
                    ", url='" + url + '\'' +
                    '}';
        }
    }

}
