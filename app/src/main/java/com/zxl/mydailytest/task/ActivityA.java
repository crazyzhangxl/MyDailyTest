package com.zxl.mydailytest.task;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.zxl.mydailytest.R;
import com.zxl.mydailytest.timer.CountingTimerView;
import com.kyleduo.switchbutton.SwitchButton;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author zxl on 2018/08/20.
 *         discription:
 */
public class ActivityA extends AppCompatActivity {
    private static final String TAG = "启动";
    private ToggleButton mToggleButton;
    private LinearLayout mLinearLayout;
    private CountingTimerView mTimeText;
    private SwitchButton mSwitchButton;
    private int time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity);
        Log.e(TAG, "ActivityA onCreate: ");

//        mSwitchButton = findViewById(R.id.switchButton);
//        mSwitchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                Toast.makeText(ActivityA.this,isChecked?"开":"关", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        mTimeText = findViewById(R.id.timer);
//        mTimeText.setTime(0, true);
//        Timer timer = new Timer();
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        time += 1000;
//                        mTimeText.setTime(time,true);
//                    }
//                });
//            }
//        },0,1000);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "ActivityA onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "ActivityA onResume: ");
    }


    public int getStatusBarHeight() {
        int result = 0;
        //获取状态栏高度的资源id
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

}
