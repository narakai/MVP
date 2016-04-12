package com.example.leon.mvp.activity;

import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by leon on 16/4/7.
 */
public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        super.onCreate(savedInstanceState);
        /**
         * init LeakCanary watch memory leak in activity
         */
//        RefWatcher refWatcher = LifeApplication.getRefWatcher(this);
//        refWatcher.watch(this);
    }

    /**
     * init data
     */
    protected abstract void initData();

    /**
     * init UI views
     */
    protected abstract void initView();

    /**
     * init events
     */
    protected abstract void initEvent();

    protected void init() {
        initData();
        initView();
        initEvent();
    }
}
