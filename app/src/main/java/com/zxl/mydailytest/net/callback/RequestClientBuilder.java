package com.zxl.mydailytest.net.callback;

import android.content.Context;

import com.zxl.mydailytest.net.RestClient;
import com.zxl.mydailytest.net.RestCreator;
import com.zxl.mydailytest.ui.LatteLoader;
import com.zxl.mydailytest.ui.LoaderStyle;

import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * @author zxl on 2018/8/14.
 *         discription:
 */

public class RequestClientBuilder {
    private  String url;
    private  static final Map<String,Object> PARAMS = RestCreator.getParams();
    private  IRequest iRequest;
    private  ISuccess iSuccess;
    private  IError iError;
    private  IFailure iFailure;
    private  RequestBody body;
    private LoaderStyle mLoaderStyle;
    private Context mContext;

    public RequestClientBuilder(Context context) {
        this.mContext = context;
    }

    public final RequestClientBuilder url(String url){
        this.url = url;
        return this;
    }

    public final RequestClientBuilder params(WeakHashMap<String,Object> params){
         PARAMS.putAll(params);
         return this;
    }

    public final RequestClientBuilder params(String key,Object value){
        PARAMS.put(key,value);
        return this;
    }



    public final RequestClientBuilder request(IRequest iRequest){
        this.iRequest = iRequest;
        return this;
    }

    public final RequestClientBuilder success(ISuccess iSuccess){
        this.iSuccess = iSuccess;
        return this;
    }

    public final RequestClientBuilder error(IError iError){
        this.iError = iError;
        return this;
    }

    public final RequestClientBuilder failure(IFailure iFailure){
        this.iFailure = iFailure;
        return this;
    }

    public final RequestClientBuilder raw(String raw){
        this.body = RequestBody.create(MediaType.parse("application/json;charset=utf-8"),raw);
        return this;
    }

    public final RequestClientBuilder setLoaderStyle(LoaderStyle loaderStyle){
        this.mLoaderStyle = loaderStyle;
        return this;
    }

    public final RequestClientBuilder setLoaderStyle(){
        this.mLoaderStyle = LoaderStyle.SquareSpinIndicator;
        return this;
    }


    public final RestClient build(){
        return new RestClient(url,PARAMS,iRequest,iSuccess,iError,iFailure,body,mLoaderStyle,mContext);
    }
}
