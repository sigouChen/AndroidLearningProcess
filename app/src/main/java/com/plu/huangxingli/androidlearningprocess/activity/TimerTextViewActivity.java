package com.plu.huangxingli.androidlearningprocess.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Toast;

import com.plu.huangxingli.androidlearningprocess.BaseActivity;
import com.plu.huangxingli.androidlearningprocess.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by lily on 16-1-14.
 */
public class TimerTextViewActivity extends BaseActivity{

    Toast toast;
    int i=0;
    private Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timer_text_layout);
        final Chronometer chronometer= (Chronometer) findView(R.id.chronometer);
        Button button_start= (Button) findViewById(R.id.button_start);
        Button button_stop= (Button) findViewById(R.id.button_stop);
       // toast=new Toast(TimerTextViewActivity.this);

        button_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //SystemClock.elapsedRealtime 从开机到现在的时间　单位毫秒
                //加上之后,计时器控件又从0开始计时了
                chronometer.setBase(SystemClock.elapsedRealtime());
                chronometer.start();
            }
        });

        button_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chronometer.stop();
            }
        });

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                i++;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        toast=Toast.makeText(TimerTextViewActivity.this,"toast " + i,Toast.LENGTH_SHORT);
                        toast.show();

                    }
                });

            }
        }, 0, 200);
    }

    @Override
    protected void onPause() {
        super.onPause();
        timer.cancel();
        toast.cancel();
    }
}
