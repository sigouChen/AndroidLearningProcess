package com.plu.huangxingli.androidlearningprocess.realMvp.view;

import android.os.Bundle;

import com.plu.huangxingli.androidlearningprocess.BaseActivity;
import com.plu.huangxingli.androidlearningprocess.realMvp.presenter.BasePresenter;

/**
 * Created by lenovo on 2016/3/15.
 */
public abstract class BaseMVPActiviy<V,T extends BasePresenter<V>> extends BaseActivity {


    T mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter=createPresenter();
        mPresenter.attachView((V)this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachFromView();
    }

    public abstract T createPresenter();


}
