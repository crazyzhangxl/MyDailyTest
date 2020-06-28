package com.zxl.mydailytest.activities.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.zxl.mydailytest.R;


/**
 * @author crazyZhangxl on 2018-12-20 13:27:13.
 * Describe:
 */

public class NormalFgActivity extends AppCompatActivity {
    private static final String TAG = "Activity";
    private FrameLayout mFmContent;
    private Button mBtn;
    private int index = 1;
    private Fragment mFragmentA;
    private Fragment mFragmentB;

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "onResume: ");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "onCreate: ");
        setContentView(R.layout.activity_normal_fg);
        initViews();
        initData();
        initListener();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG, "onStop: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "onPause: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy: ");
    }

    private void initListener() {
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, "onClick: " );
                showWhichFragment(index);
            }
        });
    }

    private void initData() {
        showWhichFragment(index);
    }

    private void initViews() {
        mFmContent = findViewById(R.id.fgContent);
        mBtn = findViewById(R.id.btnChange);
    }

    private void showWhichFragment(int which){
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        if (mFragmentA != null){
            fragmentTransaction.hide(mFragmentA);
        }

        if (mFragmentB != null){
            fragmentTransaction.hide(mFragmentB);
        }
        if (which == 1){
            if (mFragmentA == null){
                mFragmentA = new FragmentA();
                fragmentTransaction.add(R.id.fgContent,mFragmentA);
            }else {
                fragmentTransaction.show(mFragmentA);
            }
            index = 2;
        }else {
            if (mFragmentB == null){
                mFragmentB = new FragmentB();
                fragmentTransaction.add(R.id.fgContent,mFragmentB);
            }else {
                fragmentTransaction.show(mFragmentB);
            }
            index = 1;
        }
        fragmentTransaction.commit();
    }
}
