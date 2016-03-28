package com.plu.huangxingli.androidlearningprocess.activity;

import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.plu.huangxingli.androidlearningprocess.R;
import com.plu.huangxingli.androidlearningprocess.Utils.PluLogUtil;
import com.plu.huangxingli.androidlearningprocess.view.CommonGifView;

import java.util.Timer;
import java.util.TimerTask;

public class MAnimateTest extends AppCompatActivity {

    private CommonGifView giftView;
    private CommonGifView giftview2;
    private Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manimate_test);
        giftView = (CommonGifView) findViewById(R.id.giftView);
       // giftView.setGiftCount(10);
        giftview2 = (CommonGifView) findViewById(R.id.giftview2);
       /// giftview2.setGiftCount(6);
        final MyHandler myHandler=new MyHandler();
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                myHandler.sendEmptyMessage(0);
            }
        }, 30, 2000);

       // giftView.startAnimate(10);

        Button button= (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


               /* AnimationDrawable animationDrawable= (AnimationDrawable) getResources().getDrawable(R.drawable.animation_list);
                findViewById(R.id.animImageView).setBackground(animationDrawable);
                animationDrawable.start();*/
              /*  giftView.startAnimate(10);
                giftview2.startAnimate(6);*/

            }
        });
    }

    class MyHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (!giftView.isRunning()){
                PluLogUtil.log("----giftview is running");
                giftView.startAnimate(6);
            }else if (!giftview2.isRunning()){
                giftview2.startAnimate(3);
                PluLogUtil.log("---giftview2 is running");
            }else{
                sendEmptyMessageDelayed(0, 3000);
                PluLogUtil.log("----giftview is all busy ");
            }

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer!=null){
            timer.cancel();


        }
    }
}
