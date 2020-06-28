package com.zxl.mydailytest.net;

import android.content.Context;

import com.zxl.mydailytest.net.callback.IError;
import com.zxl.mydailytest.net.callback.IFailure;
import com.zxl.mydailytest.net.callback.IRequest;
import com.zxl.mydailytest.net.callback.ISuccess;
import com.zxl.mydailytest.net.callback.RequestCallbacks;
import com.zxl.mydailytest.net.callback.RequestClientBuilder;
import com.zxl.mydailytest.ui.LatteLoader;
import com.zxl.mydailytest.ui.LoaderCreator;
import com.zxl.mydailytest.ui.LoaderStyle;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * @author zxl on 2018/8/14.
 *         discription:
 */

public class RestClient {
    private final String URL;
    private static final Map<String,Object> PARAMS = RestCreator.getParams();
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IError ERROR;
    private final IFailure FAILURE;
    private final RequestBody BODY;
    private final LoaderStyle LOADSTYLE;
    private final  Context CONTEXT;
    /**
     * 第一步封装了请求参数
     *      后面才会继续去调用
     * @param url
     * @param params
     * @param irequest
     * @param isuccess
     * @param ierror
     * @param ifailure
     * @param body
     */
    public RestClient(String url, Map<String, Object> params, IRequest irequest,
                      ISuccess isuccess, IError ierror, IFailure ifailure,
                      RequestBody body,LoaderStyle loaderStyle,Context context) {
        this.URL = url;
        PARAMS.putAll(params);
        this.REQUEST = irequest;
        this.SUCCESS = isuccess;
        this.ERROR = ierror;
        this.FAILURE = ifailure;
        this.BODY = body;
        this.LOADSTYLE = loaderStyle;
        this.CONTEXT = context;
    }

    public static RequestClientBuilder builder(Context context){
        return new RequestClientBuilder(context);
    }

    private void request(HttpMethod httpMethod){
        final RestService service = RestCreator.getRestService();
        Call<String> call = null;
        if (REQUEST != null){
            REQUEST.onRequestStart();
        }

        if (LOADSTYLE != null){
            LatteLoader.showLoading(CONTEXT,LOADSTYLE);
        }

        switch (httpMethod){
            case GET:
                call = service.get(URL,PARAMS);
                break;
            case POST:
                break;
            case PUT:
                break;
            case DELETE:
                break;
            default:
                break;
        }

        if (call != null){
            call.enqueue(getRequestCallBack());

        }

    }

    private Callback<String> getRequestCallBack(){
        return new RequestCallbacks(
                REQUEST,
                SUCCESS,
                ERROR,
                FAILURE,
                LOADSTYLE);
    }

    public final void get(){
        request(HttpMethod.GET);
    }
}
