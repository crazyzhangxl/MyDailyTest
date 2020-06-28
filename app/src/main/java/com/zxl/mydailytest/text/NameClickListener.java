package com.zxl.mydailytest.text;

import android.util.Log;

/**
 * @author zxl on 2018/8/15.
 *         discription:
 */

public class NameClickListener implements ISpanClick {
    private String name;

    public NameClickListener(String name) {
        this.name = name;
    }

    @Override
    public void onClick(int position) {
        Log.e("数据","第几个: "+position+"  "+name);
    }
}
