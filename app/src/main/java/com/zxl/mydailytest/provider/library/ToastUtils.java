package com.zxl.mydailytest.provider.library;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by apple on 2019-10-23.
 * description:
 */
public class ToastUtils {
    private static Context mContext;

    public ToastUtils(){

    }

    public static void init(Context context){
        Log.e("provider", "init: 初始化了");
        mContext = context.getApplicationContext();
    }

    public static void show(String msg){
        if (mContext != null){
            Toast.makeText(mContext,msg,Toast.LENGTH_LONG).show();
        }
    }

}
