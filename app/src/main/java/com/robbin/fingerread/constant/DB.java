package com.robbin.fingerread.constant;

/**
 * Created by OneAPM on 2016/8/31.
 */
public class DB {
    public static final String DB_NAME = "purezhihu";
    public static final int DB_VERSION = 1;

    public static final String TABLE_READ = "read";
    public static final String TABLE_COLLECT_DAILY = "collectdaily";
    public static final String TABLE_COLLECT_SCIENCE = "collectscience";
    public static final String TABLE_COLLECT_WECHAT = "collectwechat";
    public static final String COLUMN_ID = "newid";

    public static final String COLLECT_DAILY_ID = "collectdailyid";
    public static final String COLLECT_DAILY_BODY = "collectdailybody";
    public static final String COLLECT_DAILY_TITLE = "collectdailytitle";
    public static final String COLLECT_DAILY_IMAGE = "collectdailyimage";
    public static final String COLLECT_DAILY_IMAGESOURCE = "collectdailyimagesource";

    public static final String COLLECT_SCIENCE_TITLE = "collectsciencetitle";
    public static final String COLLECT_SCIENCE_HOT = "collectsciencehot";
    public static final String COLLECT_SCIENCE_URL = "collectscienceurl";
    public static final String COLLECT_SCIENCE_IMAGE = "collectscienceimage";

    public static final String COLLECT_WECHAT_ID = "collectwechatid";
    public static final String COLLECT_WECHAT_TITLE = "collectwechattitle";
    public static final String COLLECT_WECHAT_DATE = "collectwechatdate";
    public static final String COLLECT_WECHAT_CONTENTIMG = "collectwechatcontentimg";
    public static final String COLLECT_WECHAT_TYPEID = "collectwechattypeid";
    public static final String COLLECT_WECHAT_TYPENAME = "collectwechattypename";
    public static final String COLLECT_WECHAT_URL = "collectwechaturl";
    public static final String COLLECT_WECHAT_USERLOGO = "collectwechatuserlogo";
    public static final String COLLECT_WECHAT_USERLOGOCODE = "collectwechatuserlogocode";
    public static final String COLLECT_WECHAT_USERNAME = "collectwechatusername";


    public static final String CREATE_TABLE_READ = "create table " + TABLE_READ + "(" + COLUMN_ID + " text)";
    public static final String CREATE_TABLE_COLLECT_DAILY = "create table " + TABLE_COLLECT_DAILY + "(" + COLLECT_DAILY_ID +
            " text,"+COLLECT_DAILY_TITLE+" varchar(50),"+COLLECT_DAILY_IMAGE+" varchar(100),"+COLLECT_DAILY_IMAGESOURCE+" varchar(20),"+COLLECT_DAILY_BODY+" text)";
    public static final String CREATE_TABLE_COLLECT_SCIENCE = "create table " + TABLE_COLLECT_SCIENCE + "(" + COLLECT_SCIENCE_TITLE +
            " varchar(50),"+COLLECT_SCIENCE_HOT+" int,"+COLLECT_SCIENCE_URL+" varchar(100),"+COLLECT_SCIENCE_IMAGE+" varchar(100))";

    public static final String CREATE_TABLE_COLLECT_WECHAT ="create table "+TABLE_COLLECT_WECHAT+" ("+COLLECT_WECHAT_ID+" varchar(50), "
            +COLLECT_WECHAT_TITLE+" varchat(50),"+COLLECT_WECHAT_DATE+" varchat(50),"+COLLECT_WECHAT_CONTENTIMG+" varchat(50),"
            +COLLECT_WECHAT_TYPEID+" varchat(50),"+COLLECT_WECHAT_TYPENAME+" varchat(50),"+COLLECT_WECHAT_URL+" varchat(50),"
            +COLLECT_WECHAT_USERLOGO+" varchat(50),"+COLLECT_WECHAT_USERLOGOCODE+" varchat(50),"+COLLECT_WECHAT_USERNAME+" varchat(50)"
           +" )" ;
}
