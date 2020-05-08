package com.xiao.awesome_jetpack.application;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

/**
 * Description:应用程序（基类）
 */
public class BaseApp extends Application {

    private static BaseApp mContext = null; //获取到主线程的上下文
    private static Handler mAppHandler = null; //获取到主线程的handler
    private static Looper mAppLooper = null; //获取到主线程的looper
    private static Thread mMainThead = null; //获取到主线程
    private static int mMainTheadId; //获取到主线程的id

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        mAppHandler = new Handler();
        mAppLooper = getMainLooper();
        mMainThead = Thread.currentThread();
        mMainTheadId = android.os.Process.myTid();//主線程id
    }

    public static BaseApp getApp() {
        return mContext;
    }

    public static Handler getAppHandler() {
        return mAppHandler;
    }

    public static Looper getAppLooper() {
        return mAppLooper;
    }

    public static Thread getMainThread() {
        return mMainThead;
    }

    public static int getMainThreadId() {
        return mMainTheadId;
    }
}
