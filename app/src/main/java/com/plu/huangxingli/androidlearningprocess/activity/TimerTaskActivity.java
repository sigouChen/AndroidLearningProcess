package com.plu.huangxingli.androidlearningprocess.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;

import com.plu.huangxingli.androidlearningprocess.BaseActivity;
import com.plu.huangxingli.androidlearningprocess.Utils.PluLogUtil;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by lily on 16-1-21.
 */
public class TimerTaskActivity extends BaseActivity{

    private Timer timer;
    //timer重复sheduleTask时,Task是会叠加的

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyHandler myHandler=new MyHandler();
        Message message=myHandler.obtainMessage();
        myHandler.sendEmptyMessageDelayed(0,5000);
        timer = new Timer();
        scheduleTimerTask();
    }


    private void scheduleTimerTask(){
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                PluLogUtil.log(TimerTaskActivity.class,"----timer tick");
            }
        },0,3000);
    }


    class MyHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
           // scheduleTimerTask();
        }
    }
}
