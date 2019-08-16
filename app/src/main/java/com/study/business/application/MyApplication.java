package com.study.business.application;

import android.app.Application;

/**
 * 整个程序的入口 主要做初始化
 * created by Administrator on 2019/8/16
 */
public class MyApplication extends Application {

    public static MyApplication application=null;
    @Override
    public void onCreate() {
        super.onCreate();
        application=this;
    }
    public static MyApplication getInstance(){
        return application;
    }
}
