package com.zxl.mydailytest.activities.background;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.zxl.mydailytest.display.DisplayActivity;
import com.zxl.mydailytest.R;

/**
 * @author crazyZhangxl on 2018-12-25 14:59:01.
 * Describe:
 */

public class BgActivity extends AppCompatActivity {
    private static final String TAG = "finish";
    private TextView tvStatus,tvOne,tvTwo,tvThree;
    private Drawable mDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bg);
        //finish();
        Log.e(TAG, "结束完毕" );
        tvStatus = findViewById(R.id.tvStatus);
        mDrawable = ContextCompat.getDrawable(this, R.mipmap.ic_location);
        // 设置大小
        mDrawable.setBounds(0, 0,28,28);
        // 设置drawableLeft
        tvStatus.setCompoundDrawables(mDrawable, null, null, null);
        initViews();
        actionViews();
        actionExample();
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        for (int i=0;i<10;i++){
                            try {
                                Thread.sleep(100);
                                Log.e(TAG, "run: "+i);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
        ).start();
        Log.e(TAG, "onCreate:完毕");

        tvStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, "onClick: finish之前" );
                startActivity(new Intent(BgActivity.this, DisplayActivity.class));
                finish();
                Log.e(TAG, "onClick: finish之后" );
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "onStart:");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "onResume" );
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy: " );
    }

    @Override
    public void finish() {
        super.finish();
        Log.e(TAG, "finish: " );
    }

    private void actionViews() {
        tvOne.setText(getResources().getString(R.string.str_text));
        actionImage();
        Log.e(TAG, "actionViews: ");
    }

    private void actionImage() {
        SpannableString spanString = new SpannableString("1 南京");
        ImageSpan imageSpan = new ImageSpan(mDrawable, ImageSpan.ALIGN_BOTTOM);
        // 将0-1进行替换
        spanString.setSpan(imageSpan,0,1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvTwo.setText(spanString);
    }

    /**
     * 自定义单击url链接的动作...
     */
    private void actionToActivity(){
        String text = "跳转向活动";
        SpannableString spanToActivity = new SpannableString(text);
        spanToActivity.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                // Intent intent = new Intent(BgActivity.this,NextActivity.class);
                //  startActivity(intent);
            }
        },0,text.length()-1,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvThree.setText(spanToActivity);
        // 在单击链接时 凡事要执行的动作 都是要设置MovementMethod对象
        tvThree.setMovementMethod(LinkMovementMethod.getInstance());
    }


    private void actionExample(){
        SpannableStringBuilder stringBuilder = new SpannableStringBuilder("");
        stringBuilder.append("你好啊我的哥们\n");
        String inner = "我最帅";
        SpannableString spanInner = new SpannableString(inner);
        spanInner.setSpan(new BackgroundColorSpan(Color.RED),
                0,
                spanInner.length(),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        stringBuilder.append(spanInner);
        stringBuilder.append("\n啧啧");
        tvThree.setText(stringBuilder);
    }

    private void initViews() {
        tvOne = findViewById(R.id.tvOne);
        tvTwo = findViewById(R.id.tvTwo);
        tvThree = findViewById(R.id.tvThree);
    }
}
