package com.zxl.mydailytest.scroll.conflict;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zxl.mydailytest.R;

import java.util.List;

/**
 * Created by apple on 2019-11-07.
 * description:
 */
public class LvAdapter extends BaseAdapter {
    private List<String> mList;
    private Context mContext;

    public LvAdapter(List<String> list, Context context) {
        mList = list;
        mContext = context;
    }

    @Override
    public int getCount() {
        if (mList == null){
            return  0;
        }
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        if (mList == null){
            return null;
        }
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        if (mList == null){
            return 0;
        }
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null){
            convertView =  LayoutInflater.from(mContext).inflate(R.layout.item_lv_str,null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tvContent.setText(mList.get(position));
        return convertView;
    }

    private final class ViewHolder{
        private TextView tvContent;
        public ViewHolder(View view){
            tvContent = view.findViewById(R.id.tvContent);
        }
    }
}
