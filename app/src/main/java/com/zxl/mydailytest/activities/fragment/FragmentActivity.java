package com.zxl.mydailytest.activities.fragment;

import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.zxl.mydailytest.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zxl on 2018/09/12.
 *         discription:
 */
public class FragmentActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        initViews();
        setViewPager();
    }

    private void setViewPager() {
        FragmentA fragmentA = new FragmentA();
        FragmentB fragmentB = new FragmentB();
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(fragmentA);
        fragmentList.add(fragmentB);
        ShowFragmentPagerAdapter adapter = new ShowFragmentPagerAdapter(getSupportFragmentManager(), fragmentList);
        // 设置最大缓存
        mViewPager.setOffscreenPageLimit(2);
        mViewPager.setAdapter(adapter);

    }


    private void initViews() {
        mViewPager = findViewById(R.id.vp);
    }
}
