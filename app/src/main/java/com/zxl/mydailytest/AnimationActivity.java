package com.zxl.mydailytest;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.zxl.mydailytest.wuba.dialog.TransitionDialog;


/**
 * @author crazyZhangxl on 2018-10-10 14:38:34.
 * Describe: 动画学习 -----
 */

public class AnimationActivity extends AppCompatActivity {
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        initViews();
    }

    private void initViews() {
        mImageView = findViewById(R.id.iv);
        findViewById(R.id.btnTurnLeft).setOnClickListener(v -> turnLeft());
        findViewById(R.id.btnTurnRight).setOnClickListener(v -> turnRight());
    }

    private void turnRight() {
        // x轴从a扩展到b y轴从a扩展到b  ; 相对点
        ScaleAnimation scaleAnimation = new ScaleAnimation(0.2f, 1.2f, 0.2f, 1.2f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setFillAfter(true);
        scaleAnimation.setDuration(1000);
        mImageView.startAnimation(scaleAnimation);
    }

    private void turnLeft() {
//        // y轴不变,x轴从起始点移动到左边
//        TranslateAnimation translateAnimation = new TranslateAnimation(Animation.ABSOLUTE, 0,
//                Animation.RELATIVE_TO_PARENT, -1,
//                Animation.ABSOLUTE, 0,
//                Animation.ABSOLUTE, 0);
//        translateAnimation.setFillAfter(true);
//        translateAnimation.setDuration(1000);
//        mImageView.startAnimation(translateAnimation);
        TransitionDialog transitionDialog =
                new TransitionDialog(this, R.style.Job_Theme_Dialog_Generic);
        Animation animationIn = AnimationUtils.loadAnimation(this, R.anim.slide_in_bottom);
        Animation animationOut = AnimationUtils.loadAnimation(this, R.anim.slide_out_bottom);
        transitionDialog.setBackgroundTransition(animationIn,animationOut);
        transitionDialog.setContentView(R.layout.dialog_test);
        // 设置在展示之前
        transitionDialog.setClickOutClose(false);
        transitionDialog.show();
    }
}
