package com.robbin.fingerread.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.robbin.fingerread.constant.DB;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by OneAPM on 2016/8/31.
 */
public class DailyDao {
    DatabaseHealper healper;


    public DailyDao(Context context){
        healper=DatabaseHealper.getInstance(context);
    }
    public void insertReadNew(String s) {
        SQLiteDatabase writableDatabase = healper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(DB.COLUMN_ID,s);
        writableDatabase.insert(DB.TABLE_READ,null,values);
        writableDatabase.close();
    }
    public List<String> getAllRead(){
        List<String> list = new ArrayList<>();
        SQLiteDatabase readableDatabase = healper.getReadableDatabase();
        String sql="select * from "+DB.TABLE_READ;
        Cursor cursor = null;
        try {
            cursor = readableDatabase.rawQuery(sql,null);
            if (cursor.moveToFirst()) {
                do {
                    String id = cursor.getString(0);
                    list.add(id);
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
}
