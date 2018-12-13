package com.rxjava.mvp.common.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.alibaba.android.arouter.BuildConfig;
import com.alibaba.android.arouter.launcher.ARouter;
import com.hjq.toast.ToastUtils;
import com.rxjava.mvp.common.network.CookieReadInterceptor;
import com.rxjava.mvp.common.network.CookiesSaveInterceptor;
import com.rxjava.mvp.common.uitl.InterceptorUtil;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;


public class MyApplication extends Application {

    public static final int TIMEOUT = 60;
    private static OkHttpClient mOkHttpClient;
    public static MyApplication myApp;
    @Override
    public void onCreate() {
        super.onCreate();
        // 初始化吐司工具类
        ToastUtils.init(this);
        myApp=this;
        if (BuildConfig.DEBUG) {
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this);
    }

    /**
     * 全局httpclient
     *
     * @return
     */
    public static OkHttpClient initOKHttp() {
        if (mOkHttpClient == null) {
            mOkHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(TIMEOUT, TimeUnit.SECONDS)//设置连接超时时间
                    .readTimeout(TIMEOUT, TimeUnit.SECONDS)//设置读取超时时间
                    .writeTimeout(TIMEOUT, TimeUnit.SECONDS)//设置写入超时时间
                    .addInterceptor(InterceptorUtil.LogInterceptor())//添加日志拦截器
                    //cookie

                    .build();
        }
        return mOkHttpClient;
    }



}
