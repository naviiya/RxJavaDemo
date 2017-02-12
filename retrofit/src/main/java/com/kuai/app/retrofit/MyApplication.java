package com.kuai.app.retrofit;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.multidex.MultiDexApplication;

/**
 * application 实例
 */
public class MyApplication extends MultiDexApplication{


    @SuppressLint("StaticFieldLeak")
    private static Context mApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
    }

    public static Context getCtx(){
        return mApplication;
    }

}
