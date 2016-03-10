package com.plu.huangxingli.androidlearningprocess.RxJava;

import android.os.Bundle;

import com.plu.huangxingli.androidlearningprocess.BaseActivity;
import com.plu.huangxingli.androidlearningprocess.Utils.PluLogUtil;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func0;

/**
 * Created by lily on 16-3-10.
 */
public class RxCreateSignActivity extends BaseActivity {

    private String name;
    private Observable<String> defObserveable;
    private Observable<String> justObserveable;
    private Subscriber<String> subscriber;
    private Observable observable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        name = "lily";

        defObserveable = Observable.defer(new Func0<Observable<String>>() {
            @Override
            public Observable<String> call() {
                return Observable.just(name);
            }
        });
        justObserveable = Observable.just(name);
        name ="lucy";
        subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                PluLogUtil.log("---onNext name is " + s);
            }
        };

        defObserveable.subscribe(subscriber);
        justObserveable.subscribe(subscriber);
        //打印结果：
        //---_PLU LOG ---onNext name is lucy
        // ---_PLU LOG ---onNext name is lily
        //2者的区别是Observable.def里面定义出来的Observable的参数能保持一直是传进去变量的最新值

        //------------------------------------------------------------------------------
        observable = Observable.timer(2, 2, TimeUnit.SECONDS);
        observable.subscribe(new Subscriber<Long>() {
            @Override
            public void onCompleted() {
                PluLogUtil.log("---onComplete");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Long aLong) {
                PluLogUtil.log("---onNext " + aLong);

            }
        });







    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (subscriber.isUnsubscribed()){
            subscriber.unsubscribe();
        }

    }
}
