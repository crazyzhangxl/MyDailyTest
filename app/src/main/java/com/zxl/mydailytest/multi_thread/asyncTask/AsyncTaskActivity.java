package com.zxl.mydailytest.multi_thread.asyncTask;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.HandlerThread;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.zxl.mydailytest.R;

import java.lang.ref.WeakReference;

public class AsyncTaskActivity extends AppCompatActivity {

    private TextView mTextView;
    private Button btnStart,btnCancel;
    private ProgressTask mProgressTask;

    public static void show(Activity activity){
        activity.startActivity(new Intent(activity, AsyncTaskActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service2);
        mTextView = findViewById(R.id.tvProgress);
        btnCancel = findViewById(R.id.btnCancel);
        btnStart = findViewById(R.id.btnStart);

        // 不能重复执行啊
        mProgressTask = new ProgressTask(new WeakReference<>(mTextView));

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new InnerStaticClass(new WeakReference<>(AsyncTaskActivity.this)).showToast();
                mProgressTask.execute();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mProgressTask.cancel(true);
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mProgressTask != null && mProgressTask.getStatus() == AsyncTask.Status.RUNNING){
            mProgressTask.cancel(true);
            mProgressTask = null;
        }
    }

    public static class InnerStaticClass{
        private WeakReference<AsyncTaskActivity> mWeakReference;

        public InnerStaticClass (WeakReference<AsyncTaskActivity> weakReference){
            mWeakReference = weakReference;
        }


        public void showToast(){
            if (null != mWeakReference.get()){
                Toast.makeText(mWeakReference.get(),"我是谁",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
