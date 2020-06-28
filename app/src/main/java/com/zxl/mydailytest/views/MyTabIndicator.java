package com.zxl.mydailytest.views;

import android.content.Context;
import android.graphics.Color;
import android.annotation.SuppressLint;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

import java.util.ArrayList;

/**
 * @author crazyZhangxl on 2018/10/18.
 * Describe: 自定义Tab指示器 ----------------------
 */
public class MyTabIndicator extends LinearLayout{
    // 选中的index --
    private int mSelectedIndex = 0;

    private Context mContext;
    // 上面的文字
    private LinearLayout mLl_tab_text;

    private int tabNum = 4;

    private ArrayList<TabView> mTabViewArrayList = new ArrayList<>();

    public MyTabIndicator(Context context) {
        this(context,null);
    }

    public MyTabIndicator(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyTabIndicator(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
    }

    private void init() {
        removeAllViews();
        setOrientation(VERTICAL);
        mLl_tab_text = new LinearLayout(mContext);
        mLl_tab_text.setOrientation(HORIZONTAL);
        mLl_tab_text.setWeightSum(tabNum);
        mLl_tab_text.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        for (int i=0;i<tabNum;i++){
            TabView tabView = createTabView("数据"+i,false);
            tabView.setIndex(i);
            mLl_tab_text.addView(tabView,i);
            mTabViewArrayList.add(tabView);
        }
        // 将水平线性的布局加入
        addView(mLl_tab_text,0);
    }

    private TabView createTabView(String text,boolean isSelected){
        TabView tabView = new TabView(mContext);
        //Params(宽,高,权重);
        tabView.setLayoutParams(new LayoutParams(0,LayoutParams.WRAP_CONTENT,1));
        tabView.setText(text);
        tabView.setGravity(Gravity.CENTER);
        // 设置左上右下 padding
        tabView.setPadding(0,40,0,40);
        tabView.setSelected(isSelected);
        //设置点击事件
        tabView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return tabView;
    }


    class TabView extends AppCompatTextView {
        private int mSelectedColor = Color.RED;
        private int mNormalColor = Color.BLACK;
        private boolean isSelected = false;
        private int index;
        public TabView(Context context) {
            this(context,null);
        }

        public TabView(Context context, @Nullable AttributeSet attrs) {
            super(context, attrs);
            setTextSize(15f);
        }

        @Override
        public void setText(CharSequence text, BufferType type) {
            if (isSelected){
                setTextColor(mSelectedColor);
            }else {
                setTextColor(mNormalColor);
            }
            super.setText(text, type);
        }


        @Override
        public void setSelected(boolean selected) {
            isSelected = selected;
            setText(getText());
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public void resetState(){
            isSelected = false;
            setText(getText());
        }

        public void setSelectedColor(int selectedColor) {
            mSelectedColor = selectedColor;
        }

        public void setNormalColor(int normalColor) {
            mNormalColor = normalColor;
        }
    }
}
