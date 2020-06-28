package com.zxl.mydailytest.spannable;

import android.graphics.Color;
import android.text.TextPaint;
import android.text.style.CharacterStyle;

/**
 * Created by apple on 2019-12-05.
 * description:
 */
public class TextSpannable extends CharacterStyle {

    @Override
    public void updateDrawState(TextPaint tp) {
        tp.setTextSize(40);
        tp.setColor(Color.RED);
        tp.setUnderlineText(true);
    }
}
