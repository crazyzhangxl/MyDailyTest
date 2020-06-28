package com.zxl.mydailytest.views;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatTextView;

/**
 * @author crazyZhangxl on 2018/10/22.
 * Describe: 自定义状态View
 */
public class DoStateView extends AppCompatTextView {

    private boolean isSelected = false;
    private OnSelecedListener mOnSelecedListener;

    public DoStateView(Context context) {
        this(context,null);

    }

    public DoStateView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // 设置颜色
        setTextColor(createColorStateList(Color.BLACK,Color.RED));
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnSelecedListener != null){
                    isSelected = !isSelected;
                    DoStateView.this.setSelected(isSelected);
                    mOnSelecedListener.onSelectedOnChanged(isSelected);
                }
            }
        });
    }

    /**
     * 颜色选择
     * @param defaultColor   默认颜色
     * @param selectedColor  选中颜色
     * @return
     */
    private static ColorStateList createColorStateList(int defaultColor, int selectedColor) {
        final int[][] states = new int[2][];
        final int[] colors = new int[2];
        int i = 0;

        states[i] = SELECTED_STATE_SET;
        colors[i] = selectedColor;
        i++;

        // Default enabled state
        states[i] = EMPTY_STATE_SET;
        colors[i] = defaultColor;
        return new ColorStateList(states, colors);
    }

    /**
     * 背景选择
       setBackground(createStateListDrawable(normalDrawableResID,selectedDrawableResID));
     * @param normalDrawableResID
     * @param selectedDrawableResID
     * @return
     */
    private StateListDrawable  createStateListDrawable(int normalDrawableResID,int selectedDrawableResID){
        StateListDrawable stateListDrawable = new StateListDrawable();
        Drawable normalDrawable = getContext().getResources().getDrawable(normalDrawableResID);
        Drawable selectedDrawable = getContext().getResources().getDrawable(selectedDrawableResID);
        // empty_state_set
        stateListDrawable.addState(EMPTY_STATE_SET,normalDrawable);
        stateListDrawable.addState(SELECTED_STATE_SET,selectedDrawable);
        return stateListDrawable;
    }

    public void setOnSelecedListener(OnSelecedListener onSelecedListener){
        this.mOnSelecedListener = onSelecedListener;
    }

    public interface OnSelecedListener{
        void onSelectedOnChanged(boolean selectState);
    }
}
