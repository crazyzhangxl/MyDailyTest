package com.zxl.mydailytest;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.zxl.mydailytest.views.DoStateView;
import com.zxl.mydailytest.views.MyTabLayout;

/**
 * @author crazyZhangxl on 2018-10-18 17:27:20.
 * Describe:
 */

public class MyTabActivity extends AppCompatActivity implements DoStateView.OnSelecedListener {
    private DoStateView mDoStateView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_tab);
        MyTabLayout myTabLayout = findViewById(R.id.mytab);
        myTabLayout.setTabInit();
        mDoStateView = findViewById(R.id.stateView);
        mDoStateView.setOnSelecedListener(this);
    }

    @Override
    public void onSelectedOnChanged(boolean selectState) {

    }
}
