package com.zxl.mydailytest.views;


import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.AppBarLayout;
import com.zxl.mydailytest.R;
import com.zxl.mydailytest.net.RestClient;
import com.zxl.mydailytest.net.callback.IError;
import com.zxl.mydailytest.net.callback.IFailure;
import com.zxl.mydailytest.net.callback.IRequest;
import com.zxl.mydailytest.net.callback.ISuccess;

/**
 * @author zxl on 2018/08/14.
 *         discription:
 */
public class RetrofitNetActivity extends AppCompatActivity {
    private AppBarLayout mAppBarLayout;
    private TextView mTvTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit_net);
        //testRestClient();
        mAppBarLayout = (AppBarLayout)findViewById(R.id.appBarLayout);
        mTvTitle =(TextView) findViewById(R.id.tv_title);
        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBar, int offset) {
                mTvTitle.setAlpha(Math.abs(offset * 1f / appBar.getTotalScrollRange()));
            }
        });
    }

    private void testRestClient(){
        RestClient.builder(this)
                .url("")
                .setLoaderStyle()
                .request(new IRequest() {
                    @Override
                    public void onRequestStart() {

                    }

                    @Override
                    public void onRequestEnd() {

                    }
                })
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {

                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {

                    }
                })
                .build()
        .get();
    }
}
