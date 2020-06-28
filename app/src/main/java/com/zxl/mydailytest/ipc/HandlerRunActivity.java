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

import com.zxl.mydailytest.R;

import java.lang.ref.WeakReference;

import javax.security.auth.callback.Callback;

public class HandlerRunActivity extends AppCompatActivity {
    private Button btnMsg;

    public static void show(Activity activity){
        Intent intent = new Intent(activity,HandlerRunActivity.class);
        activity.startActivity(intent);
    }


    private WeakReference<Handler> mHandler = new WeakReference<>(
            new Handler(new Handler.Callback() {
                @Override
                public boolean handleMessage(Message msg) {
                    if (msg.what == 1){
                        Log.e("处理线程", Thread.currentThread().getName());
                        Log.e("处理结果", "在处理了");
                    }
                    return true;
                }
            })
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_run);

        btnMsg = findViewById(R.id.btnMsg);
        btnMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Message message = Message.obtain();
                message.what = 1;
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Log.e("发送线程", Thread.currentThread().getName());
                        mHandler.get().sendMessage(message);
                    }
                }).start();
            }
        });
    }
}
