package com.plu.huangxingli.androidlearningprocess.ToastAbout;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.plu.huangxingli.androidlearningprocess.BaseActivity;
import com.plu.huangxingli.androidlearningprocess.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by lily on 16-1-20.
 */
public class ToastActivity extends BaseActivity{

    private MyHandler myHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myHandler = new MyHandler();
        Message message= myHandler.obtainMessage();
        message.sendToTarget();
        Timer timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast toast=new Toast(ToastActivity.this);
                        LayoutInflater inflater=LayoutInflater.from(ToastActivity.this);
                        View view=inflater.inflate(R.layout.self_toast,null);
                        toast.setView(view);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.setDuration(Toast.LENGTH_SHORT);

                        toast.show();

                    }
                });

            }
        },5000);



    }

    class MyHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Toast.makeText(ToastActivity.this,"i am toast",Toast.LENGTH_SHORT).show();
            Message message=Message.obtain();
            myHandler.sendEmptyMessageDelayed(0,1000);



        }
    }



}
