package com.zxl.mydailytest.state_list_animator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.zxl.mydailytest.R;

/**
 * @author crazyZhangxl on 2018-10-8 11:01:23.
 * Describe:
 */

public class ListAnimatorActivity extends AppCompatActivity {
    private CheckBox mCheckBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_animator);
        mCheckBox = (CheckBox) findViewById(R.id.cb);
        mCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
