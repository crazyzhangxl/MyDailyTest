package com.zxl.mydailytest.activities;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.text.style.StrikethroughSpan;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.zxl.mydailytest.text.CircleMovementMethod;
import com.zxl.mydailytest.text.NameClickListener;
import com.zxl.mydailytest.text.SpannableBg;
import com.zxl.mydailytest.text.TextClickSpan;
import com.zxl.mydailytest.R;

/**
 * @author zxl on 2018/08/14.
 *         discription: EditText和TextView测试
 */
public class EditTextActivity extends Activity {
    private TextView mTvColor;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        getWindow().setStatusBarColor(Color.parseColor("#ff0000"));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_text);
        initView();
        setSpannableStyle();

    }

    private void setSpannableStyle() {
        SpannableString dollars = new SpannableString("$6000");
        dollars.setSpan(new SpannableBg(),
                0,
                1,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        dollars.setSpan(new StrikethroughSpan(),1,dollars.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        mTvColor.setText(dollars);
        dollars.setSpan(new TextClickSpan(new NameClickListener(
                "我牛逼"), 0),
                1,dollars.length()
                ,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        mTvColor.setText(dollars);
        /* 这句话才是重点吖,不加根本没法点击 背景是这个LinkMovementMethod在起作用
        * */
        Toast.makeText(this, "", Toast.LENGTH_SHORT).show();

        // 传入的是资源你的ID
        mTvColor.setMovementMethod(new CircleMovementMethod(R.color.transparent));
    }

    private void initView() {
        mTvColor = findViewById(R.id.tvColor);
    }


//    public void showToast(String str){
//        // 一些属性
//        final WindowManager.LayoutParams params = new WindowManager.LayoutParams();
//        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
//        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
//        params.format = PixelFormat.TRANSLUCENT;
//        params.windowAnimations = com.android.internal.R.style.Animation_Toast;
//        params.type = WindowManager.LayoutParams.TYPE_TOAST;
//        params.setTitle("Toast");
//        params.flags = WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
//                | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
//                | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE;
//        // 指定吐司的布局位置  params.gravity = Gravity.LEFT+Gravity.TOP;
//        params.gravity = Gravity.CENTER;
//        // 吐司显示效果 挂在在窗体上面
//        View view = LayoutInflater.from(this).inflate(R.layout.toast_design, null);
//        TextView tvText = view.findViewById(R.id.tvToast);
//        tvText.setText(str);
//        WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
//        // 在窗体上挂在view
//        wm.addView(view,params);
//
//    }



}
