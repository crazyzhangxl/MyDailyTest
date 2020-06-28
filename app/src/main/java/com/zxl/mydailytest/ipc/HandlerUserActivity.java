package com.zxl.mydailytest.ipc;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.zxl.mydailytest.R;

import java.lang.ref.WeakReference;

/**
 * 如何正确的使用handler
 */
public class HandlerUserActivity extends AppCompatActivity {
    private MyHandler mMyHandler;

    private static class MyHandler extends Handler{
        private WeakReference<HandlerUserActivity> mActivityWeakReference;

        public MyHandler(WeakReference<HandlerUserActivity> activityWeakReference) {
            mActivityWeakReference = activityWeakReference;
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                default:
                    break;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMyHandler = new MyHandler(new WeakReference<>(this));
        setContentView(R.layout.activity_handler_user);

        // 1.handler.sendMessage & 重写 handler {void handleMessage(Message message)}
        // 2.handler.post(Runnable runnable)
        // 3.runOnUiThread(Runnable run)
        //    if(主线程 Thread.currentThread == mUiThread?? ){
        //        run.run();
        //    }else{
        //        mHandler.post(run);
        //    }
        // 4.View.post(Runnable run){
        //     ....
        // }

    }

    @Override
    protected void onDestroy() {
        mMyHandler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }
}
