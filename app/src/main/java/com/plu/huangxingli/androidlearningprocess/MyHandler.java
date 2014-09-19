package com.plu.huangxingli.androidlearningprocess;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;


/**
 * Created by huangxingli on 2014/9/19.
 */
public class MyHandler extends Handler {

    public MyHandler(Looper looper){
        super(looper);
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
    }
}
