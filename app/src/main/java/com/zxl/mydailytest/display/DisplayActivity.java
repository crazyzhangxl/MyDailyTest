package com.zxl.mydailytest.display;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.zxl.mydailytest.R;


public class DisplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        calDensity();
    }

    private void calDensity(){
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        float density = displayMetrics.density;
        Log.e("尺寸", "calDensity: "+density );
        Log.e("尺寸", "dpi: "+displayMetrics.densityDpi);
        Log.e("尺寸", "x = "+displayMetrics.widthPixels+"  ,  y = "+displayMetrics.heightPixels );
        // calDensity: 2.75
        // dpi: 440
        // x = 1080  ,  y = 2118
        // 获得状态栏高度  = 110
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        int height = getResources().getDimensionPixelSize(resourceId);
        Log.e("尺寸", "height: "+height );
        // height: 110
        DisplayMetrics outMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getRealMetrics(outMetrics);
        int widthPixel = outMetrics.widthPixels;
        int heightPixel = outMetrics.heightPixels;
        Log.e("尺寸", "widthPixel = " + widthPixel + ",heightPixel = " + heightPixel);
        Log.e("尺寸", "density = "+outMetrics.density);
        // getRealMetrics 会计算 虚拟软件盘的高度
        // widthPixel = 1080,heightPixel = 2248
    }
}
