package com.plu.huangxingli.androidlearningprocess.RxJava;

import android.database.Observable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.plu.huangxingli.androidlearningprocess.BaseActivity;
import com.plu.huangxingli.androidlearningprocess.R;
import com.plu.huangxingli.androidlearningprocess.Utils.PluLogUtil;

import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observer;
import rx.Subscriber;
import rx.subscriptions.CompositeSubscription;

public class ChangeSignActivity extends BaseActivity {

    private CompositeSubscription mCompositeSubscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_sign);
        mCompositeSubscription=new CompositeSubscription();
        Button btnBuffer=findView(R.id.btnBuffer);
        btnBuffer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //buffer(2)打印
                /**
                 * 03-17 07:30:41.040 23029-23029/com.plu.huangxingli.androidlearningprocess V/PLU: ---_PLU LOG ---buffer onNext is [3, a]
                 03-17 07:30:41.040 23029-23029/com.plu.huangxingli.androidlearningprocess V/PLU: ---_PLU LOG ---buffer onNext is [5, 6]
                 03-17 07:30:41.040 23029-23029/com.plu.huangxingli.androidlearningprocess V/PLU: ---_PLU LOG ---buffer onNext is [7]
                 03-17 07:30:41.040 23029-23029/com.plu.huangxingli.androidlearningprocess V/PLU: ---_PLU LOG ----buffer onComplete

                 */
                /**
                 * buffer(2,1)打印
                 * 03-17 07:32:21.340 24953-24953/com.plu.huangxingli.androidlearningprocess V/PLU: ---_PLU LOG ---buffer onNext is [3, a]
                 03-17 07:32:21.340 24953-24953/com.plu.huangxingli.androidlearningprocess V/PLU: ---_PLU LOG ---buffer onNext is [a, 5]
                 03-17 07:32:21.340 24953-24953/com.plu.huangxingli.androidlearningprocess V/PLU: ---_PLU LOG ---buffer onNext is [5, 6]
                 03-17 07:32:21.340 24953-24953/com.plu.huangxingli.androidlearningprocess V/PLU: ---_PLU LOG ---buffer onNext is [6, 7]
                 03-17 07:32:21.340 24953-24953/com.plu.huangxingli.androidlearningprocess V/PLU: ---_PLU LOG ---buffer onNext is [7]
                 03-17 07:32:21.340 24953-24953/com.plu.huangxingli.androidlearningprocess V/PLU: ---_PLU LOG ----buffer onComplete

                 *
                 */
                /**
                 * buffer(3,1)打印
                 * 03-17 07:37:38.630 31414-31414/com.plu.huangxingli.androidlearningprocess V/PLU: ---_PLU LOG ---buffer onNext is [3, a, 5]
                 03-17 07:37:38.630 31414-31414/com.plu.huangxingli.androidlearningprocess V/PLU: ---_PLU LOG ---buffer onNext is [a, 5, 6]
                 03-17 07:37:38.630 31414-31414/com.plu.huangxingli.androidlearningprocess V/PLU: ---_PLU LOG ---buffer onNext is [5, 6, 7]
                 03-17 07:37:38.630 31414-31414/com.plu.huangxingli.androidlearningprocess V/PLU: ---_PLU LOG ---buffer onNext is [6, 7]
                 03-17 07:37:38.630 31414-31414/com.plu.huangxingli.androidlearningprocess V/PLU: ---_PLU LOG ---buffer onNext is [7]
                 03-17 07:37:38.630 31414-31414/com.plu.huangxingli.androidlearningprocess V/PLU: ---_PLU LOG ----buffer onComplete
                 bufffer(count,skip):buffer(count, skip)从原始Observable的第一项数据开始创建新的缓存，此后每当收到skip项数据，用count项数据填充缓存：开头的一项和后续的count-1项，
                 它以列表(List)的形式发射缓存，取决于count和skip的值，
                 这些缓存可能会有重叠部分（比如skip < count时），也可能会有间隙（比如skip > count时）

                 */
                mCompositeSubscription.add(rx.Observable.just("3","a","5","6","7").buffer(3, TimeUnit.SECONDS).subscribe(new Subscriber<List<String>>() {
                    @Override
                    public void onCompleted() {
                        PluLogUtil.log("----buffer onComplete ");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<String> integers) {
                        PluLogUtil.log("---buffer onNext is "+integers);
                    }
                }));
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxUtil.unSubscribe(mCompositeSubscription);
    }
}
