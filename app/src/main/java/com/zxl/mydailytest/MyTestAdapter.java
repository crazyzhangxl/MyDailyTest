package com.zxl.mydailytest;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zxl.mydailytest.activities.ShowBean;

import java.util.List;

/**
 * @author zxl on 2018/8/4.
 *         discription:
 */

public class MyTestAdapter extends BaseQuickAdapter<ShowBean,BaseViewHolder> {
    public MyTestAdapter(int layoutResId, @Nullable List<ShowBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShowBean item) {
        TextView view = helper.getView(R.id.tv);
        TextView view2 = helper.getView(R.id.tv2);
        if (item.isShow()){
            view.setLines(10);
        }else {
            view.setLines(1);
        }

        if (item.isShow_suffix()){
            view2.setLines(10);
        }else {
            view2.setLines(1);
        }
        view.setText(item.getStr());
        view2.setText(item.getStr_suffix());
        helper.addOnClickListener(R.id.tv);
        helper.addOnClickListener(R.id.tv2);
    }
}
