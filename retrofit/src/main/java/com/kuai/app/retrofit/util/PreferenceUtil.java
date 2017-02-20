package com.kuai.app.retrofit.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 *  SharedPreference 工具类
 */

public class PreferenceUtil {

    public static SharedPreferences getSp(Context context, String spName){
        return context.getSharedPreferences(spName,Context.MODE_PRIVATE);
    }

    public static void write(Context context,String spName,String key, String value){
        getSp(context,spName).edit().putString(key,value).apply();
    }

    public static String read(Context context,String spName, String key){
        return getSp(context,spName).getString(key,"");
    }
}
