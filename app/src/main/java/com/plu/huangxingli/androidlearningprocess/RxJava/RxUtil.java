package com.plu.huangxingli.androidlearningprocess.RxJava;

import rx.Subscription;

/**
 * Created by lily on 16-3-14.
 */
public class RxUtil {

    public static void unSubscribe(Subscription subscription){

               if (subscription!=null) {
                    subscription.unsubscribe();
                }
    }
}
