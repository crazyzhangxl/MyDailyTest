package com.zxl.mydailytest.timer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author crazyZhangxl on 2018/11/22.
 * Describe:
 */
public class CountingTimerView extends View {

    private static final String TWO_DIGITS_D = "%02d:";
    private static final String ONE_DIGIT_D = "%01d:";
    private static final String NEG_TWO_DIGITS_D = "-%02d:";
    private static final String NEG_ONE_DIGIT_D = "-%01d:";
    private static final String TWO_DIGITS = "%02d";
    private String mHours, mMinutes, mSeconds;
    private Paint mPaint = new Paint();

    public CountingTimerView(Context context) {
        super(context);
    }

    public CountingTimerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CountingTimerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * Update the time to display. Separates that time into the hours, minutes,
     * seconds and hundredths. If update is true, the view is invalidated so
     * that it will draw again.
     *
     * @param time
     *            new time to display - in milliseconds
     *            flag to show hundredths resolution
     * @param update
     *            to invalidate the view - otherwise the time is examined to see
     *            if it is within 100 milliseconds of zero seconds and when so,
     *            invalidate the view.
     */
    // TODO:showHundredths S/B attribute or setter - i.e. unchanging over object
    // life
    public void setTime(long time,boolean update) {
        boolean neg = false, showNeg = false;
        String format;
        if (time < 0) {
            time = -time;
            neg = showNeg = true;
        }
        long hundreds, seconds, minutes, hours;
        seconds = time / 1000;
        hundreds = (time - seconds * 1000) / 10;
        minutes = seconds / 60;
        seconds = seconds - minutes * 60;
        hours = minutes / 60;
        minutes = minutes - hours * 60;
        if (hours > 999) {
            hours = 0;
        }
        // The time can be between 0 and -1 seconds, but the "truncated"
        // equivalent time of hours
        // and minutes and seconds could be zero, so since we do not show
        // fractions of seconds
        // when counting down, do not show the minus sign.
        // TODO:does it matter that we do not look at showHundredths?
        if (hours == 0 && minutes == 0 && seconds == 0) {
            showNeg = false;
        }

        if (hours >= 10) {
            format = showNeg ? NEG_TWO_DIGITS_D : TWO_DIGITS_D;
            mHours = String.format(format, hours);
        } else if (hours > 0) {
            format = showNeg ? NEG_ONE_DIGIT_D : ONE_DIGIT_D;
            mHours = String.format(format, hours);
        } else {
            format = showNeg ? NEG_ONE_DIGIT_D : ONE_DIGIT_D;
            mHours = String.format(format, 0);
        }

        // Minutes are never empty and when hours are non-empty, must be two
        // digits
        if (minutes >= 10 || hours > 0) {
            format = (showNeg && hours == 0) ? NEG_TWO_DIGITS_D : TWO_DIGITS_D;
            mMinutes = String.format(format, minutes);
        } else {
            format = (showNeg && hours == 0) ? NEG_TWO_DIGITS_D : TWO_DIGITS_D;
            mMinutes = String.format(format, minutes);
        }
        mSeconds = String.format(TWO_DIGITS, seconds);
        if (update) {
            invalidate();
        }
    }

    @Override
    public void onDraw(Canvas canvas) {
        String result = mHours + mMinutes + mSeconds;
        mPaint.setTextSize(80);
        mPaint.setColor(Color.BLACK);
        float top = Math.abs(mPaint.getFontMetrics().top);
        float textWidth = mPaint.measureText(result);
        float x = (getMeasuredWidth() - textWidth) / 2;
        canvas.drawText(result, x, top, mPaint);

    }
}

