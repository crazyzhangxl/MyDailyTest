package com.zxl.mydailytest.scroll;

import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.SystemClock;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.zxl.mydailytest.JingActivity;
import com.zxl.mydailytest.MyDvRelativeLayout;
import com.zxl.mydailytest.NotificationUtils;
import com.zxl.mydailytest.R;
import com.zxl.mydailytest.test.Test2Activity;
import com.zxl.mydailytest.views.MyRedView;


import java.util.ArrayList;



/**
 * @author zxl on 2018/07/19.
 *         discription:
 */
public class ViewDispatchActivity extends AppCompatActivity {
    private MyRedView mRedView;
    private ImageView iv;
    private Button mButton;
    private MyDvRelativeLayout mRl;

    private int requestCode = (int) SystemClock.uptimeMillis();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_dispatch);
        mRedView = (MyRedView) findViewById(R.id.redView);
        iv =  findViewById(R.id.iv);
        mRedView.post(new Runnable() {
            @Override
            public void run() {
                Log.e("宽高","getWidth = "+mRedView.getWidth()+" getHeight = "+mRedView.getHeight());
                Log.e("宽高","scrollX = "+mRedView.getScrollX()+" getScrollY - "+mRedView.getScrollY());
            }
        });

        findViewById(R.id.shoot).setOnClickListener(v -> {
            Bitmap bitmap = loadBitmapFromView(mRedView);
            iv.setImageBitmap(bitmap);
        });

        findViewById(R.id.btnNotification).setOnClickListener(v -> {
            sendNotification();

        });

        findViewById(R.id.btnCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ViewDispatchActivity.this, "取消", Toast.LENGTH_SHORT).show();
                new NotificationUtils.Build(ViewDispatchActivity.this).cancelTargetNf("1");
            }
        });

        initViewsListener();
    }

    private void initViewsListener() {
        mButton = findViewById(R.id.button);
        mRl = findViewById(R.id.rl);
        mButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        break;
                    case MotionEvent.ACTION_UP:
                        break;
                    case MotionEvent.ACTION_MOVE:
                        break;

                }
                Log.e("事件分发", "onTouch: "+event.getAction());
                return false;
            }
        });

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("事件分发", "OnClickListener:");
                startActivity(new Intent(ViewDispatchActivity.this, Test2Activity.class));
            }
        });


    }


    private void sendNotification() {
       // notify_mailbox();
        notifyThis();
    }

    private void notify_mailbox() {
        //设置想要展示的数据内容
        Intent intent = new Intent(this, JingActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pIntent = PendingIntent.getActivity(this,
                1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        int largeIcon = R.mipmap.ic_launcher;
        int smallIcon = R.mipmap.ic_launcher;
        String ticker = "您有一条新通知";
        String title = "冰冰";
        ArrayList<String> messageList = new ArrayList<String>();
        messageList.add("文明,今晚有空吗？");
        messageList.add("晚上跟我一起去玩吧?");
        messageList.add("怎么不回复我？？我生气了！！");
        messageList.add("我真生气了！！！！！你听见了吗!");
        messageList.add("文明，别不理我！！！");
        String content = "[" + messageList.size() + "条]" + title + ": " + messageList.get(0);
        //实例化工具类，并且调用接口
    }

    private void notifyThis(){
        new NotificationUtils.Build(this).setSendId("1")
                .setSendName("小李")
                .setSendMsg("你是谁")
                .build();
    }



    private Bitmap loadBitmapFromView(View v) {
        if (v == null) {
            return null;
        }
        Bitmap screenshot;
        // 已经把这个view给拷贝了一份了
        screenshot = Bitmap.createBitmap(v.getWidth(), v.getHeight(), Bitmap.Config.ARGB_8888);
        Log.e("宽高1","scrollX = "+v.getScrollX()+" getScrollY - "+v.getScrollY());
        Canvas c = new Canvas(screenshot);
        c.translate(-v.getScrollX(), -v.getScrollY());
        v.draw(c);
        Log.e("宽高2","bitmap宽 = "+screenshot.getWidth()+" bitmap高 ="+screenshot.getHeight());
        return screenshot;
    }

}
