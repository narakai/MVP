package com.example.leon.mvp.app;

import android.app.Application;

import com.example.leon.mvp.utils.CrashHandler;

/**
 * Created by leon on 16/4/7.
 */
public class BaseApplication extends Application {
    private static BaseApplication instance;

    public static BaseApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        CrashHandler.getInstance().init(instance);
    }
}
