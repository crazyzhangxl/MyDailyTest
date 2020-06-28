package com.zxl.mydailytest.wuba;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.util.AttributeSet;


import com.zxl.mydailytest.R;

import java.util.Iterator;

/**
 * @author crazyZhangxl on 2018/10/17.
 * Describe:
 */
public class CustomNumKeyboardView
        extends KeyboardView
{
    private boolean mConFirmBtnEnabled = true;
    private Context mContext;
    private Keyboard mKeyboard;

    public CustomNumKeyboardView(Context paramContext, AttributeSet paramAttributeSet)
    {
        super(paramContext, paramAttributeSet);
        this.mContext = paramContext;
    }

    public CustomNumKeyboardView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
    {
        super(paramContext, paramAttributeSet, paramInt);
        this.mContext = paramContext;
    }

    private void drawIcon(Canvas paramCanvas, Keyboard.Key paramKey)
    {
        if (paramKey.icon != null)
        {
            paramKey.icon.setBounds(
                    paramKey.x + (paramKey.width - paramKey.icon.getIntrinsicWidth()) / 2,
                    paramKey.y + (paramKey.height - paramKey.icon.getIntrinsicHeight()) / 2,
                    paramKey.x + (paramKey.width - paramKey.icon.getIntrinsicWidth()) / 2 + paramKey.icon.getIntrinsicWidth(),
                    paramKey.y + (paramKey.height - paramKey.icon.getIntrinsicHeight()) / 2 + paramKey.icon.getIntrinsicHeight());
            paramKey.icon.draw(paramCanvas);
        }
    }

    private void drawKeyBackground(int paramInt, Canvas paramCanvas, Keyboard.Key paramKey)
    {
        Drawable localDrawable = this.mContext.getResources().getDrawable(paramInt);
        int[] arrayOfInt = paramKey.getCurrentDrawableState();
        if (paramKey.codes[0] != 0) {
            localDrawable.setState(arrayOfInt);
        }
        localDrawable.setBounds(paramKey.x, paramKey.y,
                paramKey.x + paramKey.width,
                paramKey.y + paramKey.height);
        localDrawable.draw(paramCanvas);
    }

    private void drawText(Canvas paramCanvas, Keyboard.Key paramKey, float paramFloat, int paramInt, Typeface paramTypeface)
    {
        Rect localRect = new Rect();
        Paint localPaint = new Paint();
        localPaint.setTextAlign(Paint.Align.CENTER);
        localPaint.setAntiAlias(true);
        localPaint.setColor(paramInt);
        if (paramKey.label != null)
        {
            // 字体大小
            localPaint.setTextSize(paramFloat);
            localPaint.setTypeface(paramTypeface);
            localPaint.getTextBounds(paramKey.label.toString(), 0, paramKey.label.toString().length(), localRect);
            paramFloat = paramKey.x + paramKey.width / 2;
            paramInt = paramKey.y;
            int i = paramKey.height / 2;
            paramCanvas.drawText(paramKey.label.toString(), paramFloat, localRect.height() / 2 + (paramInt + i), localPaint);
        }
    }

    public boolean isConfirmBtnEnabled()
    {
        return this.mConFirmBtnEnabled;
    }


    public void setConfirmBtnEnabled(boolean paramBoolean)
    {
        this.mConFirmBtnEnabled = paramBoolean;
        invalidate();
    }

    @Override
    public void onDraw(Canvas paramCanvas)
    {
        super.onDraw(paramCanvas);
        this.mKeyboard = getKeyboard();
        Iterator localIterator = this.mKeyboard.getKeys().iterator();
        while (localIterator.hasNext())
        {
            Keyboard.Key localKey = (Keyboard.Key)localIterator.next();
            if (localKey.codes[0] == -5)
            {
                drawKeyBackground(R.drawable.keyboard_delete_btn_bg, paramCanvas, localKey);
                drawIcon(paramCanvas, localKey);
            } else if (localKey.codes[0] == -4)
            {
                if (this.mConFirmBtnEnabled)
                {
                    drawKeyBackground(R.drawable.keyboard_cofirm_btn_bg, paramCanvas, localKey);
                    drawText(paramCanvas, localKey, getResources().getDimension(R.dimen.fontsize36), R.color.white, Typeface.DEFAULT);
                }
            }else if (localKey.codes[0] == -3){
                drawKeyBackground(R.drawable.keyboard_btn_normal_bg, paramCanvas, localKey);
                drawIcon(paramCanvas, localKey);
            } else {
                drawKeyBackground(R.drawable.keyboard_btn_normal_bg, paramCanvas, localKey);
                drawText(paramCanvas, localKey, getResources().getDimension(R.dimen.fontsize36), R.color.color_red, Typeface.DEFAULT);
            }
        }
    }


}
