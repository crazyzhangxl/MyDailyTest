package com.zxl.mydailytest.ipc;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.zxl.mydailytest.R;

public class HandlerMsgActivity extends AppCompatActivity {
    private Button mBtnHandle;

    public static void show(Activity activity){
        Intent intent = new Intent(activity,HandlerMsgActivity.class);
        activity.startActivity(intent);
    }

    private Handler mHandler = new Handler();
    private Message message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_msg);

        mBtnHandle = findViewById(R.id.btnSend);
        message = Message.obtain(mHandler, new Runnable() {
            @Override
            public void run() {
                Log.e("线程处理线程", Thread.currentThread().getName() );
                Log.e("处理结果", "处理了");
            }
        });
        mBtnHandle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 点击事件 启用一个线程去模拟耗时操作
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Log.e("消息发送线程", Thread.currentThread().getName() );
                        mHandler.sendMessage(message);
                    }
                }).start();
            }
        });
    }
}
