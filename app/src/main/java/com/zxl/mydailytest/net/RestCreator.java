package com.zxl.mydailytest.net;

import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * @author zxl on 2018/8/14.
 *         discription: 创造方法
 */

public class RestCreator {

    public static RestService getRestService(){
        return RestServiceHolder.REST_SERVICE;
    }

    private static class ParamsHolder{
        private static final WeakHashMap<String,Object> PARAMS = new WeakHashMap<>();
    }

    public static WeakHashMap<String,Object> getParams(){
        return ParamsHolder.PARAMS;
    }



    /**
     * 单例Client
     */
    private static final class OkHttpHolder{
        private static final int TIME_OUT = 60;
        private static final OkHttpClient OK_HTTP_CLIENT = new OkHttpClient
                .Builder()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .build();
    }

    /**
     * 单例Retrofit
     */
    private static final class RetrofitHolder{
        /**
         * 这个先模拟 后面关于全局的再进行学习设置
         */
        private static final String BASE_UTL  = "https://blog.csdn.net";
        private static final Retrofit RETROFIT_CLIENT = new Retrofit.Builder()
                .baseUrl(BASE_UTL)
                .client(OkHttpHolder.OK_HTTP_CLIENT)
                // 转换为String类型
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
    }

    /**
     * 单例RestService
     */
    private static final class RestServiceHolder{
        private static final RestService REST_SERVICE =
                RetrofitHolder.RETROFIT_CLIENT
                .create(RestService.class);
    }


}
