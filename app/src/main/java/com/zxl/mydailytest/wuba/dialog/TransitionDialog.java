package com.zxl.mydailytest.wuba.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.ActionProvider;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.zxl.mydailytest.R;

import static android.view.KeyEvent.KEYCODE_SEARCH;

/**
 * @author crazyZhangxl on 2018/10/18.
 * Describe:
 */
public class TransitionDialog
        extends Dialog implements Animation.AnimationListener {
    private Animation mAnimationIn = AnimationUtils.loadAnimation(getContext(), R.anim.fade_in);
    private Animation mAnimationOut;
    private TransitionDialogListener mTransitionDialogListener;
    private boolean isClickOutClose  = true;
    public TransitionDialog(Context paramContext, int paramInt)
    {
        super(paramContext, paramInt);
        this.mAnimationIn.setAnimationListener(this);
        this.mAnimationOut = AnimationUtils.loadAnimation(getContext(), R.anim.fade_out);
        this.mAnimationOut.setAnimationListener(this);
        setOnKeyListener(new DialogInterface.OnKeyListener()
        {
            @Override
            public boolean onKey(DialogInterface paramAnonymousDialogInterface,
                                 int paramAnonymousInt, KeyEvent paramAnonymousKeyEvent)
            {
                return paramAnonymousKeyEvent.getKeyCode() == KEYCODE_SEARCH;
            }
        });
    }

    public boolean animationIsEnd()
    {
        Animation localAnimation = findViewById(R.id.TransitionDialogBackground).getAnimation();
        return (localAnimation == null) || (localAnimation.hasEnded());
    }

    public void dismissOut()
    {
        if (!animationIsEnd()) {
            return;
        }
        if (this.mAnimationOut == null)
        {
            dismiss();
            return;
        }
        this.mAnimationOut.reset();
        // 开启关闭动画
        findViewById(R.id.TransitionDialogBackground).startAnimation(this.mAnimationOut);
    }

    @Override
    public void onAnimationEnd(Animation paramAnimation)
    {
        // 如果当前动画的话 那么dismiss Dialog
        if (paramAnimation == this.mAnimationOut) {
            dismiss();
        }
        if ((paramAnimation != this.mAnimationIn) || (this.mTransitionDialogListener == null)) {
            return;
        }
        this.mTransitionDialogListener.showAfterAnimation();
    }

    @Override
    public void onAnimationRepeat(Animation paramAnimation) {}

    @Override
    public void onAnimationStart(Animation paramAnimation) {}

    @Override
    public void onBackPressed()
    {
        if ((this.mTransitionDialogListener != null) && (this.mTransitionDialogListener.onTransitionDialogBack())) {
            return;
        }
        dismissOut();
    }

    @Override
    protected void onCreate(Bundle paramBundle)
    {
        super.onCreate(paramBundle);
        getWindow().setSoftInputMode(32);
    }

    public void setBackgroundTransition(Animation animationIn, Animation animationOut)
    {
        this.mAnimationIn = animationIn;
        if (this.mAnimationIn != null) {
            this.mAnimationIn.setAnimationListener(this);
        }
        this.mAnimationOut = animationOut;
        if (this.mAnimationOut != null) {
            this.mAnimationOut.setAnimationListener(this);
        }
    }

    public void setTransitionDialogListener(TransitionDialogListener paramTransitionDialogListener)
    {
        this.mTransitionDialogListener = paramTransitionDialogListener;
    }

    public void setClickOutClose(boolean isClickOutClose){
        this.isClickOutClose = isClickOutClose;
    }

    @Override
    public void show()
    {
        super.show();
        // 设置空白区域点击消失------ 手动
        if (isClickOutClose) {
            findViewById(R.id.TransitionDialogBackground).setOnClickListener(v -> dismissOut());
            findViewById(R.id.content_layout).setOnClickListener(null);
        }
        if (this.mAnimationIn == null) {
            return;
        }
        this.mAnimationIn.reset();
        // 正在展示的意思
        if (isShowing())
        {
            findViewById(R.id.TransitionDialogBackground).startAnimation(this.mAnimationIn);
            return;
        }
        findViewById(R.id.TransitionDialogBackground).setAnimation(this.mAnimationIn);
    }

    public interface TransitionDialogListener
    {
        boolean onTransitionDialogBack();

        void showAfterAnimation();
    }
}
