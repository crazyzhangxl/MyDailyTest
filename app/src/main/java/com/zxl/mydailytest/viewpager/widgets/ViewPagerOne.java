package com.zxl.mydailytest.viewpager.widgets;

import android.content.Context;
import android.text.Layout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.zxl.mydailytest.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by apple on 2019-11-11.
 * description:
 */
public class ViewPagerOne extends ViewPager {

    public ViewPagerOne(Context context) {
        this(context,null);
    }

    public ViewPagerOne(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        List<String> list = new ArrayList<>();
        list.add("好好学习");
        list.add("努力奋斗");
        list.add("人生赢家");
        setAdapter(new ViewPagerAdapter(list));

    }


    private class ViewPagerAdapter extends PagerAdapter {

        private List<String> mData = new ArrayList<>();


        public ViewPagerAdapter(List<String> data) {
            mData = data;
        }

        @Override
        public int getCount() {
            return mData == null ? 0 : mData.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {

            return view.equals(object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = null;
            view =  LayoutInflater.from(getContext()).inflate(R.layout.layout_vp,null);
            TextView tvContent = view.findViewById(R.id.tvContent);
            tvContent.setText(mData.get(position));
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View)object;
            container.removeView(view);
        }
    }
}
