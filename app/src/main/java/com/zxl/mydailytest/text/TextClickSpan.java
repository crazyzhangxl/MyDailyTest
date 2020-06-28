package com.zxl.mydailytest.text;

import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;

/**
 * @author zxl on 2018/8/15.
 *         discription:
 */

public class TextClickSpan extends ClickableSpan {
    private ISpanClick mISpanClick;
    private int mPosition;

    public TextClickSpan(ISpanClick ISpanClick, int posiotion) {
        mISpanClick = ISpanClick;
        mPosition = posiotion;
    }

    @Override
    public void onClick(View widget) {
        mISpanClick.onClick(mPosition);
    }

    @Override
    public void updateDrawState(TextPaint ds) {
        // 这里继承的话 那么会重叠之前你的属性,所以在这
        // 里不做操作
    }
}
