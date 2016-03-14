package com.plu.huangxingli.androidlearningprocess.RxJava;

import rx.Subscription;

/**
 * Created by lily on 16-3-14.
 */
public class RxUtil {

    public static void unSubscribe(Subscription... subscriptions){
        if (subscriptions!=null&&subscriptions.length!=0){
            for (Subscription subscription:subscriptions){
                subscription.unsubscribe();
            }
        }
    }
}
