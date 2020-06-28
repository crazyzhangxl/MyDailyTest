package com.zxl.mydailytest;

import android.app.Application;
import android.content.Context;

import java.lang.ref.WeakReference;

/**
 * Created by apple on 2019-11-04.
 * description:
 */
public class MyApp extends Application {
    private static WeakReference<Context> mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = new WeakReference<>(this);
    }

    public static Context getContext() {
        return mContext.get();
    }
}
