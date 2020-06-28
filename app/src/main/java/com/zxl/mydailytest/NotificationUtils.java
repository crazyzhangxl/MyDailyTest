package com.zxl.mydailytest;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.core.app.NotificationCompat;

import com.zxl.mydailytest.scroll.MyScrollActivity;

import java.util.Random;

import static android.app.NotificationManager.IMPORTANCE_HIGH;
import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * @author ChayChan
 * @description: 通知栏工具
 * @date 2017/9/26  9:51
 */

public class NotificationUtils {

    private static final Random RANDOM = new Random();
    //提醒（响铃震动）的周期
    private static final long REMIND_PERIOD = 5 * 1000;

    //通知栏上次提醒时间
    private static long mNotificationRemindTime;
    //前台上次提醒时间
    private static long mForegroundRemindPreTime;

    private static NotificationManager nm;
    private NotificationUtils(String sendID,String sendName,String sendMsg,Context context){

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context,sendID);
        //可以让通知显示在最上面
        mBuilder.setPriority(IMPORTANCE_HIGH)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true);

        if (shouldRemind(true)) {
            mBuilder.setDefaults(Notification.DEFAULT_ALL);
            //使用默认的声音、振动、闪光
        }

        Bitmap largeIcon = BitmapFactory.decodeResource(
                context.getResources(), R.mipmap.ic_launcher);
        sendMsg = sendName + ":" + sendMsg; //内容为 xxx:内容
        //        int unreadCount = RongIMClient.getInstance().getUnreadCount(conversationType, targetId);
        //        if (unreadCount > 1) {
        //            //如果未读数大于1，则还有拼接上[x条]
        //            String num = String.format(UiUtils.getString(R.string.notification_num_format), unreadCount);
        //            content = num + content;//内容为 [x条] xxx:内容
        //        }

        mBuilder.setLargeIcon(largeIcon)
                .setContentTitle(sendName);

        Intent intent = new Intent(context, MyScrollActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, getRandomNum(),
                intent, PendingIntent.FLAG_UPDATE_CURRENT);

        //通知首次出现在通知栏，带上升动画效果的
        mBuilder.setTicker(sendMsg);
        //内容
        mBuilder.setContentText(sendMsg);

        mBuilder.setContentIntent(pendingIntent);
        Notification notification = mBuilder.build();

        int notifyId = 0;
        try {
            notifyId = Integer.parseInt(sendID);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            notifyId = -1;
        }

        //弹出通知栏
        nm.notify(notifyId, notification);
    }

    public static class Build {
        private Context mContext;
        private String sendId;
        private String sendName;
        private String sendMsg;
        public Build(Context context){
            this.mContext = context;
            if (nm == null){
                nm = (NotificationManager) context
                        .getSystemService(Activity.NOTIFICATION_SERVICE);
            }
        }

        public Build setSendId(String sendId){
            this.sendId = sendId;
            return this;
        }

        public Build setSendName(String sendName){
            this.sendName = sendName;
            return this;
        }

        public Build setSendMsg(String sendMsg){
            this.sendMsg = sendMsg;
            return this;
        }

        public void cancelTargetNf(String targetID){
            if (nm != null){
                nm.cancel(Integer.parseInt(targetID));
            }
        }

        public NotificationUtils build(){
            return new NotificationUtils(sendId,sendName,sendMsg,mContext);
        }

    }


    public static void showNotification(Context context,
                                        String conversationType,
                                        String targetId,
                                        String avatar,
                                        String name,
                                        String content) {
        NotificationManager notificationManager = (NotificationManager)
                context.getSystemService(NOTIFICATION_SERVICE);


        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context,"111");
        //可以让通知显示在最上面
        mBuilder.setPriority(IMPORTANCE_HIGH);
        mBuilder.setSmallIcon(R.mipmap.ic_launcher);
        mBuilder.setWhen(System.currentTimeMillis());
        mBuilder.setAutoCancel(true);

        if (shouldRemind(true)) {
            mBuilder.setDefaults(Notification.DEFAULT_ALL);
            //使用默认的声音、振动、闪光
        }

        Bitmap largeIcon = BitmapFactory.decodeResource(
                context.getResources(), R.mipmap.ic_launcher);
        content = name + ":" + content; //内容为 xxx:内容
        //        int unreadCount = RongIMClient.getInstance().getUnreadCount(conversationType, targetId);
        //        if (unreadCount > 1) {
        //            //如果未读数大于1，则还有拼接上[x条]
        //            String num = String.format(UiUtils.getString(R.string.notification_num_format), unreadCount);
        //            content = num + content;//内容为 [x条] xxx:内容
        //        }

        mBuilder.setLargeIcon(largeIcon);

        mBuilder.setContentTitle(name);

        Intent intent = new Intent(context,MyScrollActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, getRandomNum(),
                intent, PendingIntent.FLAG_UPDATE_CURRENT);

        //通知首次出现在通知栏，带上升动画效果的
        mBuilder.setTicker(content);
        //内容
        mBuilder.setContentText(content);

        mBuilder.setContentIntent(pendingIntent);
        Notification notification = mBuilder.build();

        int notifyId = 0;
        try {
            notifyId = Integer.parseInt(targetId);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            notifyId = -1;
        }

        //弹出通知栏
        notificationManager.notify(notifyId, notification);
    }


    /**
     * 判断是否需要提醒，根据是否超过周期
     *
     * @return
     */
    private static boolean shouldRemind(boolean isNotification) {
        if (isNotification) {
            if (System.currentTimeMillis() - mNotificationRemindTime >= REMIND_PERIOD) {
                mNotificationRemindTime = System.currentTimeMillis();
                return true;
            }
            return false;
        }

        //如果是判断前台提醒
        if (System.currentTimeMillis() - mForegroundRemindPreTime >= REMIND_PERIOD) {
            mForegroundRemindPreTime = System.currentTimeMillis();
            return true;
        }

        return false;
    }




    public static int getRandomNum() {
        return RANDOM.nextInt(Integer.MAX_VALUE);
    }
}
