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

    private String name="lily";
    private Observable<String> defObserveable;
    private Observable<String> justObserveable;
    private Observable<String> comObservable;

    private Subscriber<String> subscriber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        defObserveable = Observable.defer(new Func0<Observable<String>>() {
           @Override
           public Observable<String> call() {
               return Observable.just(name);
           }
       });
        justObserveable = Observable.just(name);
        comObservable=Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext(name);
            }
        });
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
        name ="lucy";
        comObservable.subscribe(subscriber);
        defObserveable.subscribe(subscriber);
        justObserveable.subscribe(subscriber);
        //打印结果：
        //---_PLU LOG ---onNext name is lucy
        //---_PLU LOG ---onNext name is lucy
        // ---_PLU LOG ---onNext name is lily

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (subscriber.isUnsubscribed()){
            subscriber.unsubscribe();
        }

    }
}
