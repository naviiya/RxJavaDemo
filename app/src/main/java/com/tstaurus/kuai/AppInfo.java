package com.tstaurus.kuai;

import android.graphics.drawable.Drawable;

/**
 * Created by tstau on 2017/2/7.
 */

public class AppInfo {

    private String appName;

    private Drawable icon;

    public AppInfo(String appName, Drawable icon) {
        this.appName = appName;
        this.icon = icon;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }
}
