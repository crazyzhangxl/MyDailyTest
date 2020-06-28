package com.zxl.mydailytest;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author zxl on 2018/08/06.
 *         discription:
 */
public class ChartsActivity extends AppCompatActivity {
    private LineChart mLineChart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charts);
        initViews();
        setLineChart();
    }

    private void setLineChart() {
        // 设置无数据时的显示状态 ------
        mLineChart.setNoDataText("Sorry,暂无数据啊");
        mLineChart.setDescription(null);
        // 不显示表格颜色
        mLineChart.setDrawGridBackground(false);
        // 不显示y轴右边的值
        mLineChart.getAxisRight().setEnabled(false);
        // 不显示图例  ===== 修改图例也是在这里设置的吧

        // 设置可触碰的手势
        mLineChart.setTouchEnabled(true);
        // enable scaling and dragging
        mLineChart.setDragEnabled(true);
        mLineChart.setScaleEnabled(true);

        Legend legend = mLineChart.getLegend();
        legend.setEnabled(false);

        // 设置X轴的样式
        XAxis xAxis = mLineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setAxisLineColor(Color.GREEN);
        xAxis.setAxisLineWidth(2);
        // 是否设置网格线 --- 和x轴有关 ;
        // 效果: 如若为true : 那么每一条y轴显示的刻度线都会画出来的
        xAxis.setDrawGridLines(false);
        // 设置是否显示x轴
        xAxis.setEnabled(true);



        MyMarkerView mv = new MyMarkerView(this, R.layout.custom_marker_view);
        mv.setChartView(mLineChart); // For bounds control
        mLineChart.setMarker(mv); // Set the marker to the chart

        // 设置左边Y轴
        YAxis yAxis = mLineChart.getAxisLeft();
        yAxis.setAxisLineColor(Color.RED);
        yAxis.setAxisLineWidth(2);
        yAxis.setDrawGridLines(false);
        yAxis.setEnabled(true);
        // y轴设置了最小值和最大值 当然这些值时肯定会在y刻度上显示的
        // 当设定的值大于这些值那么会适配即超过设定的这些值的
        yAxis.setAxisMinimum(0);


        List<String> xValues = new ArrayList<>();
        for (int i=0;i<3;i++){
            xValues.add(""+i);
        }

        // 这个是数值
        List<Entry> mValues = new ArrayList<>();
        mValues.add(new Entry(0,4f));
        mValues.add(new Entry(1,12f));
        mValues.add(new Entry(2,2f));
        mValues.add(new Entry(3,5f));
        mValues.add(new Entry(4,5f));
        mValues.add(new Entry(5,5f));
        mValues.add(new Entry(6,5f));
        mValues.add(new Entry(7,5f));

        // 这个后面会写的
        if (mLineChart.getData() != null &&
                mLineChart.getData().getDataSetCount() > 0){
            // 刷新
        }else {
            // 设置数据
        }


        // 设置折线的样式 ==== 这个很重要
        LineDataSet dataSet = new LineDataSet(mValues, "你猜猜");
        // 设置曲线颜色
        dataSet.setColor(Color.RED);
        // 设置平滑曲线
        dataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        dataSet.setDrawCircles(true);
        // 设置点的颜色
        dataSet.setCircleColor(Color.GREEN);
        // 设置点的半径
        dataSet.setCircleRadius(2f);
        // 不显示坐标点的数据(----坐标点的数据(--)----)
        dataSet.setDrawValues(false);
        // 设置点为实心的  true为空洞的
        dataSet.setDrawCircleHole(false);
        // 不显示定位线
        dataSet.setHighlightEnabled(false);

        /*
        * 判断的好处是公有属性可以提取出来
        * */

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(dataSet);
        LineData lineData = new LineData(dataSets);

/*        mLineChart.getXAxis().setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return null;
            }
        });*/
        mLineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {

            }

            @Override
            public void onNothingSelected() {

            }
        });


        mLineChart.setData(lineData);
        mLineChart.invalidate();
    }

    private void initViews() {
        mLineChart = findViewById(R.id.lineChart);
    }
}
