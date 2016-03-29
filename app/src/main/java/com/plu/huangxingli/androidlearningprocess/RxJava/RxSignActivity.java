package com.plu.huangxingli.androidlearningprocess.RxJava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.plu.huangxingli.androidlearningprocess.R;
import com.plu.huangxingli.androidlearningprocess.Utils.PluLogUtil;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.subscriptions.CompositeSubscription;

public class RxSignActivity extends AppCompatActivity {


    private CompositeSubscription mCompositeSubscription; //用来管理订阅者的一个类，不用单个取消订阅


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_sign);
        mCompositeSubscription=new CompositeSubscription();
        mCompositeSubscription.add(Observable.timer(2, 2, TimeUnit.SECONDS, AndroidSchedulers.mainThread()).subscribe(new Action1<Long>() {
            @Override
            public void call(Long aLong) {
                PluLogUtil.log("--- subscribe action thread is " + Thread.currentThread().getName()+"value is "+aLong);
            }
        }));
        mCompositeSubscription.add(Observable.interval(1, 2, TimeUnit.SECONDS, AndroidSchedulers.mainThread()).subscribe(new Action1<Long>() {
            @Override
            public void call(Long aLong) {
                PluLogUtil.log("--interval call value is "+aLong);
            }
        }));
        mCompositeSubscription.add(Observable.range(3, 10, AndroidSchedulers.mainThread()).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                PluLogUtil.log("----range call value is "+integer);
            }
        }));
        mCompositeSubscription.add(Observable.range(3,5).repeat(2, AndroidSchedulers.mainThread()).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                PluLogUtil.log("---repeatSubs call value is " + integer + " thread is " + Thread.currentThread().getName());
            }
        }));
        /*mReaptWhenSubscription=Observable.range(3,3).repeatWhen(new Func1<Observable<? extends Void>, Observable<?>>() {
            @Override
            public Observable<?> call(Observable<? extends Void> observable) {
                return null;
            }
        })*/

       /* Observable<Integer> observable1 = Observable.just(1,2,3);
        Observable<Integer> observable2 = Observable.just(4, 5, 6, 7);
        Observable.zip(observable1, observable2, new Func2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer integer, Integer integer2) {
                return integer + integer2;
            }
        }).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                System.out.println("Sequence complete.");
            }

            @Override
            public void onError(Throwable e) {
                System.err.println("Error: " + e.getMessage());
            }

            @Override
            public void onNext(Integer value) {
                System.err.println("Next:" + value);
            }
        });*/
        mCompositeSubscription.add(Observable.just(1,2,3).repeatWhen(new Func1<Observable<? extends Void>, Observable<?>>() {
            @Override
            public Observable<?> call(Observable<? extends Void> observable) {
                //重复3次
                return observable.zipWith(Observable.range(1, 3), new Func2<Void, Integer, Integer>() {
                    @Override
                    public Integer call(Void aVoid, Integer integer) {
                        Log.v("REPWHEN","---ZIP WITH CALL INTEGER IS "+integer);
                        return integer;
                    }
                }).flatMap(new Func1<Integer, Observable<?>>() {
                    @Override
                    public Observable<?> call(Integer integer) {
                        Log.v("REPWHEN","----FLATMAP CALL VALUE IS "+integer);
                        System.out.println("delay repeat the " + integer + " count");
                        //1秒钟重复一次
                        return Observable.timer(5, TimeUnit.SECONDS);
                    }
                });
            }
        }).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                System.out.println("Sequence complete.");
            }

            @Override
            public void onError(Throwable e) {
              //  System.err.println("Error: " + e.getMessage());
            }

            @Override
            public void onNext(Integer value) {
                Log.v("REPWHEN","---SUBSCRIBER ONNEXT VALUE IS "+value);
                System.out.println("Next:" + value);
            }
        }));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_rx_sign, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so longpic
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxUtil.unSubscribe(mCompositeSubscription);
    }
}
