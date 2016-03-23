package com.plu.huangxingli.androidlearningprocess.RxJava.operators;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.plu.huangxingli.androidlearningprocess.BaseActivity;
import com.plu.huangxingli.androidlearningprocess.R;
import com.plu.huangxingli.androidlearningprocess.Utils.PluLogUtil;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func0;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by lily on 16-3-10.
 */
public class RxCreateSignActivity extends BaseActivity {

    @Bind(R.id.btnCreate)
    Button btnCreate;
    @Bind(R.id.btnJust)
    Button btnJust;
    @Bind(R.id.btnDefer)
    Button btnDefer;
    @Bind(R.id.btnFrom)
    Button btnFrom;
    @Bind(R.id.btnTimer)
    Button btnTimer;
    @Bind(R.id.btnInterval)
    Button btnInterval;
    @Bind(R.id.btnRange)
    Button btnRange;
    @Bind(R.id.btnRepeat)
    Button btnRepeat;
    @Bind(R.id.btnStart)
    Button btnStart;
    @Bind(R.id.btnLeft)
    Button btnLeft;

    @Bind(android.R.id.list)
    ListView showListView;
    private String name;
    private Observable<String> defObserveable;
    private Observable<String> justObserveable;
    private Subscriber<String> stringSubscriber;
    private Observable<String> createObservable;

    private ArrayAdapter<String> adapter;
    private Observable<String> fromObservable;
    private Observable<Long> itervalObservable;
    private Subscriber<Long> longSubscriber;
    private Observable<Long> timerObservable;
    private Subscriber<Integer> integerSubscriber;
    private Observable<Integer> rangeObservable;
    private Observable repeatObservable;
    private Observable<String> startWithObserver;

    @OnClick(R.id.btnCreate) void creatOp() {
        // TODO call server...
        PluLogUtil.log("---createOp");
        showList.clear();
        createObservable.subscribe(stringSubscriber);
    }

    @OnClick(R.id.btnJust) void justOp(){
        showList.clear();
        PluLogUtil.log("---just op onClick");
        justObserveable.subscribe(stringSubscriber);
    }

    @OnClick(R.id.btnDefer) void defOp(){
        showList.clear();
        defObserveable.subscribe(stringSubscriber);
    }
    @OnClick(R.id.btnFrom) void fromOp(){
        PluLogUtil.log("--from operation ");
        showList.clear();
        fromObservable.subscribe(stringSubscriber);
    }
    @OnClick(R.id.btnInterval) void intervalOp(){
        PluLogUtil.log("----interval btn click");
        showList.clear();
        itervalObservable.subscribe(longSubscriber);
    }
    @OnClick(R.id.btnTimer) void timerOp(){
        showList.clear();
        timerObservable.subscribe(longSubscriber);
    }
    @OnClick(R.id.btnRange) void rangeOp(){
        showList.clear();
        rangeObservable.subscribe(integerSubscriber);
    }
    @OnClick(R.id.btnRepeat) void repeatOp(){
        showList.clear();
        repeatObservable.subscribe(integerSubscriber);
    }
    @OnClick(R.id.btnStart) void startOp(){
        startWithObserver.subscribe(stringSubscriber);
    }

    ArrayList<String> showList=new ArrayList<>();
    private CompositeSubscription mCompsiteSubscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.createoper);
        mCompsiteSubscription=new CompositeSubscription();
        ButterKnife.bind(this);
        adapter = new ArrayAdapter<String>(RxCreateSignActivity.this,android.R.layout.simple_list_item_1,android.R.id.text1,showList);
        showListView.setAdapter(adapter);
        name = "lily";
        defObservable();
        justObservable();
        createObservable();
        createStringSubscriber();
        name = "lucy";
        String[] beaties={"lily","lucy","killy"};
        fromObservable = Observable.from(beaties);
        itervalObservable = Observable.interval(10, 500, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread()); //10ms后开始发射消息，每隔500mｓ发射一次,前2个数字的单位是由第三个参数的单位决定的
        createLongSubscriber();
        timerObservable = Observable.timer(1, TimeUnit.SECONDS, AndroidSchedulers.mainThread());//timer和interval默认都在第三方线程运行，可自己指定运行线程

        rangeObservable = Observable.range(0, 3);//range操作符的subscriber默认运行在主线程
        createIntegerSubscriber();
        repeatObservable = rangeObservable.repeat(3);//repeate操作符不是静态的,为其他创建出来的Observable添加重复发射特性
        startWithObserver = defObserveable.startWith(Observable.just("ha"));
        mCompsiteSubscription.add(longSubscriber);
        mCompsiteSubscription.add(stringSubscriber);
        mCompsiteSubscription.add(integerSubscriber);






        //打印结果：
        //---_PLU LOG ---onNext name is lucy
        // ---_PLU LOG ---onNext name is lily
        //2者的区别是Observable.def里面定义出来的Observable的参数能保持一直是传进去变量的最新值

        //------------------------------------------------------------------------------
       // observable = Observable.timer(2, 2, TimeUnit.SECONDS);
       // observable.subscribe(subscriber1);


    }

    private void createIntegerSubscriber() {
        integerSubscriber = new Subscriber<Integer>() {
            @Override
            public void onStart() {
                super.onStart();
                showLog("range操作符默认运行在主线程");
            }

            @Override
            public void onCompleted() {
                showLog("onCompleted ");

            }

            @Override
            public void onError(Throwable e) {
                showLog("onError : "+e.getMessage());

            }

            @Override
            public void onNext(Integer integer) {
                showLog("onNext : "+integer);
                PluLogUtil.log("----interger subscriber onNext thread is "+Thread.currentThread().getName());
            }
        };
    }

    private Subscriber createLongSubscriber() {
        longSubscriber = new Subscriber<Long>() {
            @Override
            public void onCompleted() {
                showLog("onCompleted ");
            }

            @Override
            public void onError(Throwable e) {
                showLog("onError :　"+e.getMessage());
            }

            @Override
            public void onNext(final Long aLong) {
                PluLogUtil.log("-long subscribe onNext-thread is " + Thread.currentThread().getName());
                showLog("onNext : " + aLong);
            }
        };
        return longSubscriber;
    }

    private void createStringSubscriber() {
        stringSubscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {
                PluLogUtil.log("---onComplete");
                showLog("onCompleted ");

            }

            @Override
            public void onError(Throwable e) {
                PluLogUtil.log("---onError");
                showLog("onError : " + e.getMessage());

            }

            @Override
            public void onNext(String s) {
                showLog("onNext :"+s);

                PluLogUtil.log("---onNext name is " + s);
            }
        };
    }

    private void defObservable() {
        defObserveable = Observable.defer(new Func0<Observable<String>>() {
            @Override
            public Observable<String> call() {
                return Observable.just(name);
            }
        });
    }

    private void justObservable() {
        justObserveable = Observable.just(name);
    }

    private void createObservable() {
        createObservable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext(name);
                subscriber.onCompleted();
            }
        });
    }


    private void showLog(String content){
        showList.add(content);
        adapter.notifyDataSetChanged();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mCompsiteSubscription!=null){
            mCompsiteSubscription.unsubscribe();
        }



    }
}
