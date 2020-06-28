package com.zxl.mydailytest;

import android.app.IntentService;
import android.graphics.Canvas;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by apple on 2020/6/5.
 * description:
 */
public class LastItemDecoration extends RecyclerView.ItemDecoration {
    private int lastCount;

    public LastItemDecoration(int lastCount) {
        this.lastCount = lastCount;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        int childCount = parent.getChildCount();
        View lastSeenView =  parent.getChildAt(childCount - 1);
        int lastSeenPosition = parent.getChildAdapterPosition(lastSeenView);
        if (lastCount == lastSeenPosition){
            // 最后一个
        }
    }
}
