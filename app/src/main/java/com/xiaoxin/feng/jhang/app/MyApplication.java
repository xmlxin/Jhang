package com.xiaoxin.feng.jhang.app;

import android.app.Application;
import android.content.Context;
import android.os.StrictMode;

/**
 * @author: xiaoxin
 * date: 2018/5/24
 * describe:
 * 修改内容:
 */

public class MyApplication extends Application {

    public static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();

        mContext = this;

        // android 7.0系统解决拍照的问题
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();
    }
}
