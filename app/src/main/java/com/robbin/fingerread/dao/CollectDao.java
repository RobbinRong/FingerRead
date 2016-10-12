package com.robbin.fingerread.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.robbin.fingerread.bean.ArticleBean;
import com.robbin.fingerread.bean.NewsDetail;
import com.robbin.fingerread.constant.DB;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by OneAPM on 2016/9/12.
 */
public class CollectDao {
    DatabaseHealper healper;

    public CollectDao(Context context) {
        healper=DatabaseHealper.getInstance(context);
    }
    public void  insertDaily(NewsDetail detail){
        SQLiteDatabase database = healper.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(DB.COLLECT_DAILY_ID,detail.getId());
        contentValues.put(DB.COLLECT_DAILY_TITLE,detail.getTitle());
        contentValues.put(DB.COLLECT_DAILY_IMAGE,detail.getImage());
        contentValues.put(DB.COLLECT_DAILY_IMAGESOURCE,detail.getImage_source());
        contentValues.put(DB.COLLECT_DAILY_BODY,detail.getBody());
        long insert = database.insert(DB.TABLE_COLLECT_DAILY, null, contentValues);
        Log.e("insert", "insertDaily: "+insert);
        database.close();
    }
    public void  insertScience(ArticleBean articleBean){
        SQLiteDatabase database = healper.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(DB.COLLECT_SCIENCE_TITLE,articleBean.getTitle());
        contentValues.put(DB.COLLECT_SCIENCE_HOT,articleBean.getReplies_count());
        contentValues.put(DB.COLLECT_SCIENCE_URL,articleBean.getUrl());
        contentValues.put(DB.COLLECT_SCIENCE_IMAGE,articleBean.getImage_info().getUrl());
        database.insert(DB.TABLE_COLLECT_SCIENCE,null,contentValues);
        database.close();
    }
    public List<NewsDetail> getAllDaily(){
        List<NewsDetail> list = new ArrayList<>();
        SQLiteDatabase readableDatabase = healper.getReadableDatabase();
        String sql="select * from "+DB.TABLE_COLLECT_DAILY;
        Cursor cursor = null;
        try {
            cursor = readableDatabase.rawQuery(sql,null);
            if (cursor.moveToFirst()) {
                do {
                    NewsDetail detail=new NewsDetail();
                    detail.setId(cursor.getInt(0));
                    detail.setTitle(cursor.getString(1));
                    detail.setImage(cursor.getString(2));
                    detail.setImage_source(cursor.getString(3));
                    detail.setBody(cursor.getString(4));
                    list.add(detail);
                } while (cursor.moveToNext());
            }
        }catch (Exception e){
            Log.e("rjb",e.getMessage());
        }
        finally {
            if(cursor!=null){
                cursor.close();
            }
        }
        return list;
    }
    public List<ArticleBean> getAllScience(){
        List<ArticleBean> list = new ArrayList<>();
        SQLiteDatabase readableDatabase = healper.getReadableDatabase();
        String sql="select * from "+DB.TABLE_COLLECT_SCIENCE;
        Cursor cursor = null;
        try {
            cursor = readableDatabase.rawQuery(sql,null);
            if (cursor.moveToFirst()) {
                do {
                    ArticleBean articleBean=new ArticleBean();
                    articleBean.setTitle(cursor.getString(0));
                    articleBean.setReplies_count(cursor.getInt(1));
                    articleBean.setUrl(cursor.getString(2));
                    articleBean.getImage_info().setUrl(cursor.getString(3));
                    list.add(articleBean);
                } while (cursor.moveToNext());
            }
        }catch (Exception e){
            Log.e("rjb",e.getMessage());
        }
        finally {
            if(cursor!=null){
                cursor.close();
            }
        }
        return list;
    }

    public void  deleteDaily(NewsDetail detail){
        SQLiteDatabase database = healper.getWritableDatabase();
        database.execSQL("delete  from "+DB.TABLE_COLLECT_DAILY+" where "+DB.COLLECT_DAILY_ID+"="+detail.getId());
        database.close();
    }
    public void  deleteScience(ArticleBean articleBean){
        SQLiteDatabase database = healper.getWritableDatabase();
        database.execSQL("delete  from "+DB.TABLE_COLLECT_SCIENCE+" where "+DB.COLLECT_SCIENCE_URL+"='"+articleBean.getUrl()+"'");
        database.close();
    }
}
