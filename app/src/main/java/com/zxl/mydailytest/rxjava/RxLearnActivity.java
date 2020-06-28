package com.zxl.mydailytest.rxjava;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.zxl.mydailytest.R;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class RxLearnActivity extends AppCompatActivity {
    private static final String TAG = "RxLearnActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_learn);
        rxTwo();
    }


    private void rxOne(){
        Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(ObservableEmitter<Object> emitter) throws Exception {
                // 发射器...
            }
        }).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {

            }
        });
    }
    
    private void rxTwo(){
        Observable.create(new ObservableOnSubscribe<DataBean>() {
            @Override
            public void subscribe(ObservableEmitter<DataBean> emitter) throws Exception {
                // 进行网路请求获取数据
                Log.e(TAG, "subscribe: "+Thread.currentThread().getName() );
                Thread.sleep(2000);
                DataBean dataBean = new DataBean("小王","123");
                emitter.onNext(dataBean);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .map(new Function<DataBean, String>() {
                    @Override
                    public String apply(DataBean dataBean) throws Exception {
                        Log.e(TAG, "map: "+Thread.currentThread().getName() );
                        if (dataBean != null){
                            return "姓名 = "+dataBean.getName()+", 年龄 = "+dataBean.getAge();
                        }
                        return null;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        Log.e(TAG, "onNext: "+Thread.currentThread().getName() );
                        Toast.makeText(RxLearnActivity.this, s, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
