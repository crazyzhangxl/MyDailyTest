package com.zxl.mydailytest;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zxl on 2018/08/07.
 *         discription:
 */
public class ChartMGActivity extends AppCompatActivity {
    private LineChart mLineChart;
    private Button mBtnClick;
    private LinearChartManager mLinearChartManager;
    private List<IncomeBean> mIncomeBeans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart_mg);
        mLineChart = findViewById(R.id.lineChart);
        mBtnClick = findViewById(R.id.btnClick);
        mLinearChartManager = new LinearChartManager(mLineChart);
        mIncomeBeans = new ArrayList<>();
        mIncomeBeans.add(new IncomeBean("20180501",2f));
        mIncomeBeans.add(new IncomeBean("20180503",5f));
        mIncomeBeans.add(new IncomeBean("20180506",7f));

        mIncomeBeans.add(new IncomeBean("20180507",1f));
        mIncomeBeans.add(new IncomeBean("20180510",5f));

        mIncomeBeans.add(new IncomeBean("20180511",7f));
        mIncomeBeans.add(new IncomeBean("201806012",2f));

        mIncomeBeans.add(new IncomeBean("20180911",9f));
        mIncomeBeans.add(new IncomeBean("20180912",6f));

        mIncomeBeans.add(new IncomeBean("201810011",4f));
        mIncomeBeans.add(new IncomeBean("20181113",7f));

        mIncomeBeans.add(new IncomeBean("20181115",3f));
        mIncomeBeans.add(new IncomeBean("20181117",6f));

        mIncomeBeans.add(new IncomeBean("20181118",2f));
        mLinearChartManager.showLineChart(mIncomeBeans,"溶解氧", Color.BLUE);
        //设置曲线填充色 以及 MarkerView
        Drawable drawable = getResources().getDrawable(R.drawable.fade_blue);
        mLinearChartManager.setChartFillDrawable(drawable);
        mLinearChartManager.setMarkerView(this);

        initListener();
    }

    private void initListener() {
        mBtnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIncomeBeans.add(new IncomeBean("20181201",7f));
                mIncomeBeans.add(new IncomeBean("20181203",7f));
                mIncomeBeans.add(new IncomeBean("20181205",7f));
                mLinearChartManager.showLineChart(mIncomeBeans,"溶解氧", Color.BLUE);
                //设置曲线填充色 以及 MarkerView
                Drawable drawable = getResources().getDrawable(R.drawable.fade_blue);
                mLinearChartManager.setChartFillDrawable(drawable);
                mLinearChartManager.setMarkerView(ChartMGActivity.this);
            }
        });


    }
}
