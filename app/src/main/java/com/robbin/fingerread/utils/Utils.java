package com.robbin.fingerread.utils;

import android.content.Context;
import android.content.res.Configuration;

import com.robbin.fingerread.constant.Settings;

import java.util.Locale;

/**
 * Created by OneAPM on 2016/9/13.
 */
public class Utils {


    public static int getCurrentLanguage() {
        int lang = Settings.getInstance().getInt(Settings.LANGUAGE, -1);
        if (lang == -1) {
            String language = Locale.getDefault().getLanguage();
            String country = Locale.getDefault().getCountry();

            if (language.equalsIgnoreCase("zh")) {
                if (country.equalsIgnoreCase("CN")) {
                    lang = 1;
                } else {
                    lang = 2;
                }
            } else {
                lang = 0;
            }
        }
        return lang;
    }
    public static void changeLanguage(Context context, int lang) {
        String language = null;
        String country = null;

        switch (lang) {
            case 1:
                language = "zh";
                country = "CN";
                break;
            case 2:
                language = "zh";
                country = "HK";
                break;
            default:
                language = "en";
                country = "US";
                break;
        }

        Locale locale = new Locale(language, country);
        Configuration conf = context.getResources().getConfiguration();
        conf.locale = locale;
        context.getApplicationContext().getResources().updateConfiguration(conf, context.getResources().getDisplayMetrics());
    }

}
