package com.zxl.mydailytest.indicator;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.zxl.mydailytest.R;
import com.zxl.mydailytest.activities.fragment.FragmentA;
import com.zxl.mydailytest.activities.fragment.FragmentB;
import com.zxl.mydailytest.views.WubaTownRadioGroup;
import com.zxl.mydailytest.wuba.JitHCircleIndicator;
import com.zxl.mydailytest.wuba.JumpCPagerIndicator;
import com.zxl.mydailytest.wuba.ViewPagerEx;

import java.util.ArrayList;
import java.util.List;

/**
 * @author crazyZhangxl on 2018-10-13 16:50:40.
 * Describe:
 */

public class IndicatorActivity extends AppCompatActivity {
    private RadioGroup mRadioGroup;
    private RadioButton rb1;
    private RadioButton rb2;

    private WubaTownRadioGroup mWubaTownRadioGroup;
    private RadioButton city;

    private JitHCircleIndicator mJitHCircleIndicator;
    private ViewPagerEx mViewPager;
    private JumpCPagerIndicator mJumpCPagerIndicator;

    private RadioGroup mRadioMain;
    private RadioButton mRadioAc1,mRadioAc2,mRadioAc3;
    private ImageView mIvLinear;


    private int lineWidth = 80;
    private int currentPage = -1;
    private int textWidth = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indicator);
        initGpMain();
        mRadioGroup = findViewById(R.id.radioGroup);
        rb1 = findViewById(R.id.rb1);
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

            }
        });

        mWubaTownRadioGroup = findViewById(R.id.city_hot_tab);
        city = findViewById(R.id.city);
        city.setChecked(true);
        mWubaTownRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

            }
        });
        mJitHCircleIndicator = findViewById(R.id.circleIndicator);
        mJumpCPagerIndicator = findViewById(R.id.jumpIndicator);

        mViewPager = findViewById(R.id.viewPager);
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new FragmentA());
        fragments.add(new FragmentB());
        fragments.add(new FragmentA());
        //fragments.add(new FragmentB());
       // fragments.add(new FragmentA());
        mViewPager.setAdapter(new MyFragmentAdapter(getSupportFragmentManager(),fragments));
        mViewPager.setCurrentItem(1);
        // 设置可不可以滑动的意思0
        mViewPager.setNoScroll(false);

        mJitHCircleIndicator.setViewPager(mViewPager);
        mJumpCPagerIndicator.setViewPager(mViewPager);

        // 设置了滑动监听了 ----
        // 当从页面一向页面二滑动时：
        // 我们可以发现第一个参数值直接从0->1，第二个参数值从0.0依次增加到0.9xx无限靠近1，然后页面到达第二页它又恢复成了0
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // 对于三个标签 那么一共只有四种情况
                //{1. 0->1 1->2 1->0 2->1}
                Log.e("onPageScrolled",position+","+positionOffset+","+positionOffsetPixels);

                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) mIvLinear.getLayoutParams();
                if (currentPage == 0 && position == 0){
                    //0 - > 1
                    layoutParams.leftMargin =(int)(getScreenWidthWidth()/6-lineWidth/2+positionOffset*getScreenWidthWidth()/3f);
                }else if (currentPage == 1 && position == 1){
                    layoutParams.leftMargin =(int)(getScreenWidthWidth()/2-lineWidth/2+positionOffset*getScreenWidthWidth()/3f);
                    // 1 -> 2
                } else if(currentPage == 1 && position == 0){
                    // 1 -> 0
                    layoutParams.leftMargin =(int)(getScreenWidthWidth()/2-lineWidth/2- (1-positionOffset)*getScreenWidthWidth()/3f);

                }else if (currentPage == 2 && position == 1){
                    // 2 -> 1
                    layoutParams.leftMargin =(int)(getScreenWidthWidth()*5/6-lineWidth/2-(1-positionOffset)*getScreenWidthWidth()/3f);
                }
                mIvLinear.setLayoutParams(layoutParams);
            }

            @Override
            public void onPageSelected(int position) {
                currentPage = position;
                switch (position){
                    case 0:
                        mRadioAc1.setChecked(true);
                        break;
                    case 1:
                        mRadioAc2.setChecked(true);
                        break;
                    case 2:
                        mRadioAc3.setChecked(true);
                        break;
                        default:
                            break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    /**
     * 获得屏幕宽度----
     * @return
     */
    private int getScreenWidthWidth(){
         // 获取显示屏信息
         Display display = getWindow().getWindowManager().getDefaultDisplay();
         // 得到显示屏宽度
         DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        return metrics.widthPixels;
    }

    private void initGpMain() {
        mRadioMain = findViewById(R.id.rgMain);
        mRadioAc1 = findViewById(R.id.rbAc1);
        mRadioAc2 = findViewById(R.id.rbAc2);
        mRadioAc3 = findViewById(R.id.rbAc3);
        mRadioAc1.setChecked(true);
        mRadioMain.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId){
                case R.id.rbAc1:
                    mRadioAc1.setChecked(true);
                    break;
                case R.id.rbAc2:
                    mRadioAc2.setChecked(true);
                    break;
                case R.id.rbAc3:
                    mRadioAc3.setChecked(true);
                    break;
                    default:
                        break;
            }
        });

        mIvLinear = findViewById(R.id.imageIndicator);
        //*****
        // 只有其父布局为LinearLatout时，才能够有 LinearLayoutParams,才能够有leftMargin 设置左边间距
        //*****
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) mIvLinear.getLayoutParams();
        layoutParams.width = lineWidth;
        layoutParams.leftMargin = getScreenWidthWidth()/6 - lineWidth/2;
        mIvLinear.setLayoutParams(layoutParams);
        // 后面会分析一些layoutParams的

    }

}
