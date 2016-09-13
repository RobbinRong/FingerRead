package com.robbin.fingerread.constant;

/**
 * Created by OneAPM on 2016/8/31.
 */
public class DB {
    public static final String DB_NAME = "purezhihu";
    public static final int DB_VERSION = 1;

    public static final String TABLE_READ = "read";
    public static final String TABLE_COLLECT_DAILY = "collectdaily";
    public static final String COLUMN_ID = "newid";

    public static final String COLLECT_DAILY_ID = "collectdailyid";
    public static final String COLLECT_DAILY_BODY = "collectdailybody";
    public static final String COLLECT_DAILY_TITLE = "collectdailytitle";
    public static final String COLLECT_DAILY_IMAGE = "collectdailyimage";
    public static final String COLLECT_DAILY_IMAGESOURCE = "collectdailyimagesource";
    public static final String CREATE_TABLE_READ = "create table " + TABLE_READ + "(" + COLUMN_ID + " text)";
    public static final String CREATE_TABLE_COLLECT_DAILY = "create table " + TABLE_COLLECT_DAILY + "(" + COLLECT_DAILY_ID +
            " text,"+COLLECT_DAILY_TITLE+" varchar(50),"+COLLECT_DAILY_IMAGE+" varchar(100),"+COLLECT_DAILY_IMAGESOURCE+" varchar(20),"+COLLECT_DAILY_BODY+" text)";

}
