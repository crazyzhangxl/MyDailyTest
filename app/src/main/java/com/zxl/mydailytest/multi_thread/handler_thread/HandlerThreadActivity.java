package com.zxl.mydailytest.multi_thread.handler_thread;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zxl.mydailytest.R;

public class HandlerThreadActivity extends AppCompatActivity {
    private TextView tvResult;
    private Button mBtnOne;
    private HandlerThread mHandlerThread;
    private Handler mHandler;

    private Handler mOutHandler = new Handler();

    public static void show(Activity activity){
        Log.e("日志", "展示" );
        activity.startActivity(new Intent(activity,HandlerThreadActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_thread);
        initHandlerThread();
        initViews();
        initListener();
        //test();
    }

    private void test(){
        Log.e("日志", "执行测试");
        mOutHandler.post(new Runnable() {
            @Override
            public void run() {
                mOutHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        Log.e("日志", "我是张豹爷" );
                    }
                });
                Log.e("日志", "吴志明睡着了");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.e("日志", "吴志明睡醒了" );
            }
        });

        mBtnOne.post(new Runnable() {
            @Override
            public void run() {

            }
        });
    }

    private void initHandlerThread() {
        mHandlerThread = new HandlerThread("work");
        // 开启线程...
        mHandlerThread.start();
        mHandler = new Handler(mHandlerThread.getLooper()){
            @Override
            public void handleMessage(Message msg) {
                // 消息处理很明显此时还是在子线程中呢....
                // work 处理消息运行于  子线程work中
                // 想要更新UI还得再post到 main线程
                Log.e("handlerThread", Thread.currentThread().getName()+"");
                super.handleMessage(msg);
            }
        };

    }

    // 记得退出handler
    private void quitHandler(){
        mHandlerThread.quit();
    }

    private void initListener() {
        mBtnOne.setOnClickListener(v -> {
            // 发送消息
            if (mHandler !=null){
                mHandler.sendEmptyMessage(0);
            }
        });
    }

    private void initViews() {
        tvResult = findViewById(R.id.tvResult);
        mBtnOne = findViewById(R.id.btnActionOne);
    }
}
