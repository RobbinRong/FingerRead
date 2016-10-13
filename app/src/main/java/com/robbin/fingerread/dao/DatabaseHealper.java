package com.robbin.fingerread.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.robbin.fingerread.constant.DB;

/**
 * Created by OneAPM on 2016/8/31.
 */
public class DatabaseHealper extends SQLiteOpenHelper{

    private static  DatabaseHealper healper;
    public static  DatabaseHealper getInstance(Context context){
        if(healper==null){
            synchronized (DatabaseHealper.class){
                if(healper==null){
                    healper=new DatabaseHealper(context, DB.DB_NAME,null,DB.DB_VERSION);
                }
            }

        }
        return healper;
    }
    private  DatabaseHealper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DB.CREATE_TABLE_READ);
        db.execSQL(DB.CREATE_TABLE_COLLECT_DAILY);
        db.execSQL(DB.CREATE_TABLE_COLLECT_SCIENCE);
        db.execSQL(DB.CREATE_TABLE_COLLECT_WECHAT);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
