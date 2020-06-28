package com.zxl.mydailytest.multi_thread;

import android.app.IntentService;
import android.content.Intent;
import android.annotation.SuppressLint;
import android.util.Log;

import androidx.annotation.Nullable;

import com.zxl.mydailytest.provider.library.ToastUtils;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class InitService extends IntentService {

    public InitService(String name) {
        super(name);
    }

    public InitService(){
        super("InitService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.e("启动加载", "onHandleIntent: "+Thread.currentThread().getName());
        try {
            // 模拟加载不需要在主线程运行的资源
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 初始化toast
        ToastUtils.init(this);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
