package com.zxl.mydailytest.notification;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.os.Bundle;
import android.view.View;

import com.zxl.mydailytest.R;

public class NotificationActivity extends AppCompatActivity {

    /**
     * 展示弹窗活动
     * @param activity
     */
    public static void show(Activity activity){
        Intent intent = new Intent(activity,NotificationActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        findViewById(R.id.btnShowNormalNotify).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNormalNotify();
            }
        });

        findViewById(R.id.btnShowSecondNotify).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSecondNotify();
            }
        });

        findViewById(R.id.btnCancelNotify).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelNotify();
            }
        });
    }

    private void showNormalNotify(){
        String channelId = "ID_1";
        String channelName = "Name_1";
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        // 适配Android O 需要常见channel
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            //只在Android O之上需要渠道
            NotificationChannel notificationChannel = new NotificationChannel(channelId,
                    channelName, NotificationManager.IMPORTANCE_HIGH);
            //如果这里用IMPORTANCE_NOENE就需要在系统的设置里面开启渠道，
            //通知才能正常弹出
            notificationManager.createNotificationChannel(notificationChannel);
        }
        // 后面是常规操作...
        Notification notification = new NotificationCompat.Builder(this, channelId)
                .setContentTitle("通知标题")
                .setContentText("通知内容")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .build();
        notificationManager.notify(1,notification);
    }

    // todo 开启铃声 呼吸灯
    // NotificationChannel创建之后就无法主动修改铃声、震动等设置
    private void showSecondNotify(){
        String channelId = "ID_2";
        String channelName = "Name_2";
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        // 适配Android O 需要常见channel
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            //只在Android O之上需要渠道
            NotificationChannel notificationChannel = new NotificationChannel(channelId,
                    channelName, NotificationManager.IMPORTANCE_DEFAULT);
            //如果这里用IMPORTANCE_NOENE就需要在系统的设置里面开启渠道，
            //通知才能正常弹出
            //设置震动...
            notificationChannel.enableVibration(true);
            notificationChannel.setVibrationPattern(new long[]{100,200,100});

            notificationManager.createNotificationChannel(notificationChannel);
        }
        // 后面是常规操作...
        Notification notification = new NotificationCompat.Builder(this, channelId)
                .setContentTitle("通知标题")
                .setContentText("通知内容")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .build();
        notificationManager.notify(2,notification);
    }

    private void cancelNotify(){
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.cancelAll();
    }
}
