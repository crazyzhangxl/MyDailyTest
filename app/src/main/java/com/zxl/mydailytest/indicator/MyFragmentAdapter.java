package com.zxl.mydailytest.indicator;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

/**
 * @author crazyZhangxl on 2018/10/13.
 * Describe:
 */
public class MyFragmentAdapter extends FragmentPagerAdapter {
    private List<Fragment> mFragmentList;

    public MyFragmentAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        mFragmentList = list;
    }

    @Override
    public Fragment getItem(int position) {
        if (mFragmentList == null || mFragmentList.size() == 0) {
            return null;
        }
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        if (mFragmentList == null) {
            return 0;
        }
        return mFragmentList.size();
    }

}
