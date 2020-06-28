package com.zxl.mydailytest.activities;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.zxl.mydailytest.R;

import java.security.MessageDigest;

/**
 * @author crazyZhangxl on 2018/12/24.
 * Describe:
 */
public class EmotionLayout extends LinearLayout implements View.OnClickListener {
    private static final String TAG = "EmotionLayout";
    private int mMeasureWidth,mMeasureHeight;
    /**
     * 选中的下标
     */
    private int mTabPosi = 0;
    private Context mContext;
    private ViewPager mVpEmotioin;
    private LinearLayout mLlPageNumber;
    private LinearLayout mLlTabContainer;
    private RelativeLayout mRlEmotionAdd;
    private int mTabCount;
    /**
     * view缓存 SparseArray的好处 ？？？
     */
    private SparseArray<View> mTabViewArray = new SparseArray<>();


    public EmotionLayout(Context context) {
        this(context,null);
    }

    public EmotionLayout(Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }

    public EmotionLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        setOrientation(VERTICAL);
        Log.e(TAG, "EmotionLayout: 构造方法里面");
    }



    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mMeasureWidth = measureSize(widthMeasureSpec);
        mMeasureHeight = measureSize(heightMeasureSpec);
        Log.e(TAG, "onMeasure: "+mMeasureWidth+"  "+mMeasureHeight );
        setMeasuredDimension(mMeasureWidth,mMeasureHeight);
    }

    private int measureSize(int measureSpec){
        int result = 0;
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);
        if (MeasureSpec.EXACTLY == mode){
            result = size;
        }else {
            result = EmotionDataManager.dpToPx(250);
            if (MeasureSpec.AT_MOST == measureSpec){
                result = Math.min(result,size);
            }
        }
        return result;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.e(TAG, "onSizeChanged:---------------");
        baseInit();
        // 初始化布局
        init();
        // 设置监听
        initListener();
    }

    private void baseInit() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.emotion_layout, null);
        view.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,mMeasureHeight));
        mVpEmotioin = (ViewPager) view.findViewById(R.id.vpEmotioin);
        mLlPageNumber = (LinearLayout) view.findViewById(R.id.llPageNumber);
        mLlTabContainer = (LinearLayout) view.findViewById(R.id.llTabContainer);
        mRlEmotionAdd = (RelativeLayout) view.findViewById(R.id.rlEmotionAdd);
        addView(view);
    }

    private void init() {
        initTabs();
    }

    private void initTabs() {
        EmotionTab emotionTab = new EmotionTab(mContext, R.drawable.ic_tab_emoji);
        mLlTabContainer.addView(emotionTab);
        EmotionTab addTab = new EmotionTab(mContext, R.drawable.ic_tab_add);
        mLlTabContainer.addView(addTab);
        // 表情
        mTabViewArray.put(0,emotionTab);
        mTabViewArray.put(1,addTab);
        // 设置选中第一个
        selectTab(0);
    }


    private void initListener() {
        if (mLlTabContainer != null){
            mTabCount = mLlTabContainer.getChildCount();
            for (int position = 0;position<mTabCount;position++){
                View tab = mLlTabContainer.getChildAt(position);
                tab.setTag(position);
                tab.setOnClickListener(this);
            }
        }

        mVpEmotioin.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                setCurPageCommon(position);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 设置当前页面--------
     * @param position
     */
    private void setCurPageCommon(int position) {
        // 设置当前界面 ----
        setCurPage(position, (int) Math.ceil(EmotionDataManager.getDisplayCount()/1f/EmotionDataManager.SIZE));
    }

    /**
     * 设置当前页------------- 当前页
     * @param position
     */
    private void selectTab(int position) {
        /*------这是啥-----*/
        if (position == mTabViewArray.size()){
            return;
        }

        // 先全部设置为白色 ---------
        for (int i = 0; i < mTabCount; i++) {
            View tab = mTabViewArray.get(i);
            tab.setBackgroundResource(R.drawable.shape_tab_normal);
        }
        // 再设置当前------
        mTabViewArray.get(position).setBackgroundResource(R.drawable.shape_tab_press);
        /*
         * 核心方法进行填充 ------- 显示表情 ------------
         */
        fillVpEmotioin(position);
    }

    /**
     * 核心填充 ----------
     * @param position
     */
    private void fillVpEmotioin(int position) {
        EmotionViewPagerAdapter adapter = new EmotionViewPagerAdapter(position, mMeasureWidth, mMeasureHeight);
        mVpEmotioin.setAdapter(adapter);
        // 移除所有的view
        mLlPageNumber.removeAllViews();
        setCurPageCommon(0);
    }

    /**
     * 设置当前页 -----------------
     * @param page         当前页  =====
     * @param pageCount    页的数量 ====
     *
     *  那起码把这里给看懂吧 ------- 这不过分吧-------
     *
     */
    private void setCurPage(int page, int pageCount) {
        int hasCount = mLlPageNumber.getChildCount();
        int forMax = Math.max(hasCount, pageCount);

        ImageView ivCur = null;
        for (int i = 0; i < forMax; i++) {
            if (pageCount <= hasCount) {
                if (i >= pageCount) {
                    mLlPageNumber.getChildAt(i).setVisibility(View.GONE);
                    continue;
                } else {
                    ivCur = (ImageView) mLlPageNumber.getChildAt(i);
                }
            } else {
                if (i < hasCount) {
                    ivCur = (ImageView) mLlPageNumber.getChildAt(i);
                } else {
                    ivCur = new ImageView(mContext);
                    ivCur.setBackgroundResource(R.drawable.selector_view_pager_indicator);
                    LayoutParams params = new LayoutParams(EmotionDataManager.dpToPx(8), EmotionDataManager.dpToPx(8));
                    ivCur.setLayoutParams(params);
                    params.leftMargin = EmotionDataManager.dpToPx(3);
                    params.rightMargin = EmotionDataManager.dpToPx(3);
                    mLlPageNumber.addView(ivCur);
                }
            }

            ivCur.setId(i);
            ivCur.setSelected(i == page);
            ivCur.setVisibility(View.VISIBLE);
        }
    }

    /**
     * tab的点击事件---------------
     * @param v
     */
    @Override
    public void onClick(View v) {
        mTabPosi = (int) v.getTag();
        // 设置选中
        selectTab(mTabPosi);
    }
}
