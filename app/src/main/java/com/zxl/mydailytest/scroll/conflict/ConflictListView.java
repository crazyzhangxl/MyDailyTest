package com.zxl.mydailytest.scroll.conflict;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by apple on 2019-11-07.
 * description:
 */
public class ConflictListView extends ListView {
    public ConflictListView(Context context) {
        super(context);
    }

    public ConflictListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ConflictListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int measureSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >>2,MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, measureSpec);
    }
}
