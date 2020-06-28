package com.zxl.mydailytest.activities.colorFliter;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.zxl.mydailytest.R;

/**
 * @author zxl on 2018/08/15.
 *         discription:
 */
public class ImageFilterActivity extends AppCompatActivity {
    private ColorFilterImageView mColorFilterImageView;
    private Button mBtn;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_filter);
        mColorFilterImageView = (ColorFilterImageView) findViewById(R.id.filter);
        // 必须设置点击事件
        Log.e("测量",mColorFilterImageView.getMeasuredHeight()+" ");
        mColorFilterImageView.measure(0,0);
        Log.e("测量",mColorFilterImageView.getMeasuredHeight()+" ");
        mColorFilterImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ImageFilterActivity.this, "点击", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
