package com.zxl.mydailytest.recycle;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.zxl.mydailytest.R;

import java.util.List;

/**
 * @author crazyZhangxl on 2018/12/10.
 * Describe:
 */
public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewHolder> {
    public RecyclerListener mRecyclerListener;

    public interface RecyclerListener{
        void onFirstBtnClick(int position);
        void onSecondBtnClick(int position);
        void onAllCBtnClick(int position);
    }

    private Context mContext;
    private List<RecycleBean> mRecycleBeanList;

    public RecycleAdapter(Context context,RecyclerListener recyclerListener) {
        mContext = context;
        this.mRecyclerListener = recyclerListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_recyclerview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position, List<Object> payloads) {
        Log.e("局部", "onBindViewHolder: ");
        if (payloads.isEmpty()){
            onBindViewHolder(holder,position);
        }else {
            int flag = (int)payloads.get(0);
            switch (flag){
                case 1:
                    Log.e("局部", "看我刷新控制1咯");
                    holder.mTvFirst.setText("数据 "+ mRecycleBeanList.get(position).getFirst());
                    break;
                case 2:
                    holder.mTvSecond.setText("数据 "+ mRecycleBeanList.get(position).getSecond());
                    break;
                case 3:
                    onBindViewHolder(holder,position);
                    break;
                default:
                        break;
            }
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTvFirst.setText("数据 "+ mRecycleBeanList.get(position).getFirst());
        holder.mTvSecond.setText("数据 "+ mRecycleBeanList.get(position).getSecond());
        holder.mBtnFirst.setOnClickListener(v -> {
            if (mRecyclerListener != null){
                mRecyclerListener.onFirstBtnClick(position);
            }
        });

        holder.mBtnSecond.setOnClickListener(v -> {
            if (mRecyclerListener != null){
                mRecyclerListener.onSecondBtnClick(position);
            }
        });

        holder.mBtnTotal.setOnClickListener(v -> {
            if (mRecyclerListener != null){
                mRecyclerListener.onAllCBtnClick(position);
            }
        });
    }

    public void setData(List<RecycleBean> mList){
        mRecycleBeanList = mList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mRecycleBeanList == null){
            return 0;
        }else {
            return mRecycleBeanList.size();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTvFirst,mTvSecond;
        private Button mBtnFirst,mBtnSecond,mBtnTotal;
        public ViewHolder(View itemView) {
            super(itemView);
            mTvFirst = itemView.findViewById(R.id.tvFirst);
            mTvSecond = itemView.findViewById(R.id.tvSecond);
            mBtnFirst = itemView.findViewById(R.id.btnFirst);
            mBtnSecond = itemView.findViewById(R.id.btnSecond);
            mBtnTotal = itemView.findViewById(R.id.btnAll);
        }
    }
}
