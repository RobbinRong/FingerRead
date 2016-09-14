package com.robbin.fingerread.constant;

import android.content.Context;
import android.content.SharedPreferences;

import com.robbin.fingerread.FingerReadApplication;

/**
 * Created by OneAPM on 2016/9/13.
 */
public class Settings {
    public static boolean isNightMode = false;
    public static final String NIGHT_MODE = "night_mode";
    private SharedPreferences mPrefs;
    public static final String SETTINGNAME = "settings";
    public static final String LANGUAGE = "language";
    public static  boolean needRecreate = false;

    private static  Settings settings=new Settings(FingerReadApplication.AppContext);
    private  Settings(Context context){
        mPrefs = context.getSharedPreferences(SETTINGNAME, Context.MODE_PRIVATE);
    }
    public static Settings getInstance(){
        return  settings;
    }
    public boolean getBoolean(String key, boolean def) {
        return mPrefs.getBoolean(key, def);
    }

    public Settings putBoolean(String key, boolean value) {
        mPrefs.edit().putBoolean(key, value).commit();
        return this;
    }
    public Settings putInt(String key, int value) {
        mPrefs.edit().putInt(key, value).commit();
        return this;
    }

    public int getInt(String key, int defValue) {
        return mPrefs.getInt(key, defValue);
    }

}
