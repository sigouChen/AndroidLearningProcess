package com.plu.huangxingli.androidlearningprocess.RxJava.operators;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.plu.huangxingli.androidlearningprocess.BaseActivity;
import com.plu.huangxingli.androidlearningprocess.R;
import com.plu.huangxingli.androidlearningprocess.Utils.PluLogUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.observables.GroupedObservable;
import rx.subscriptions.CompositeSubscription;

public class ChangeSignActivity extends BaseActivity {

    @Bind(R.id.btnBuffer)
    Button btnBuffer;
    @Bind(R.id.btnflatMap)
    Button btnflatMap;

    @Bind(R.id.btnGroupBy)
    Button btnGroupBy;
    @Bind(R.id.btnMap)
    Button btnMap;
    @Bind(R.id.btnScan)
    Button btnScan;
    @Bind(R.id.btnWindow)
    Button btnWindow;
    @Bind(R.id.btnRepeat)
    Button btnRepeat;
    @Bind(R.id.btnStart)
    Button btnStart;
    @Bind(R.id.btnLeft)
    Button btnLeft;
    @Bind(android.R.id.list)
    ListView listview;
    private CompositeSubscription mCompositeSubscription;

    ArrayList<String> changeSignList=new ArrayList<>();
    private ArrayAdapter<String> adapter;
    private Observable<List<String>> bufferObservable;
    private Subscriber bufferSubscribe;
    private Observable<String> flatMapObservable;
    private Subscriber booleanSubscriber;
    private Observable<Boolean> mapObservable;
    private Observable<GroupedObservable<Boolean, Integer>> groupedObservable;
    private Subscriber groupBySubscriber;
    private Observable<Integer> scanObservable;
    private Subscriber scanSubscriber;
    private Observable<Observable<String>> windowObservable;
    private Subscriber windowSubscriber;
    private Subscriber<String> stringSubscriber;

    @OnClick(R.id.btnBuffer) void bufferOp(){

        changeSignList.clear();
        bufferObservable.subscribe(bufferSubscribe);

    }
    @OnClick(R.id.btnflatMap) void flatMapOp(){
        changeSignList.clear();
        flatMapObservable.subscribe(stringSubscriber);
    }
    @OnLongClick(R.id.btnBuffer) boolean bufferOp1(){
        changeSignList.clear();
        bufferObservable=Observable.just("3", "a", "5", "6", "7").buffer(3,1);
        bufferObservable.subscribe(bufferSubscribe);
        PluLogUtil.log("----btnBuffer onClick");
        return true;
    }
    @OnClick(R.id.btnMap) void mapOp(){
        changeSignList.clear();
        mapObservable.subscribe(booleanSubscriber);
    }
    @OnClick(R.id.btnGroupBy) void groupByOp(){
        changeSignList.clear();
        groupedObservable.subscribe(groupBySubscriber);
    }

    @OnClick(R.id.btnScan) void scanOp(){
        changeSignList.clear();
        scanObservable.subscribe(scanSubscriber);
    }
    @OnClick(R.id.btnWindow) void windowOp(){
        changeSignList.clear();
        windowObservable.subscribe(windowSubscriber);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_sign);
        ButterKnife.bind(this);
        adapter = new ArrayAdapter<String>(ChangeSignActivity.this,android.R.layout.simple_list_item_1,android.R.id.text1,changeSignList );
        listview.setAdapter(adapter);
        createStringSubscribe();
        bufferObservable=Observable.just("3", "a", "4", "5", "6", "7").buffer(3);
        mCompositeSubscription = new CompositeSubscription();
        createGroupObservable();
        createGroupBySubscriber();
        createFlatMapObservable();
        createMapObservable();
        createBooleanSubscriber();
        createScanObservable();
        createScanSubscriber();
        stringSubscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {
                PluLogUtil.log("----string subscriber onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                PluLogUtil.log("----string subscriber onError is "+e.getMessage());
            }

            @Override
            public void onNext(String s) {
                PluLogUtil.log("-- string subscriber onNext is "+s);

            }
        };
        //Observable<Observable<String>> windowObservable=
        windowObservable = Observable.just("a", "b", "c", "d", "e", "f").window(2);//Window和Buffer类似，但不是发射来自原始Observable的数据包，
        createWindowSubscriber();
        mCompositeSubscription.add(booleanSubscriber);
        mCompositeSubscription.add(bufferSubscribe);
        mCompositeSubscription.add(groupBySubscriber);
        mCompositeSubscription.add(scanSubscriber);
        mCompositeSubscription.add(windowSubscriber);
    }

    private void createWindowSubscriber() {
        windowSubscriber = new Subscriber<Observable<String>>() {//它发射的是Observables，这些Observables中的每一个都发射原始Observable数据的一个子集，最后发射一个onCompleted通知
            @Override
            public void onCompleted() {
                showLog("onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                showLog("parent onError : "+e.getMessage());
            }

            @Override
            public void onNext(Observable<String> stringObservable) {
                showLog("parent onNext stringObservable : "+stringObservable);
                stringObservable.subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        showLog("child onNext window : "+s);
                    }
                });
                PluLogUtil.log("--window Observable onNext is " + stringObservable);
            }
        };
    }

    private void createScanSubscriber() {
        scanSubscriber = new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                showLog("onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                showLog("onError : "+e.getMessage());
            }

            @Override
            public void onNext(Integer o) {
                showLog("onNext : "+o);
            }
        };
    }

    private void createScanObservable() {
        scanObservable = Observable.just(1, 2, 3, 4, 5, 6).scan(new Func2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer integer, Integer integer2) {
                return integer+integer2;
            }
        });
    }

    private void createGroupBySubscriber() {
        groupBySubscriber = new Subscriber<GroupedObservable<Boolean,Integer>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(final GroupedObservable<Boolean,Integer> o) {
                showLog("parent goupby onNext : "+o.cache()); //onNext会执行组数次，分为几组，就会调用几次
                o.subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        showLog(" cached groupBy key : "+o.getKey()+" value : "+integer);
                        PluLogUtil.log("----groupBySubscriber key is " + o.getKey() + " value is " + integer);
                    }
                });
            }
        };
    }

    private void createGroupObservable() {
        groupedObservable = Observable.just(1, 2, 3, 4, 5, 6).groupBy(new Func1<Integer, Boolean>() {
            @Override
            public Boolean call(Integer integer) {
                //分成两组,key分别为true和false
                return integer % 2 == 0;
            }
        });
    }

    private void createBooleanSubscriber() {
        booleanSubscriber = new Subscriber<Boolean>() {
            @Override
            public void onCompleted() {
                showLog("onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                showLog("onError : "+e.getMessage());
            }

            @Override
            public void onNext(Boolean o) {
                showLog("onNext : "+o);
            }
        };
    }

    private void createMapObservable() {
        mapObservable = Observable.just("4").map(new Func1<String, Boolean>() {
            @Override
            public Boolean call(String s) {
                return s.equals("4");
            }
        });
    }

    private void createFlatMapObservable() {
        flatMapObservable = Observable.just("lily","lucy").flatMap(new Func1<String, Observable<String>>() {
            @Override
            public Observable<String> call(String s) {
                return Observable.just(s+" is a good girl");
            }
        });
    }

    private void createStringSubscribe() {
        bufferSubscribe = new Subscriber<List<String>>() {
            @Override
            public void onCompleted() {
                showLog("onCompleted");

            }

            @Override
            public void onError(Throwable e) {
                showLog("onError : "+e.getMessage());
            }

            @Override
            public void onNext(List<String> o) {
                showLog("onNext : "+o);

            }
        };
    }

    private void showLog(String content){
        changeSignList.add(content);
        adapter.notifyDataSetChanged();

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mCompositeSubscription!=null){
            mCompositeSubscription.unsubscribe();
        }
    }
}
