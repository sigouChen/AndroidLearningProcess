package com.plu.huangxingli.androidlearningprocess.realMvp.presenter;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * Created by lenovo on 2016/3/15.
 */
public  class BasePresenter<T>{

    Reference<T> reference;
    public void attachView(T t) {
     reference=new WeakReference<T>(t);
    }

    public T getView(){
        return reference==null?null:reference.get();
    }

    public boolean isViewAttached(){
        return reference!=null&&reference.get()!=null;
    }




    public void detachFromView() {
        if (reference!=null){
            reference.clear();
            reference=null;
        }
    }
}
