package com.zxl.mydailytest.toast;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.zxl.mydailytest.MyApplication;
import com.zxl.mydailytest.R;


/**
 * Created by apple on 2020-04-08.
 * 经过压力测试且无问题
 */
public class ToastUtils {
    private static Toast mToast;

    public static void showShortText(String text){
        showText(text,Toast.LENGTH_SHORT);
    }

    public static void showLongText(String text){
        showText(text,Toast.LENGTH_LONG);
    }

    public static void showText(String text,int duration){
        if (TextUtils.isEmpty(text)){
            return;
        }
        TextView tvText;
        if (mToast == null){
            mToast = Toast.makeText(MyApplication.getContext(), "", Toast.LENGTH_SHORT);
            final View toastLayout = ((LayoutInflater) MyApplication.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                    .inflate(R.layout.toast_layout, null);
            tvText = toastLayout.findViewById(R.id.toast_text);
            mToast.setView(toastLayout);
        }else {
            mToast.cancel();
            mToast = Toast.makeText(MyApplication.getContext(), "", Toast.LENGTH_SHORT);
            final View toastLayout = ((LayoutInflater) MyApplication.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                    .inflate(R.layout.toast_layout, null);
            tvText = toastLayout.findViewById(R.id.toast_text);
            mToast.setView(toastLayout);
        }
        mToast.setDuration(duration);
        tvText.setText(text);
        mToast.show();
    }

    // 普通方法.....
    public static void showToast(String msg, int duration) {

        mToast = Toast.makeText(MyApplication.getContext(), "", duration);
        mToast.setText(msg);
        mToast.show();
    }
}
