package com.zxl.mydailytest.indicator;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;


import com.zxl.mydailytest.R;

import java.util.ArrayList;

/**
 * @author crazyZhangxl on 2018-10-13 14:42:00.
 * Describe: 指示器1活动 --- 顶部指示器
 */

public class IndicatorOneActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {
    private ImageView ivGank;
    private ImageView ivBook;
    private ImageView ivMe;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indicator_one);
        initViews();
        setViewPager();
        setListener();
    }

    // 设置点击事件 --- 改变之前判断是否被选中了---
    private void setListener() {
        ivGank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //不然cpu会有损耗
                if (mViewPager.getCurrentItem() != 0) {
                    ivGank.setSelected(true);
                    ivBook.setSelected(false);
                    ivMe.setSelected(false);
                    mViewPager.setCurrentItem(0);
                }
            }
        });

        ivBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //不然cpu会有损耗
                if (mViewPager.getCurrentItem() != 1) {
                    ivGank.setSelected(false);
                    ivBook.setSelected(true);
                    ivMe.setSelected(false);
                    mViewPager.setCurrentItem(1);
                }
            }
        });

        ivMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //不然cpu会有损耗
                if (mViewPager.getCurrentItem() != 2) {
                    ivGank.setSelected(false);
                    ivBook.setSelected(false);
                    ivMe.setSelected(true);
                    mViewPager.setCurrentItem(2);
                }
            }
        });
    }

    private void setViewPager() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new GankFragment());
        fragments.add(new GankFragment());
        fragments.add(new MeFragment());
        mViewPager.setAdapter(new MyFragmentAdapter(getSupportFragmentManager(),fragments));
        mViewPager.setOffscreenPageLimit(2);
        // 设置viewpage滑动监听 ---- 当滑到某个界面时 标题背景改变
        mViewPager.addOnPageChangeListener(this);
        // 设置默认选中第一个 通过选中的背景设置切换
        ivGank.setSelected(true);
        mViewPager.setCurrentItem(0);
    }

    private void initViews() {
        ivGank = findViewById(R.id.iv_title_gank);
        ivBook = findViewById(R.id.iv_title_one);
        ivMe = findViewById(R.id.iv_title_dou);
        mViewPager = findViewById(R.id.viewPager);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position){
            case 0:
                ivGank.setSelected(true);
                ivBook.setSelected(false);
                ivMe.setSelected(false);
                break;
            case 1:
                ivGank.setSelected(false);
                ivBook.setSelected(true);
                ivMe.setSelected(false);
                break;
            case 2:
                ivGank.setSelected(false);
                ivBook.setSelected(false);
                ivMe.setSelected(true);
                break;
                default:
                    break;

        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
