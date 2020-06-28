package com.zxl.mydailytest.activities.fragment;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zxl on 2018/9/12.
 *         discription:
 */

public class ShowFragmentPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> mFragmentList = new ArrayList<>();

    public ShowFragmentPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.mFragmentList = fragments;
    }


    @Override
    public Fragment getItem(int position) {
        if (mFragmentList == null) {
            return null;
        }else {
            return mFragmentList.get(position);
        }
    }

    @Override
    public int getCount() {
        if (mFragmentList == null){
            return 0;
        }else {
            return mFragmentList.size();
        }
    }
}
