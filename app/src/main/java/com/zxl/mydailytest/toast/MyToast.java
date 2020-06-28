package com.zxl.mydailytest.toast;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.zxl.mydailytest.R;


/**
 * Created by apple on 2019-09-05.
 * description: 自定义toast可修改背景色
 */
public class MyToast {
    public static final int LENGTH_SHORT = Toast.LENGTH_SHORT;

    private Toast mToast;
    private TextView tvText;

    public MyToast(Context context) {
        customToast(context);
    }

    public Toast getToast() {
        return mToast;
    }

    public void setText(String text){
        if (mToast != null){
            tvText.setText(text);
        }
    }


    public void cancel(){
        if (mToast != null){
            mToast.cancel();
        }
    }

    public void show(){
        if (mToast != null){
            mToast.show();
        }
    }

    public void setDuration(int duration){
        if (mToast != null){
            mToast.setDuration(duration);
        }
    }


    public void customToast(@NonNull Context context){
        mToast = Toast.makeText(context, "", Toast.LENGTH_SHORT);
        final View toastLayout = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                .inflate(R.layout.toast_layout, null);
        tvText = toastLayout.findViewById(R.id.toast_text);
        mToast.setView(toastLayout);

    }
}
