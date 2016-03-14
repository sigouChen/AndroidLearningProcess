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
    private Observable<String> comObservable;
    private Observable observable;
    private Subscriber<Integer> subscriber1;

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
        comObservable= Observable.create(new Observable.OnSubscribe<String>() {
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

        subscriber1 = new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                    PluLogUtil.log("---timer subscriber onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                PluLogUtil.log("--timer subscribe onError is "+e.getMessage());
            }

            @Override
            public void onNext(Integer integer) {
                PluLogUtil.log("---timer subscribe  onNext is "+integer);
            }
        };

        name ="lucy";
      //  defObserveable.subscribe(subscriber);
       // justObserveable.subscribe(subscriber);
        comObservable.subscribe(subscriber);
        //打印结果：
        //---_PLU LOG ---onNext name is lucy
        // ---_PLU LOG ---onNext name is lily
        //2者的区别是Observable.def里面定义出来的Observable的参数能保持一直是传进去变量的最新值

        //------------------------------------------------------------------------------
        observable = Observable.timer(2, 2, TimeUnit.SECONDS);
        observable.subscribe(subscriber1);
        

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (subscriber.isUnsubscribed()){
            subscriber.unsubscribe();
        }
        if (subscriber1.isUnsubscribed()){
            subscriber1.unsubscribe();
        }

    }
}
