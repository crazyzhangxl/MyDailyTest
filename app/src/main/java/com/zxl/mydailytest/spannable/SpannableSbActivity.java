package com.zxl.mydailytest.spannable;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zxl.mydailytest.R;

public class SpannableSbActivity extends AppCompatActivity {
    private TextView tvTextOne;
    private boolean isLoading = false;

    public static void show(Activity activity){
        activity.startActivity(new Intent(activity, SpannableSbActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spannable_sb);
        initViews();
    }

    private void initViews() {
        tvTextOne = findViewById(R.id.tvText);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && !isLoading){
            setSpannableOne();
            isLoading = true;
        }
    }

    private void setSpannableOne(){
        String content = "我叫张先磊,我还得努力我还得努力,我是";
        String tag = "第一";
        String total = content+tag;

        LinearLayout layout = new LinearLayout(this);

        TextView tvTag = new TextView(this);
        tvTag.setText(tag);
        tvTag.setTextColor(Color.RED);
        tvTag.setTextSize(15);
        tvTag.setBackgroundResource(R.drawable.bg_tag_style);
        tvTag.setPadding(10,2,10,2);
        layout.addView(tvTag);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) tvTag.getLayoutParams();
        layoutParams.leftMargin = 20;
        layoutParams.rightMargin = 20;

        // 将布局转换为bitmap.... !!!!!
        layout.setDrawingCacheEnabled(true);
        layout.measure(0,0);
        layout.layout(0,0,layout.getMeasuredWidth(),layout.getMeasuredHeight());
        Bitmap tvBitmap =  Bitmap.createBitmap(layout.getDrawingCache());
        layout.destroyDrawingCache();

        // spannableString
        SpannableString spannableString = new SpannableString(total);
        spannableString.setSpan(new ImageSpan(this,tvBitmap),
                content.length(),total.length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvTextOne.setText(spannableString);
    }
}
