package com.zxl.mydailytest.views;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.util.Pools;
import androidx.core.view.ViewCompat;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;

import java.util.ArrayList;

import static com.google.android.material.tabs.TabLayout.GRAVITY_FILL;


/**
 * @author crazyZhangxl on 2018/10/22.
 * Describe: 自定义TabLayout领悟学习
 */
public class MyTabLayout extends LinearLayout {
    private NormalTabStrip mNormalTabStrip;
    private final ArrayList<MyTab> mTabs = new ArrayList<>();
    private MyTab mSelectedTab;
    private ColorStateList mTabTextColors;
    static final int FIXED_WRAP_GUTTER_MIN = 16; //dps
    static final int MOTION_NON_ADJACENT_OFFSET = 24;
    int mTabGravity;
    private ValueAnimator mScrollAnimator;
    private static final int INVALID_WIDTH = -1;
    int mTabMaxWidth = Integer.MAX_VALUE;


    private final int mRequestedTabMinWidth = -1;
    private static final int DEFAULT_HEIGHT = 48; // dps
    private static final int TAB_MIN_WIDTH_MARGIN = 56; //dps
    private final int mRequestedTabMaxWidth = -1;




    private OnTabSelectedListener mSelectedListener;
    private final ArrayList<OnTabSelectedListener> mSelectedListeners = new ArrayList<>();
    private OnTabSelectedListener mCurrentVpSelectedListener;

    public interface OnTabSelectedListener {

        /**
         * Called when a tab enters the selected state.
         *
         * @param tab The tab that was selected
         */
        public void onTabSelected(MyTab tab);

        /**
         * Called when a tab exits the selected state.
         *
         * @param tab The tab that was unselected
         */
        public void onTabUnselected(MyTab tab);

        /**
         * Called when a tab that is already selected is chosen again by the user. Some applications
         * may use this action to return to the top level of a category.
         *
         * @param tab The tab that was reselected.
         */
        public void onTabReselected(MyTab tab);
    }

    public void setScrollPosition(int position, float positionOffset, boolean updateSelectedText) {
        setScrollPosition(position, positionOffset, updateSelectedText, true);
    }

    void setScrollPosition(int position, float positionOffset, boolean updateSelectedText,
                           boolean updateIndicatorPosition) {
        final int roundedPosition = Math.round(position + positionOffset);
        if (roundedPosition < 0 || roundedPosition >= mNormalTabStrip.getChildCount()) {
            return;
        }

        // Set the indicator position, if enabled
        if (updateIndicatorPosition) {
            Log.e("log","Set the indicator position ");
            mNormalTabStrip.setIndicatorPositionFromTabPosition(position, positionOffset);
        }

        // Now update the scroll position, canceling any running animation
        if (mScrollAnimator != null && mScrollAnimator.isRunning()) {
            mScrollAnimator.cancel();
        }
        scrollTo(calculateScrollXForTab(position, positionOffset), 0);

        // Update the 'selected state' view as we scroll, if enabled
        if (updateSelectedText) {
            setSelectedTabView(roundedPosition);
        }
    }

    private void setSelectedTabView(int position) {
        final int tabCount = mNormalTabStrip.getChildCount();
        if (position < tabCount) {
            for (int i = 0; i < tabCount; i++) {
                final View child = mNormalTabStrip.getChildAt(i);
                child.setSelected(i == position);
            }
        }
    }

    private int calculateScrollXForTab(int position, float positionOffset) {
        return 0;
    }


    // 这2个不太懂啊 ----


    private final Pools.Pool<MyTabView> mTabViewPool = new Pools.SimplePool<>(12);
    private static final Pools.Pool<MyTab> sTabPool = new Pools.SynchronizedPool<>(16);

    public MyTabLayout(Context context) {
        this(context,null);
    }

    public MyTabLayout(Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }

    public MyTabLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mNormalTabStrip = new NormalTabStrip(context);
        // 设置颜色选择器
        mTabTextColors = createColorStateList(Color.RED,Color.GREEN);
        mNormalTabStrip.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT
                ,ViewGroup.LayoutParams.WRAP_CONTENT));
        mNormalTabStrip.setSelectedIndicatorColor(Color.RED);
        mNormalTabStrip.setSelectedIndicatorHeight(2);
        addView(mNormalTabStrip);
        updateMyTabViews(true);
    }

    public int getSelectedTabPosition() {
        return mSelectedTab != null ? mSelectedTab.getPosition() : -1;
    }


    /**
     * 设置View的入口 *******************
     */
    public void setTabInit(){
        // 添加Tab数据
        for (int i = 0; i < 4; i++) {
            addMyTab(newTab().setText("你是谁"+i), false);
        }
        // 设置选中的TAB
        selectTab(getTabAt(0));
        setScrollPosition(0,0,false,false);
    }

    @Nullable
    public MyTab getTabAt(int index) {
        return (index < 0 || index >= getTabCount()) ? null : mTabs.get(index);
    }

    public int getTabCount() {
        return mTabs.size();
    }


    public void addMyTab(@NonNull MyTab tab, boolean setSelected) {
        addMyTab(tab, mTabs.size(), setSelected);
    }

    public void addMyTab(@NonNull MyTab tab, int position, boolean setSelected) {
        if (tab.mParent != this) {
            throw new IllegalArgumentException("Tab belongs to a different TabLayout.");
        }
        configureTab(tab, position);
        addTabView(tab);

        if (setSelected) {
            tab.select();
        }
    }

    private void addTabView(MyTab tab) {
        final MyTabView tabView = tab.mMyTabView;
        // 已经添加入内了
        mNormalTabStrip.addView(tabView, tab.getPosition(), createLayoutParamsForTabs());
    }

    private LinearLayout.LayoutParams createLayoutParamsForTabs() {
        final LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.MATCH_PARENT);
        // 直接设置权重
        lp.width = 0;
        lp.weight = 1;
        return lp;
    }

    private void configureTab(MyTab tab, int position) {
        // tab设置索引
        tab.setPosition(position);
        // 添加如缓存中
        mTabs.add(position, tab);

        final int count = mTabs.size();
        for (int i = position + 1; i < count; i++) {
            mTabs.get(i).setPosition(i);
        }
    }

    void selectTab(MyTab tab) {
        selectTab(tab, true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // If we have a MeasureSpec which allows us to decide our height, try and use the default
        // height
        final int idealHeight = dpToPx(getDefaultHeight()) + getPaddingTop() + getPaddingBottom();
        switch (MeasureSpec.getMode(heightMeasureSpec)) {
            case MeasureSpec.AT_MOST:
                heightMeasureSpec = MeasureSpec.makeMeasureSpec(
                        Math.min(idealHeight, MeasureSpec.getSize(heightMeasureSpec)),
                        MeasureSpec.EXACTLY);
                break;
            case MeasureSpec.UNSPECIFIED:
                heightMeasureSpec = MeasureSpec.makeMeasureSpec(idealHeight, MeasureSpec.EXACTLY);
                break;
            default:
                break;
        }

        final int specWidth = MeasureSpec.getSize(widthMeasureSpec);
        if (MeasureSpec.getMode(widthMeasureSpec) != MeasureSpec.UNSPECIFIED) {
            // If we don't have an unspecified width spec, use the given size to calculate
            // the max tab width
            mTabMaxWidth = mRequestedTabMaxWidth > 0
                    ? mRequestedTabMaxWidth
                    : specWidth - dpToPx(TAB_MIN_WIDTH_MARGIN);
        }

        // Now super measure itself using the (possibly) modified height spec
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        if (getChildCount() == 1) {
            // If we're in fixed mode then we need to make the tab strip is the same width as us
            // so we don't scroll
            final View child = getChildAt(0);
            boolean remeasure = false;
            remeasure = child.getMeasuredWidth() != getMeasuredWidth();


            if (remeasure) {
                // Re-measure the child with a widthSpec set to be exactly our measure width
                int childHeightMeasureSpec = getChildMeasureSpec(heightMeasureSpec, getPaddingTop()
                        + getPaddingBottom(), child.getLayoutParams().height);
                int childWidthMeasureSpec = MeasureSpec.makeMeasureSpec(
                        getMeasuredWidth(), MeasureSpec.EXACTLY);
                child.measure(childWidthMeasureSpec, childHeightMeasureSpec);
            }
        }
    }

    private int getDefaultHeight() {
        return DEFAULT_HEIGHT;
    }

    void selectTab(final MyTab tab, boolean updateIndicator) {
        // 这个可能为空的 思考下的---- 当前确实为空啊
        final MyTab currentTab = mSelectedTab;

        if (currentTab == tab) {
            if (currentTab != null) {
                dispatchTabReselected(tab);
                animateToTab(tab.getPosition());
            }
        } else {
            final int newPosition = tab != null ? tab.getPosition() : MyTab.INVALID_POSITION;
            Log.e("log","最新的Position newPosition = "+newPosition);
            if (updateIndicator) {
                if ((currentTab == null || currentTab.getPosition() == MyTab.INVALID_POSITION)
                        && newPosition != MyTab.INVALID_POSITION) {
                    // If we don't currently have a tab, just draw the indicator
                    Log.e("log","开始画指示器了没");
                    setScrollPosition(newPosition, 0f, true);
                } else {
                    animateToTab(newPosition);
                }
                if (newPosition != MyTab.INVALID_POSITION) {
                    setSelectedTabView(newPosition);
                }
            }
            if (currentTab != null) {
                dispatchTabUnselected(currentTab);
            }
            mSelectedTab = tab;
            if (tab != null) {
                dispatchTabSelected(tab);
            }
        }
    }

    private void ensureScrollAnimator() {
        if (mScrollAnimator == null) {
            mScrollAnimator = new ValueAnimator();
            mScrollAnimator.setInterpolator(new FastOutSlowInInterpolator());
            mScrollAnimator.setDuration(300);
            mScrollAnimator.addUpdateListener(animator -> scrollTo((int) animator.getAnimatedValue(), 0));
        }
    }

    private void animateToTab(int newPosition) {
        if (newPosition == MyTab.INVALID_POSITION) {
            return;
        }

        if (getWindowToken() == null || !ViewCompat.isLaidOut(this)
                || mNormalTabStrip.childrenNeedLayout()) {
            // If we don't have a window token, or we haven't been laid out yet just draw the new
            // position now
            setScrollPosition(newPosition, 0f, true);
            return;
        }

        final int startScrollX = getScrollX();
        final int targetScrollX = calculateScrollXForTab(newPosition, 0);

        if (startScrollX != targetScrollX) {
            ensureScrollAnimator();
            mScrollAnimator.setIntValues(startScrollX, targetScrollX);
            mScrollAnimator.start();
        }

        // Now animate the indicator
        mNormalTabStrip.animateIndicatorToPosition(newPosition, 300);
    }

    private void dispatchTabSelected(@NonNull final MyTab tab) {
        for (int i = mSelectedListeners.size() - 1; i >= 0; i--) {
            mSelectedListeners.get(i).onTabSelected(tab);
        }
    }

    private void dispatchTabUnselected(@NonNull final MyTab tab) {
        for (int i = mSelectedListeners.size() - 1; i >= 0; i--) {
            mSelectedListeners.get(i).onTabUnselected(tab);
        }
    }

    private void dispatchTabReselected(@NonNull final MyTab tab) {
        for (int i = mSelectedListeners.size() - 1; i >= 0; i--) {
            mSelectedListeners.get(i).onTabReselected(tab);
        }
    }



    /**
     * 构建tab
     * @return
     */
    @NonNull
    public MyTab newTab(){
        MyTab myTab = sTabPool.acquire();
        if (myTab == null) {
            myTab = new MyTab();
        }
        myTab.mParent = this;
        myTab.mMyTabView = createMyTabView(myTab);
        return myTab;
    }

    private MyTabView createMyTabView(MyTab myTab){
        MyTabView myTabView = mTabViewPool != null ? mTabViewPool.acquire() : null;
        if (myTabView == null){
            myTabView = new MyTabView(getContext());
        }
        myTabView.setTab(myTab);
        myTabView.setFocusable(true);
        // 设置最小的宽度还没有
        return myTabView;
    }


    /**
     * MyTabView的管理类
     */
    private static final class MyTab{
        private static final int INVALID_POSITION = -1;
        private CharSequence mText;
        private int mPosition = INVALID_POSITION;
        private MyTabView mMyTabView;
        private MyTabLayout mParent;

        MyTab(){

        }

        public int getPosition() {
            return mPosition;
        }

        void setPosition(int position) {
            mPosition = position;
        }

        public MyTab setText(@Nullable CharSequence text) {
            mText = text;
            updateView();
            return this;
        }

        @Nullable
        public CharSequence getText() {
            return mText;
        }

        public void select() {
            if (mParent == null) {
                throw new IllegalArgumentException("Tab not attached to a TabLayout");
            }
            mParent.selectTab(this);
        }


        private void updateView() {
            if (mMyTabView != null) {
                mMyTabView.update();
            }
        }

        public boolean isSelected() {
            if (mParent == null) {
                throw new IllegalArgumentException("Tab not attached to a TabLayout");
            }
            return mParent.getSelectedTabPosition() == mPosition;
        }


    }

    /**
     * 这里就是简单的文字效果-------
     */
    class MyTabView extends AppCompatTextView {
        private MyTab mMyTab;
        public MyTabView(Context context) {
            super(context);
            setGravity(Gravity.CENTER);
            setClickable(true);
        }


         void setTab(@Nullable final MyTab tab) {
             if (tab != mMyTab) {
                 mMyTab = tab;
                 update();
             }
         }

         final void update(){
             final CharSequence text = mMyTab != null ? mMyTab.getText() : null;
             if (text != null) {
                 setText(text);
             }
             // 设置背景颜色------
             setTextColor(mTabTextColors);
             setSelected(mMyTab != null && mMyTab.isSelected());
         }

    }

    private  ColorStateList createColorStateList(int defaultColor, int selectedColor) {
        final int[][] states = new int[2][];
        final int[] colors = new int[2];
        int i = 0;

        states[i] = SELECTED_STATE_SET;
        colors[i] = selectedColor;
        i++;

        // Default enabled state
        states[i] = EMPTY_STATE_SET;
        colors[i] = defaultColor;
        i++;

        return new ColorStateList(states, colors);
    }


    /**
     * 更新布局
     * @param requestLayout 是否需要重新布局
     */
    void updateMyTabViews(boolean requestLayout){
        for (int i=0 ;i< mNormalTabStrip.getChildCount();i++){
            View child = mNormalTabStrip.getChildAt(i);
            child.setMinimumWidth(getTabMinWidth());
            LayoutParams layoutParams = (LayoutParams) child.getLayoutParams();
            // 这里假设是少数几个吧 按权重去分配的 ---
            layoutParams.width = 0;
            layoutParams.weight = 1;
            if (requestLayout) {
                child.requestLayout();
            }
        }
    }

    private int getTabMinWidth() {
        if (mRequestedTabMinWidth != INVALID_WIDTH) {
            // If we have been given a min width, use it
            return mRequestedTabMinWidth;
        }
        // Else, we'll use the default value
        return  0;
    }

    private class NormalTabStrip extends LinearLayout{
        private int mSelectedIndicatorHeight;
        private  Paint mSelectedIndicatorPaint;

        int mSelectedPosition = -1;
        float mSelectionOffset;

        private int mLayoutDirection = -1;

        private int mIndicatorLeft = -1;
        private int mIndicatorRight = -1;

        private ValueAnimator mIndicatorAnimator;


        /**
         *
         ViewGroup默认情况下，出于性能考虑，会被设置成WILL_NOT_DROW，这样，ondraw就不会被执行了
         通过setWillNotDraw(false) 来调用起onDraw()
         * @param context
         */
        public NormalTabStrip(Context context) {
            super(context);
            //设置viewGroup开启onDraw()
            setWillNotDraw(false);
            mSelectedIndicatorPaint = new Paint();
            // 设置布局水平排列
            setOrientation(HORIZONTAL);
            // 设置子布局水平居中
            setGravity(Gravity.CENTER_HORIZONTAL);
        }

        /**
         * 设置指示器颜色
         * @param color
         */
        void setSelectedIndicatorColor(int color) {
            if (mSelectedIndicatorPaint.getColor() != color) {
                mSelectedIndicatorPaint.setColor(color);
                ViewCompat.postInvalidateOnAnimation(this);
            }
        }

        /**
         * 设置指示器高度
         * @param selectedIndicatorHeight
         */
        void setSelectedIndicatorHeight(int selectedIndicatorHeight) {
            if (mSelectedIndicatorHeight != selectedIndicatorHeight) {
                mSelectedIndicatorHeight = selectedIndicatorHeight;
                ViewCompat.postInvalidateOnAnimation(this);
            }
        }

        /**
         * 判断是否需要布局
         * @return
         */
        boolean childrenNeedLayout() {
            for (int i = 0, z = getChildCount(); i < z; i++) {
                final View child = getChildAt(i);
                if (child.getWidth() <= 0) {
                    return true;
                }
            }
            return false;
        }

        /**
         * 不明白 ----
         * @param position  0
         * @param positionOffset 0
         */
        void setIndicatorPositionFromTabPosition(int position, float positionOffset) {
            if (mIndicatorAnimator != null && mIndicatorAnimator.isRunning()) {
                mIndicatorAnimator.cancel();
            }

            mSelectedPosition = position;
            mSelectionOffset = positionOffset;
            updateIndicatorPosition();
        }

        /**
         * 不明白 -----
         * @return
         */
        float getIndicatorPosition() {
            return mSelectedPosition + mSelectionOffset;
        }





        void updateIndicatorPosition() {
            // 获得当前选中的-----标题
            final MyTabView selectedTitle = (MyTabView) getChildAt(mSelectedPosition);
            int left, right;
            Log.e("log"," mPosition = "+mSelectedPosition);
            Log.e("log"," mytabView = "+selectedTitle);
            selectedTitle.measure(0,0);
            Log.e("log"," onMeasure = "+selectedTitle.getMeasuredWidth());
            Log.e("log","getLeft = "+selectedTitle.getLeft());

            if (selectedTitle.getMeasuredWidth() > 0) {
                Log.e("log","updateIndicatorPosition() ---- 在里面了");
                // 距离父容器左边距离
                left = selectedTitle.getLeft();
                // 距离父容器右边距离
                right = selectedTitle.getRight();

                if (mSelectionOffset > 0f && mSelectedPosition < getChildCount() - 1) {
                    // Draw the selection partway between the tabs
                    View nextTitle = getChildAt(mSelectedPosition + 1);
                    left = (int) (mSelectionOffset * nextTitle.getLeft() +
                            (1.0f - mSelectionOffset) * left);
                    right = (int) (mSelectionOffset * nextTitle.getRight() +
                            (1.0f - mSelectionOffset) * right);
                    Log.e("log","updateIndicatorPosition() ---- 在里面了33333");
                }
            } else {
                Log.e("log","left = right = -1");
                left = right = -1;
            }

            setIndicatorPosition(left, right);
        }

        void setIndicatorPosition(int left, int right) {
            if (left != mIndicatorLeft || right != mIndicatorRight) {
                // If the indicator's left/right has changed, invalidate
                mIndicatorLeft = left;
                mIndicatorRight = right;
                // 重新绘制的api 执行onDraw()-----
                Log.e("log","-----马上执行onDraw了------");
                ViewCompat.postInvalidateOnAnimation(this);
            }
            Log.e("log","---------尼玛 难道是这里嘛-------");
        }

        void animateIndicatorToPosition(final int position, int duration) {
            if (mIndicatorAnimator != null && mIndicatorAnimator.isRunning()) {
                mIndicatorAnimator.cancel();
            }

            // 布局从右到左--是否
            final boolean isRtl = ViewCompat.getLayoutDirection(this)
                    == ViewCompat.LAYOUT_DIRECTION_RTL;
            // 目标view -----
            final View targetView = getChildAt(position);
            if (targetView == null) {
                // 如果目标不存在view 那么只是更新当前的数值并且返回
                // If we don't have a view, just update the position now and return
                updateIndicatorPosition();
                return;
            }

            final int targetLeft = targetView.getLeft();
            final int targetRight = targetView.getRight();
            final int startLeft;
            final int startRight;

            if (Math.abs(position - mSelectedPosition) <= 1) {
                // If the views are adjacent, we'll animate from edge-to-edge
                startLeft = mIndicatorLeft;
                startRight = mIndicatorRight;
            } else {
                // Else, we'll just grow from the nearest edge
                final int offset = dpToPx(MOTION_NON_ADJACENT_OFFSET);
                if (position < mSelectedPosition) {
                    // We're going end-to-start
                    if (isRtl) {
                        startLeft = startRight = targetLeft - offset;
                    } else {
                        startLeft = startRight = targetRight + offset;
                    }
                } else {
                    // We're going start-to-end
                    if (isRtl) {
                        startLeft = startRight = targetRight + offset;
                    } else {
                        startLeft = startRight = targetLeft - offset;
                    }
                }
            }

            if (startLeft != targetLeft || startRight != targetRight) {
                ValueAnimator animator = mIndicatorAnimator = new ValueAnimator();
                animator.setInterpolator(new FastOutSlowInInterpolator());
                animator.setDuration(duration);
                animator.setFloatValues(0, 1);
                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animator) {
                        final float fraction = animator.getAnimatedFraction();
                        setIndicatorPosition(
                                lerp(startLeft, targetLeft, fraction),
                                lerp(startRight, targetRight, fraction));
                    }
                });
                animator.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animator) {
                        mSelectedPosition = position;
                        mSelectionOffset = 0f;
                    }
                });
                animator.start();
            }
        }

        float lerp(float startValue, float endValue, float fraction) {
            return startValue + (fraction * (endValue - startValue));
        }

        int lerp(int startValue, int endValue, float fraction) {
            return startValue + Math.round(fraction * (endValue - startValue));
        }

        @Override
        public void onRtlPropertiesChanged(int layoutDirection) {
            super.onRtlPropertiesChanged(layoutDirection);

            // Workaround for a bug before Android M where LinearLayout did not relayout itself when
            // layout direction changed.
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
                //noinspection WrongConstant
                if (mLayoutDirection != layoutDirection) {
                    requestLayout();
                    mLayoutDirection = layoutDirection;
                }
            }
        }

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            // 如果不是精确模式直接默认即可
            if (MeasureSpec.getMode(widthMeasureSpec) != MeasureSpec.EXACTLY) {
                // HorizontalScrollView will first measure use with UNSPECIFIED, and then with
                // EXACTLY. Ignore the first call since anything we do will be overwritten anyway
                return;
            }

            final int count = getChildCount();

            // First we'll find the widest tab
            int largestTabWidth = 0;
            for (int i = 0, z = count; i < z; i++) {
                View child = getChildAt(i);
                if (child.getVisibility() == VISIBLE) {
                    largestTabWidth = Math.max(largestTabWidth, child.getMeasuredWidth());
                }
            }

            if (largestTabWidth <= 0) {
                // If we don't have a largest child yet, skip until the next measure pass
                return;
            }

            final int gutter = dpToPx(FIXED_WRAP_GUTTER_MIN);
            boolean remeasure = false;

            if (largestTabWidth * count <= getMeasuredWidth() - gutter * 2) {
                // If the tabs fit within our width minus gutters, we will set all tabs to have
                // the same width
                for (int i = 0; i < count; i++) {
                    final LinearLayout.LayoutParams lp =
                            (LayoutParams) getChildAt(i).getLayoutParams();
                    if (lp.width != largestTabWidth || lp.weight != 0) {
                        lp.width = largestTabWidth;
                        lp.weight = 0;
                        remeasure = true;
                    }
                }
            } else {
                // If the tabs will wrap to be larger than the width minus gutters, we need
                // to switch to GRAVITY_FILL
                mTabGravity = GRAVITY_FILL;
                updateMyTabViews(false);
                remeasure = true;
            }

            if (remeasure) {
                // Now re-measure after our changes
                super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            }
        }

        @Override
        public void draw(Canvas canvas) {
            super.draw(canvas);

            // Thick colored underline below the current selection
            if (mIndicatorLeft >= 0 && mIndicatorRight > mIndicatorLeft) {
                canvas.drawRect(mIndicatorLeft, getHeight() - mSelectedIndicatorHeight,
                        mIndicatorRight, getHeight(), mSelectedIndicatorPaint);
            }
        }

    }

    int dpToPx(int dps) {
        return Math.round(getResources().getDisplayMetrics().density * dps);
    }


}
