package com.chuangsi.toeat.app;

import android.app.Application;

public class AppCtx extends Application{
    private static AppCtx appCtx;

    @Override
    public void onCreate() {
        super.onCreate();
        firstCreateInstance();
    }

    protected void firstCreateInstance() {
        if (appCtx == null)
            appCtx = this;

    }

    public static AppCtx getInstance(){
        return appCtx;
    }

}