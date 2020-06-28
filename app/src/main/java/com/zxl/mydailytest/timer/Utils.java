package com.zxl.mydailytest.timer;

import android.os.Looper;

/**
 * @author crazyZhangxl on 2018/11/22.
 * Describe:
 */
public class Utils {

    public static void enforceMainLooper() {
        if (Looper.getMainLooper() != Looper.myLooper()) {
            throw new IllegalAccessError("May only call from main thread.");
        }
    }

}

