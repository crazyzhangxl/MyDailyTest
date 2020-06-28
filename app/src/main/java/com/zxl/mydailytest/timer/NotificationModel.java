package com.zxl.mydailytest.timer;

/**
 * @author crazyZhangxl on 2018/11/22.
 * Describe:
 */
/**
 * Data that must be coordinated across all notifications is accessed via this model.
 */
final class NotificationModel {

    private boolean mApplicationInForeground;

    /**
     * @param inForeground {@code true} to indicate the application is open in the foreground
     */
    void setApplicationInForeground(boolean inForeground) {
        mApplicationInForeground = inForeground;
    }

    /**
     * @return {@code true} while the application is open in the foreground
     */
    boolean isApplicationInForeground() {
        return mApplicationInForeground;
    }

    /**
     * @return a value that identifies the stopwatch notification
     */
    int getStopwatchNotificationId() {
        return Integer.MAX_VALUE - 1;
    }

    /**
     * @return a value that identifies the notification for running/paused timers
     */
    int getUnexpiredTimerNotificationId() {
        return Integer.MAX_VALUE - 2;
    }

    /**
     * @return a value that identifies the notification for expired timers
     */
    int getExpiredTimerNotificationId() {
        return Integer.MAX_VALUE - 3;
    }
}
