package com.plu.huangxingli.androidlearningprocess.RxBindingUsing;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.view.ViewEvent;
import com.plu.huangxingli.androidlearningprocess.R;
import com.plu.huangxingli.androidlearningprocess.Utils.PluLogUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Subscriber;
import rx.functions.Func1;


public class RxBindingActivityUsing extends AppCompatActivity {

    @Bind(R.id.btnBind)
    Button mBtnBind;
    private int num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_binding_activity_using);
        ButterKnife.bind(this);
        num=0;
        RxView.clicks(mBtnBind).map(new Func1<Void,Integer>() {

            @Override
            public Integer call(Void aVoid) {
                return ++num;
            }

        }).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                PluLogUtil.log("---onComplete");
            }

            @Override
            public void onError(Throwable e) {
                PluLogUtil.log("---onError");
            }

            @Override
            public void onNext(Integer integer) {
                PluLogUtil.log("---onNext is "+integer);

            }
        });
        

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
