package com.plu.huangxingli.androidlearningprocess.activity;

import android.app.ActionBar;
import android.os.Bundle;
import android.app.Activity;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.plu.huangxingli.androidlearningprocess.R;
import com.plu.huangxingli.androidlearningprocess.Utils.PluLogUtil;
import com.plu.huangxingli.androidlearningprocess.view.PayBandgeView;

import java.util.Timer;
import java.util.TimerTask;

public class DanmuKuActivity extends Activity {

    private Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danmu_ku);
        final LinearLayout linearLayout= (LinearLayout) findViewById(R.id.linear_danmu_container);


        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        PluLogUtil.log("-----hahah add danmu lalala ");
                        PayBandgeView payBandgeView = new PayBandgeView(DanmuKuActivity.this);
                        payBandgeView.setDanmuContent("i am danmucontetn");
                        payBandgeView.setSenderName("lily");
                        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        layoutParams.height=200;
                        layoutParams.weight= LinearLayout.LayoutParams.MATCH_PARENT;
                        layoutParams.leftMargin = 100;
                        payBandgeView.send();
                        linearLayout.addView(payBandgeView, layoutParams);
                    }
                });

            }
        }, 20,2500);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer!=null)
            timer.cancel();

    }
}
