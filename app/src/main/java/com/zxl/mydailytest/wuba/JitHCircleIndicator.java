package com.zxl.mydailytest.wuba;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.LinearLayout;

import androidx.annotation.AnimatorRes;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.zxl.mydailytest.R;


/**
 * @author crazyZhangxl on 2018/10/17.
 *
 * 自定义圆形 扩大动画指示器
 *
 * Describe: 自定义横线的圆形指示器,动画是选中变大
 *
 *
 * 需要手动设置控件的高度 大致是50dp
 */
public class JitHCircleIndicator extends LinearLayout{
    private final static int DEFAULT_INDICATOR_WIDTH = 5;// 默认的指示器的宽度
    // 指示器间距
    protected int mIndicatorMargin = -1;
    // 指示器宽
    protected int mIndicatorWidth = -1;
    // 指示器高
    protected int mIndicatorHeight = -1;

    protected Animator mAnimatorOut;

    protected Animator mAnimatorIn;

    protected Animator mImmediateAnimatorOut;

    protected Animator mImmediateAnimatorIn;

    @AnimatorRes
    int animatorResId = R.animator.scale_with_alpha;

    @AnimatorRes
    int animatorReverseResId = 0;

    // 选中的背景
    protected int mIndicatorBackgroundResId = R.drawable.white_radius;
    // 未选中的背景
    protected int mIndicatorUnselectedBackgroundResId = R.drawable.white_radius;

    protected int mLastPosition = -1;


    private ViewPager mViewpager;


    public JitHCircleIndicator(Context context) {
        this(context,null);
    }

    public JitHCircleIndicator(Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }

    public JitHCircleIndicator(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        int miniSize = (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                DEFAULT_INDICATOR_WIDTH, getResources().getDisplayMetrics()) + 0.5f);
        TypedArray typedArray =
                context.obtainStyledAttributes(attrs, R.styleable.JitHCircleIndicator);
        mIndicatorWidth =
                typedArray.getDimensionPixelSize(R.styleable.JitHCircleIndicator_ci_width, miniSize);
        mIndicatorHeight =
                typedArray.getDimensionPixelSize(R.styleable.JitHCircleIndicator_ci_height, miniSize);
        mIndicatorMargin =
                typedArray.getDimensionPixelSize(R.styleable.JitHCircleIndicator_ci_margin, miniSize);
        animatorResId = typedArray.getResourceId(R.styleable.JitHCircleIndicator_ci_animator,
                R.animator.scale_with_alpha);
        animatorReverseResId =
                typedArray.getResourceId(R.styleable.JitHCircleIndicator_ci_animator_reverse, 0);
        mIndicatorBackgroundResId =
                typedArray.getResourceId(R.styleable.JitHCircleIndicator_ci_drawable,
                        R.drawable.white_radius);
        mIndicatorUnselectedBackgroundResId =
                typedArray.getResourceId(R.styleable.JitHCircleIndicator_ci_drawable_unselected,
                        R.drawable.white_radius);
        typedArray.recycle();

        setOrientation(HORIZONTAL);

        // 扩大
        mAnimatorOut = AnimatorInflater.loadAnimator(getContext(), animatorResId);
        mImmediateAnimatorOut = AnimatorInflater.loadAnimator(getContext(), animatorResId);
        mImmediateAnimatorOut.setDuration(0);

        // 缩小( 动态   /  静态立即)
        mAnimatorIn = createAnimatorIn();
        mImmediateAnimatorIn = createAnimatorIn();
        mImmediateAnimatorIn.setDuration(0);
    }

    protected Animator createAnimatorIn() {
        Animator animatorIn;
        if (animatorReverseResId == 0) {
            animatorIn = AnimatorInflater.loadAnimator(getContext(), animatorResId);
            animatorIn.setInterpolator(new ReverseInterpolator());
        } else {
            animatorIn = AnimatorInflater.loadAnimator(getContext(), animatorReverseResId);
        }
        return animatorIn;
    }

    public void setViewPager(@Nullable ViewPager viewPager) {
        mViewpager = viewPager;
        if (mViewpager != null && mViewpager.getAdapter() != null) {
            mLastPosition = -1;
            createIndicators();
            mViewpager.removeOnPageChangeListener(mInternalPageChangeListener);
            mViewpager.addOnPageChangeListener(mInternalPageChangeListener);
            mInternalPageChangeListener.onPageSelected(mViewpager.getCurrentItem());
        }
    }

    private final ViewPager.OnPageChangeListener mInternalPageChangeListener =
            new ViewPager.OnPageChangeListener() {

                @Override public void onPageScrolled(int position, float positionOffset,
                                                     int positionOffsetPixels) {
                }

                @Override public void onPageSelected(int position) {

                    if (mViewpager.getAdapter() == null
                            || mViewpager.getAdapter().getCount() <= 0) {
                        return;
                    }
                    internalPageSelected(position);
                    mLastPosition = position;
                }

                @Override public void onPageScrollStateChanged(int state) {
                }
            };



    private void createIndicators() {
        removeAllViews();
        PagerAdapter adapter = mViewpager.getAdapter();
        int count;
        if (adapter == null || (count = adapter.getCount()) <= 0) {
            return;
        }
        createIndicators(count, mViewpager.getCurrentItem());
    }

    protected void createIndicators(int count, int currentPosition) {
        for (int i = 0; i < count; i++) {
            if (currentPosition == i) {
                addIndicator(mIndicatorBackgroundResId, mImmediateAnimatorOut);
            } else {
                addIndicator(mIndicatorUnselectedBackgroundResId,
                        mImmediateAnimatorIn);
            }
        }
    }

    /**
     * 添加指示器
     * @param indicatorBackgroundResId
     * @param animator
     */
    private void addIndicator(int indicatorBackgroundResId, Animator animator) {
        if (animator.isRunning()) {
            animator.end();
            animator.cancel();
        }
        View indicator = new View(getContext());
        indicator.setBackgroundResource(indicatorBackgroundResId);
        addView(indicator, mIndicatorWidth, mIndicatorHeight);
        LayoutParams lp = (LayoutParams) indicator.getLayoutParams();
        lp.leftMargin = mIndicatorMargin;
        lp.rightMargin = mIndicatorMargin;
        indicator.setLayoutParams(lp);
        animator.setTarget(indicator);
        animator.start();
    }


    /**
     * 设置目标的选中事件 ----
     * @param position
     */
    protected void internalPageSelected(int position) {

        if (mAnimatorIn.isRunning()) {
            mAnimatorIn.end();
            mAnimatorIn.cancel();
        }

        if (mAnimatorOut.isRunning()) {
            mAnimatorOut.end();
            mAnimatorOut.cancel();
        }

        View currentIndicator;
        if (mLastPosition >= 0 && (currentIndicator = getChildAt(mLastPosition)) != null) {
            currentIndicator.setBackgroundResource(mIndicatorUnselectedBackgroundResId);
            mAnimatorIn.setTarget(currentIndicator);
            mAnimatorIn.start();
        }

        View selectedIndicator = getChildAt(position);
        if (selectedIndicator != null) {
            selectedIndicator.setBackgroundResource(mIndicatorBackgroundResId);
            mAnimatorOut.setTarget(selectedIndicator);
            mAnimatorOut.start();
        }
    }

    /**
     * 倒置的插值器 原来是正的现在是反的了
     */
    protected class ReverseInterpolator implements Interpolator {
        @Override public float getInterpolation(float value) {
            return Math.abs(1.0f - value);
        }
    }
}
