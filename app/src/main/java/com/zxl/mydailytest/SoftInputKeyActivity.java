package com.zxl.mydailytest;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.method.ReplacementTransformationMethod;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

/**
 * @author zxl on 2018/07/26.
 *         discription: 软件盘活动
 */
public class SoftInputKeyActivity extends AppCompatActivity {
    private View mView;
    private EditText mEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soft_input_key);
//        initView();
//        setTreeObserver();
//        mEditText = findViewById(R.id.et);
//        mEditText.setTransformationMethod(new InputLowerToUpper());

    }


    private void initView() {
        // mView = findViewById(R.id.view);
    }

    private class InputLowerToUpper extends ReplacementTransformationMethod {
        @Override
        protected char[] getOriginal() {
            char[] lower = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z' };
            return lower;
        }

        @Override
        protected char[] getReplacement() {
            char[] upper = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z' };
            return upper;
        }

    }

    private void setTreeObserver() {
        // mView只是布局中的一块小的区域
        ViewTreeObserver viewTreeObserver = mView.getViewTreeObserver();
        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect rect = new Rect();
                mView.getWindowVisibleDisplayFrame(rect);
                Log.e("onGlobalLayout()","矩形左 = "+rect.left+"  矩形上 = "+rect.top +
                " 矩形右 = "+rect.right +"  矩形下 = "+rect.bottom);

            }
        });
    }

}
