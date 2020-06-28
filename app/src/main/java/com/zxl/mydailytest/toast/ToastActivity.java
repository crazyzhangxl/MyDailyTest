package com.zxl.mydailytest.toast;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.zxl.mydailytest.R;

public class ToastActivity extends AppCompatActivity {

    private static void onClick(View v) {
        ToastUtils.showShortText("1111");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toast);

        findViewById(R.id.btnShort).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showShortText("111");
            }
        });

        findViewById(R.id.btnLong).setOnClickListener(new PerfectClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                ToastUtils.showLongText("222");
            }
        });

        findViewById(R.id.normal).setOnClickListener(v -> Toast.makeText(ToastActivity.this, "3333", Toast.LENGTH_SHORT).show());

        findViewById(R.id.test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showToast("999",Toast.LENGTH_SHORT);
            }
        });
    }
}
