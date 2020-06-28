package com.zxl.mydailytest.ipc.service;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.zxl.mydailytest.R;
import com.zxl.mydailytest.ipc.IInterface.IMusic;
import com.zxl.mydailytest.ipc.IInterface.IStopBackAction;
import com.zxl.mydailytest.ipc.IInterface.ProgressData;
import com.zxl.mydailytest.multi_thread.asyncTask.AsyncTaskActivity;


/**
 * 通过广播从Service向Activity传值
 *
 *
 */
public class ServiceActivity extends AppCompatActivity {

    private ActyServiceConn mActyServiceConn;
    private IMusic mIMusic;

    public static void show(Activity activity){
        Intent intent = new Intent(activity,ServiceActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);


        findViewById(R.id.btnStartService).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService();
            }
        });

        findViewById(R.id.btnStopService).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService();
            }
        });

        findViewById(R.id.btnOpen).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mIMusic){
                    try {
                        mIMusic.play();
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }
        });


        findViewById(R.id.btnPause).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mIMusic){
                    try {
                        mIMusic.pause();
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        findViewById(R.id.btnEnd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mIMusic){
                    try {
                        mIMusic.stop();
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }


    private void startService(){
        Intent intent = new Intent();
        intent.setAction("com.zxl.service.remote");
        // 服务所在包名
        intent.setPackage("com.zxl.mydailytest");
        mActyServiceConn = new ActyServiceConn();
        bindService(intent,mActyServiceConn, Context.BIND_AUTO_CREATE);
    }

    private void stopService(){
        if (mActyServiceConn != null){
            unbindService(mActyServiceConn);
            mActyServiceConn = null;
        }
    }

    private class ActyServiceConn implements ServiceConnection{

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mIMusic = IMusic.Stub.asInterface(service);
            // 设置回调方法的方式,让service回调Activity方法
            // 记得注册的是 x.Stub()方法
            try {
                mIMusic.registerStop(new IStopBackAction.Stub() {
                    @Override
                    public void stopCallBack(ProgressData progressData) throws RemoteException {
                        Log.e("返回数据", "stopCallBack: "+progressData.getName() );
                        if (progressData.getProgress() == 100){
                            stopService();
                            ServiceActivity.this.finish();
                        }
                    }
                });
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("线程", Thread.currentThread().getId()+"");
    }

    @Override
    protected void onDestroy() {
        if (mActyServiceConn != null){
            stopService();
        }
        super.onDestroy();
    }
}
