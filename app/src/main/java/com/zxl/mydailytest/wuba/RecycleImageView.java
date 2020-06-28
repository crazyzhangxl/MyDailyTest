package com.zxl.mydailytest.wuba;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatImageView;

import com.zxl.mydailytest.R;


/**
 * @author crazyZhangxl on 2018/10/17.
 * Describe:
 */
public class RecycleImageView
        extends AppCompatImageView
{
    public RecycleImageView(Context paramContext)
    {
        super(paramContext);
    }

    public RecycleImageView(Context paramContext, AttributeSet paramAttributeSet)
    {
        super(paramContext, paramAttributeSet);
    }

    public RecycleImageView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
    {
        super(paramContext, paramAttributeSet, paramInt);
    }


    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas paramCanvas)
    {
        BitmapDrawable bitmapDrawable = (BitmapDrawable) getDrawable();
        Bitmap localBitmap = bitmapDrawable.getBitmap();
        // 如股票bitmap 不为空 但是不可用了(被回收了)
        if ((localBitmap != null) && (localBitmap.isRecycled())) {
            setImageDrawable(new ColorDrawable(getResources().getColor(R.color.color_blue)));
        }
        super.onDraw(paramCanvas);
    }
}
