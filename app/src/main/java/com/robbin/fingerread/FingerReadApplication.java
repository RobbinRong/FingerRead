package com.robbin.fingerread;

import android.app.Application;
import android.content.Context;

/**
 * Created by OneAPM on 2016/8/23.
 */
public class FingerReadApplication extends Application {
    public static Context AppContext = null;
    @Override
    public void onCreate() {
        super.onCreate();
        AppContext=getApplicationContext();
   }
}
