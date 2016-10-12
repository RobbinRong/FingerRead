package com.robbin.fingerread.bean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Administrator on 2016/10/12.
 */
public class WechatArticalBean implements Serializable {
    public String showapi_res_code;
    public String showapi_res_error;
    public Body showapi_res_body;

    @Override
    public String toString() {
        return "WechatArticalBean{" +
                "showapi_res_code='" + showapi_res_code + '\'' +
                ", showapi_res_error='" + showapi_res_error + '\'' +
                ", showapi_res_body=" + showapi_res_body +
                '}';
    }

    public class Body implements Serializable{
        public String ret_code;
        public PageBean pagebean;

        @Override
        public String toString() {
            return "Body{" +
                    "ret_code='" + ret_code + '\'' +
                    ", pagebean=" + pagebean +
                    '}';
        }
    }

    public class PageBean implements Serializable{
        public String allNum;
        public String allPages;
        public ArrayList<Content> contentlist;
        public String currentPage;
        public String maxResult;

        @Override
        public String toString() {
            return "PageBean{" +
                    "allNum='" + allNum + '\'' +
                    ", allPages='" + allPages + '\'' +
                    ", contentlist=" + contentlist +
                    ", currentPage='" + currentPage + '\'' +
                    ", maxResult='" + maxResult + '\'' +
                    '}';
        }
    }

    public class Content implements Serializable{
        public String contentImg;
        public String date;
        public String id;
        public String title;
        public String typeId;
        public String typeName;
        public String url;
        public String userLogo;
        public String userLogo_code;
        public String userName;

        @Override
        public String toString() {
            return "Content{" +
                    "contentImg='" + contentImg + '\'' +
                    ", date='" + date + '\'' +
                    ", id='" + id + '\'' +
                    ", title='" + title + '\'' +
                    ", typeId='" + typeId + '\'' +
                    ", typeName='" + typeName + '\'' +
                    ", url='" + url + '\'' +
                    ", userLogo='" + userLogo + '\'' +
                    ", userLogo_code='" + userLogo_code + '\'' +
                    ", userName='" + userName + '\'' +
                    '}';
        }
    }
}
