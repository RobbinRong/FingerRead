package com.robbin.fingerread.utils;

import android.content.Context;

/**
 * Created by OneAPM on 2016/8/31.
 */
public class SPUtil {
    private static  String FINGERREAD="fingerread";
    public static void toSp(Context context,String key,String value){
        context.getSharedPreferences(FINGERREAD,Context.MODE_PRIVATE).edit().putString(key,value).commit();
    }

}
