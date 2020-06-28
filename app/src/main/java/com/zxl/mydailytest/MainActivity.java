package com.zxl.mydailytest;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.zxl.mydailytest.activities.EditTextActivity;
import com.zxl.mydailytest.activities.InflateActivity;
import com.zxl.mydailytest.activities.IntentFtActivity;
import com.zxl.mydailytest.activities.PopupWindowActivity;
import com.zxl.mydailytest.activities.background.BgActivity;
import com.zxl.mydailytest.activities.colorFliter.ImageFilterActivity;
import com.zxl.mydailytest.activities.fragment.FgActivity;
import com.zxl.mydailytest.activities.fragment.FragmentActivity;
import com.zxl.mydailytest.activities.fragment.NormalFgActivity;
import com.zxl.mydailytest.activities.grid.GridActivity;
import com.zxl.mydailytest.display.DisplayActivity;
import com.zxl.mydailytest.indicator.IndicatorActivity;
import com.zxl.mydailytest.indicator.IndicatorOneActivity;
import com.zxl.mydailytest.ipc.HandlerMsgActivity;
import com.zxl.mydailytest.ipc.HandlerRunActivity;
import com.zxl.mydailytest.ipc.service.ServiceActivity;
import com.zxl.mydailytest.layer.LayerActivity;
import com.zxl.mydailytest.multi_thread.asyncTask.AsyncTaskActivity;
import com.zxl.mydailytest.multi_thread.handler_thread.HandlerThreadActivity;
import com.zxl.mydailytest.notification.NotificationActivity;
import com.zxl.mydailytest.permission.ReviewPermissionActivity;
import com.zxl.mydailytest.provider.library.ToastUtils;
import com.zxl.mydailytest.recycle.RecyclerActivity;
import com.zxl.mydailytest.rxjava.RxLearnActivity;
import com.zxl.mydailytest.scroll.MyScrollActivity;
import com.zxl.mydailytest.scroll.ViewDispatchActivity;
import com.zxl.mydailytest.scroll.conflict.ConflictAActivity;
import com.zxl.mydailytest.scroll.conflict.ConflictActivity;
import com.zxl.mydailytest.scroll.conflict.ConflictBActivity;
import com.zxl.mydailytest.spannable.SpannableSbActivity;
import com.zxl.mydailytest.state_list_animator.ListAnimatorActivity;
import com.zxl.mydailytest.task.ActivityA;
import com.zxl.mydailytest.test.Test2Activity;
import com.zxl.mydailytest.toast.ToastActivity;
import com.zxl.mydailytest.viewpager.ViewPagerOneActivity;
import com.zxl.mydailytest.views.RetrofitNetActivity;
import com.zxl.mydailytest.webview.GoodWebActivity;
import com.zxl.mydailytest.webview.WebViewActivity;

import java.util.Arrays;

/**
 * @author zxl on 2018/07/13.
 *         discription:
 */
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "启动";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("启动模式", "MainActivity onCreate" );
        setContentView(R.layout.activity_main);
        getData();
        findViewById(R.id.btnJing).setOnClickListener(v -> startActivity(new Intent(MainActivity.this, ActivityA.class)));

        findViewById(R.id.btnTableNext).setOnClickListener(v -> startActivity(new Intent(MainActivity.this,TableActivity.class)));

        findViewById(R.id.location).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,LocationActivity.class));
            }
        });

        findViewById(R.id.btnSoft).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,SoftInputKeyActivity.class));
            }
        });

        findViewById(R.id.btnMP).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ChartsActivity.class));
            }
        });

        findViewById(R.id.btnMG).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ChartMGActivity.class));
            }
        });


        findViewById(R.id.btnFragment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FragmentActivity.class));
            }
        });

        findViewById(R.id.btnNet).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RetrofitNetActivity.class));
            }
        });

        findViewById(R.id.btnEt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, EditTextActivity.class));
            }
        });

        findViewById(R.id.btnImage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ImageFilterActivity.class));
            }
        });

        findViewById(R.id.btnListAnimator).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ListAnimatorActivity.class));
            }
        });

        findViewById(R.id.btnAnimation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,AnimationActivity.class));
            }
        });

        findViewById(R.id.btnIndicator).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, IndicatorOneActivity.class));
            }
        });

        findViewById(R.id.radioGroup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, IndicatorActivity.class));
            }
        });

        findViewById(R.id.btnTab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,MyTabActivity.class));
            }
        });

        findViewById(R.id.btnCb).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,CheckBoxActivity.class));
            }
        });

        findViewById(R.id.btnRv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RecyclerActivity.class));
            }
        });

        findViewById(R.id.fglifeCycle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, NormalFgActivity.class));
            }
        });

        findViewById(R.id.filter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, IntentFtActivity.class));
            }
        });

        findViewById(R.id.btnPop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, PopupWindowActivity.class));
            }
        });

        findViewById(R.id.btnGrid).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, GridActivity.class));
            }
        });

        findViewById(R.id.inflate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, InflateActivity.class));
            }
        });

        findViewById(R.id.btnBg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, BgActivity.class));
            }
        });

        findViewById(R.id.nextFg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FgActivity.class));
            }
        });

        findViewById(R.id.btnInit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.show("初始化过了");
            }
        });

        findViewById(R.id.btnHandler).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HandlerMsgActivity.show(MainActivity.this);
            }
        });

        findViewById(R.id.btnHandler2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HandlerRunActivity.show(MainActivity.this);
            }
        });

        findViewById(R.id.btnService).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ServiceActivity.show(MainActivity.this);
            }
        });

        findViewById(R.id.btnNormalWeb).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WebViewActivity.show(MainActivity.this);
            }
        });

        new Intent();

        findViewById(R.id.btnNiceWeb).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoodWebActivity.show(MainActivity.this);
            }
        });

        findViewById(R.id.next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  Main2Activity.show(MainActivity.this);
                Test2Activity.show(MainActivity.this);
            }
        });

        /*
         * 滑动专题处理
         */

        findViewById(R.id.btnScroll).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MyScrollActivity.class));
            }
        });


        findViewById(R.id.btnDv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ViewDispatchActivity.class));
            }
        });

        findViewById(R.id.btnConflict).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ConflictActivity.class));
            }
        });


        findViewById(R.id.btnConflictOne).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConflictAActivity.show(MainActivity.this);
            }
        });

        findViewById(R.id.btnConflictTwo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConflictBActivity.show(MainActivity.this);
            }
        });

        findViewById(R.id.viewPager1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewPagerOneActivity.show(MainActivity.this);
            }
        });

        findViewById(R.id.layer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LayerActivity.class));
            }
        });

        findViewById(R.id.btnSpannable).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SpannableSbActivity.show(MainActivity.this);
            }
        });

        findViewById(R.id.btnDisplay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, DisplayActivity.class));
            }
        });
        // ==================================================多线程 ========================================
        findViewById(R.id.async).setOnClickListener(v -> AsyncTaskActivity.show(MainActivity.this));

        findViewById(R.id.handlerThread).setOnClickListener(v -> HandlerThreadActivity.show(MainActivity.this));

        findViewById(R.id.btnNotify).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationActivity.show(MainActivity.this);
            }
        });

        findViewById(R.id.btnPermission).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReviewPermissionActivity.show(MainActivity.this);
            }
        });

        findViewById(R.id.rxOne).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RxLearnActivity.class));
            }
        });

        findViewById(R.id.toast).setOnClickListener(v -> startActivity(new Intent(MainActivity.this, ToastActivity.class)));

        View son = findViewById(R.id.toast);
        son.post(new Runnable() {
            @Override
            public void run() {
                Log.e("日志", "son" );
            }
        });
        View father = findViewById(R.id.scrollview);
        father.post(new Runnable() {
            @Override
            public void run() {
                Log.e("日志", "father" );
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("启动模式", "MainActivity onDestroy" );
    }

    private void getData() {
        String[] stringArray = getResources().getStringArray(R.array.city);
        Log.e("数据", Arrays.asList(stringArray).toString());
        //output: [北京, 上海, 南京]
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "MainActivity onPause: " );
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG, "MainActivity onStop: " );
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK){
//            moveTaskToBack(true);
//            return true;
//        }
        return super.onKeyDown(keyCode, event);
    }
}
