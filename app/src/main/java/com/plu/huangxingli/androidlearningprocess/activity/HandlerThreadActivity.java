package com.plu.huangxingli.androidlearningprocess.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import com.plu.huangxingli.androidlearningprocess.BaseActivity;
import com.plu.huangxingli.androidlearningprocess.Utils.PluLogUtil;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by lily on 16-1-18.
 */
public class HandlerThreadActivity extends BaseActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ExecutorService executorService= Executors.newCachedThreadPool();
        HandlerThread handlerThread=new HandlerThread("test");
        executorService.execute(new MyHandlerThread("TEST"));
     /*   Handler handler=new MyHandler(handlerThread.getLooper());
        Message message=handler.obtainMessage();
        message.sendToTarget();*/


    }

    class MyHandlerThread extends HandlerThread{

        public MyHandlerThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            super.run();
            PluLogUtil.log(HandlerThreadActivity.class,"0---myHandleThread run thread name is "+Thread.currentThread().getName());
        }
    }

    class MyHandler extends Handler{


        public MyHandler(Looper looper) {
            super(looper);

        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            PluLogUtil.log(HandlerThreadActivity.class,"---handleMsg");

        }
    }

    class MyThread extends Thread{
        @Override
        public void run() {
            super.run();
            PluLogUtil.log(HandlerThreadActivity.class,"----run -----");
        }
    }
}
