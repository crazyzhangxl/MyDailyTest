package com.zxl.mydailytest.views;

import android.content.Context;

import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.LinearLayoutCompat;

import com.zxl.mydailytest.R;

/**
 * @author crazyZhangxl on 2018/10/16.
 * Describe:
 */
public class CitySearchView extends LinearLayout {
    private static final  int HIDE_SEARCH = 2;
    private  static final int SHOW_SEARCH = 1;
    private Context mContext;
    private View mDeleteButton;
    private EditText mEditText;
    private LayoutInflater mLayoutInflater;
    private Button mDoSearchBtn;
    private InputMethodManager mInputManager;


    public CitySearchView(Context context) {
        this(context,null);
    }

    public CitySearchView(Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }

    public CitySearchView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        this.mLayoutInflater = LayoutInflater.from(context);
        this.mInputManager =  (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
        initViews();
    }

    private void initViews() {
        View localView = mLayoutInflater.inflate(R.layout.city_search_view,null);
        localView.setLayoutParams(new LinearLayoutCompat.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        mEditText =  localView.findViewById(R.id.searcherInputEditText);
        mDoSearchBtn = localView.findViewById(R.id.searcherDoSearcherButton);
        mDeleteButton = localView.findViewById(R.id.search_del_btn);
        mDeleteButton.setVisibility(GONE);
        showSearchCancel();
        initListener();
        addView(localView);
    }

    private void initListener() {
        mDoSearchBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mDeleteButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }

    private void showSearchCancel() {
        mDoSearchBtn.setText("取消");
        mDoSearchBtn.setTag("cancel");
    }


    private void tongleSoftKeyboard(int paramInt){
        switch (paramInt){
            case 2:
                hideSoftKeyboard(mEditText);
                break;
            case 1:
                showSoftKeyboard(mEditText);
                break;
                default:
                    break;
        }

    }

    private void hideSoftKeyboard(EditText editText) {
        if (mInputManager.isActive()){
            mInputManager.hideSoftInputFromInputMethod(editText.getWindowToken(),0);
        }

    }

    // 键盘操作还是不会啊 怎么回事呢 ----
    private void showSoftKeyboard(EditText editText) {
        mInputManager.showSoftInput(editText,2);
        mInputManager.toggleSoftInput(0,2);
    }

    public void dismiss(){
        stopSearch();
        tongleSoftKeyboard(2);
        setVisibility(GONE);
    }

    // 停止收缩 即点击了取消时的状态------
    private void stopSearch() {
        clearEditText();
        hideDropDown();
        showSearchCancel();
        mDeleteButton.setVisibility(GONE);
    }

    private void hideDropDown() {
        // 隐藏搜索的列表 -----
    }

    private void clearEditText() {
        mEditText.setText("");
        showSoftKeyboard(mEditText);
    }
}
