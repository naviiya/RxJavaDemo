package com.kuai.app.retrofit;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.multidex.MultiDexApplication;

import com.squareup.leakcanary.LeakCanary;

/**
 * application 实例
 */
public class MyApplication extends MultiDexApplication{


    @SuppressLint("StaticFieldLeak")
    private static Context mApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
        // Normal app init code...
        mApplication = this;
    }

    public static Context getCtx(){
        return mApplication;
    }

}
