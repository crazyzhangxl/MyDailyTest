package com.zxl.mydailytest.text;

import android.graphics.Color;
import android.text.TextPaint;
import android.text.style.CharacterStyle;

/**
 * @author zxl on 2018/8/15.
 *         discription: CharacterStyle是字体的基类,后面像
 *         什么点击事件，背景啊 啥的都继承自该基类
 */

public class SpannableBg extends CharacterStyle {

    /**
     * 更新绘制的状态
     * 可以添加颜色下划线之列
     * @param ds
     */
    @Override
    public void updateDrawState(TextPaint ds) {
        ds.setColor(Color.parseColor("#ff0000"));
        // 这里的尺寸是 px 后面这里需要转换的
        ds.setTextSize(38);
        // 是否添加下划线
        ds.setUnderlineText(false);
        ds.clearShadowLayer();
    }
}
