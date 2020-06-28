package com.zxl.mydailytest;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/**
 * @author crazyZhangxl on 2018-11-28 14:04:42.
 * Describe: checkBox活动
 */

public class CheckBoxActivity extends AppCompatActivity {

    private CheckBox mCheckBox;
    private View rlCheck;
    private boolean isChecked = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_box);

        initViews();
    }

    private void initViews() {
        mCheckBox = findViewById(R.id.innerCheck);
        rlCheck = findViewById(R.id.rlCheck);
        mCheckBox.setChecked(isChecked);
        // 父亲布局
        rlCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isChecked = !isChecked;
                mCheckBox.setChecked(isChecked);
            }
        });

        findViewById(R.id.linear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CheckBoxActivity.this, "线性布局点击事件", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
