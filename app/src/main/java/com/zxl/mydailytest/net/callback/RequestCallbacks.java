package com.zxl.mydailytest.net.callback;

import android.os.Handler;

import androidx.annotation.NonNull;

import com.zxl.mydailytest.ui.LatteLoader;
import com.zxl.mydailytest.ui.LoaderStyle;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author zxl on 2018/8/14.
 *         discription:
 */

public class RequestCallbacks  implements Callback<String>{
    private IRequest mIRequest;
    private ISuccess mISuccess;
    private IError mIError;
    private IFailure mIFailure;
    private LoaderStyle mLoaderStyle;
    private static final Handler HANDLER = new Handler();

    public RequestCallbacks(IRequest iRequest,
                            ISuccess iSuccess,
                            IError iError,
                            IFailure iFailure,
                            LoaderStyle loaderStyle) {
        mIRequest = iRequest;
        mISuccess = iSuccess;
        mIError = iError;
        mIFailure = iFailure;
        mLoaderStyle = loaderStyle;
    }

    @Override
    public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
        if (response.isSuccessful()){
            if (call.isExecuted()){
                if (mISuccess != null){
                    mISuccess.onSuccess(response.body());
                }
            }
        }else {
            if (mIError != null){
                mIError.onError(response.code(),response.message());
            }
        }

        if (mIRequest != null){
            mIRequest.onRequestEnd();
        }
        stopLoading();
    }

    @Override
    public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {
        if (mIFailure != null){
            mIFailure.onFailure();
        }

        if (mIRequest != null){
            mIRequest.onRequestEnd();
        }

        stopLoading();
    }

    private void stopLoading(){
        if (mLoaderStyle != null){
            HANDLER.postDelayed(new Runnable() {
                @Override
                public void run() {
                    LatteLoader.stopLoading();
                }
            },2000);
        }

    }
}
