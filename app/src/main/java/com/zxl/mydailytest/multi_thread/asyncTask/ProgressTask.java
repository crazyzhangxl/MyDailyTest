package com.zxl.mydailytest.multi_thread.asyncTask;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import java.lang.ref.WeakReference;

/**
 * Created by apple on 2019-12-06.
 * description:
 */
public class ProgressTask extends AsyncTask<String,Integer,String> {
    private WeakReference<TextView> tvText;

    public ProgressTask(WeakReference<TextView> tvText) {
        this.tvText = tvText;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (tvText.get() != null){
            tvText.get().setText("暂未启动");
        }
    }

    @Override
    protected String doInBackground(String... strings) {
        int progress = 0;
        while (progress < 100){
            if (isCancelled()){
                break;
            }
            publishProgress(progress);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            progress ++;
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        if (isCancelled()){
            return;
        }
        if (tvText.get() != null){
            tvText.get().setText("进度 "+values[0]);
        }
    }

    @Override
    protected void onPostExecute(String s) {
        if (tvText.get() != null){
            tvText.get().setText("加载完毕");
        }
    }

    @Override
    protected void onCancelled() {
        Log.e("服务", "onCancelled: 回掉了" );
        if (tvText.get() != null){
            tvText.get().setText("取消啦");
        }
    }
}
