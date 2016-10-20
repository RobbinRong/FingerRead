package com.robbin.fingerread.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/10/20.
 * 主演
 */
public class MovieCelebrity {
    public List<List<Data>> data;

    @Override
    public String toString() {
        return "MovieCelebrity{" +
                "data=" + data +
                '}';
    }

    public class Data
    {
       public String avatar;
       public String cnm;
       public int cr;
       public String enm;
       public int id;
       public String roles;
       public String still;

        @Override
        public String toString() {
            return "Data{" +
                    "avatar='" + avatar + '\'' +
                    ", cnm='" + cnm + '\'' +
                    ", cr=" + cr +
                    ", enm='" + enm + '\'' +
                    ", id=" + id +
                    ", roles='" + roles + '\'' +
                    ", still='" + still + '\'' +
                    '}';
        }
    }
}
