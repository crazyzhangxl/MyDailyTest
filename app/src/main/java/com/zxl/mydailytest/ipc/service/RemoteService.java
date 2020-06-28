package com.zxl.mydailytest.ipc.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.zxl.mydailytest.ipc.IInterface.IMusic;
import com.zxl.mydailytest.ipc.IInterface.IStopBackAction;
import com.zxl.mydailytest.ipc.IInterface.ProgressData;


public class RemoteService extends Service {
    public RemoteService() {

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("线程", Thread.currentThread().getId()+"");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new MusicClass();
    }


    private class MusicClass extends IMusic.Stub {

        @Override
        public void play() {
            Log.e("服务运行", "play: 音乐开启" );
        }

        @Override
        public void pause() {
            Log.e("服务运行", "play: 音乐暂停" );
        }

        @Override
        public void stop() {
            Log.e("服务运行", "play: 音乐结束" );
            // 回调Activity进行传值
            if (mIStopBackAction != null){
                try {
                    mIStopBackAction.stopCallBack(new ProgressData(100,"数据"));
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }

        IStopBackAction mIStopBackAction;

        @Override
        public void registerStop(IStopBackAction iStopBackAction) {
            this.mIStopBackAction = iStopBackAction;
        }
    }
}
