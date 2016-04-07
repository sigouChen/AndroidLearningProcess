package com.plu.huangxingli.androidlearningprocess.activity;

import android.os.Bundle;
import android.app.Activity;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.plu.huangxingli.androidlearningprocess.R;
import com.plu.huangxingli.androidlearningprocess.Utils.PluLogUtil;
import com.plu.huangxingli.androidlearningprocess.view.PayBandgeView;
import com.plu.huangxingli.androidlearningprocess.view.PayBandgeView1;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class DanmuKuActivity extends Activity {

    private Timer timer;

    int i=0;
    private boolean hasFree;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danmu_ku);
        final LinearLayout linearLayout= (LinearLayout) findViewById(R.id.linear_danmu_container);


        final ArrayList<PayBandgeView> bandgeList=new ArrayList<>();

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        i++;
                        if (bandgeList.size() != 0) {
                            PluLogUtil.log("----bandgeList size is "+bandgeList.size());
                           // PayBandgeView firstBandgeView = bandgeList.get(0);
                            for (int j=0;j<bandgeList.size();j++){
                                PayBandgeView payBandgeView=bandgeList.get(j);
                                if (payBandgeView!=null&&!payBandgeView.isRunning()){
                                    PluLogUtil.log("----di "+j+"  ge paybandgeview is free");
                                    payBandgeView.setDanmuContent(" content 4444444447777777777777777777777777777777" + 1);
                                    payBandgeView.setSenderName("lily333333333777777777777777777777777777777777777 " + i);
                                    payBandgeView.send();
                                    hasFree=true;
                                    break;
                                }

                            }
                            if (!hasFree){
                                hasFree=false;
                                PayBandgeView payBandgeView = new PayBandgeView(DanmuKuActivity.this);
                                payBandgeView.setDanmuContent("i am num6666666666666666666666666666666666666666666666666666666 " + i);
                                payBandgeView.setSenderName("lily num 555555555555555555555555555555555555555555555555555555555555555555555555555555555 " + i);
                                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                layoutParams.height = 200;
                                layoutParams.width = LinearLayout.LayoutParams.MATCH_PARENT;
                                layoutParams.rightMargin = 500;
                                payBandgeView.send();
                                linearLayout.addView(payBandgeView, layoutParams);
                                bandgeList.add(payBandgeView);
                            }
                            /*if (firstBandgeView != null && !firstBandgeView.isRunning()) {
                                PluLogUtil.log("----firstBandge not null and not runnig  ");
                                firstBandgeView.setDanmuContent(" content " + i);
                                firstBandgeView.setSenderName(" lily " + i);
                                firstBandgeView.send();
                            }else {

                                PluLogUtil.log("-----firstBange is  " + firstBandgeView);
                                PayBandgeView payBandgeView = new PayBandgeView(DanmuKuActivity.this);
                                payBandgeView.setDanmuContent("i am num " + i);
                                payBandgeView.setSenderName("lily num " + i);
                                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                layoutParams.height = 200;
                                layoutParams.weight = LinearLayout.LayoutParams.MATCH_PARENT;
                                layoutParams.leftMargin = 100;
                                payBandgeView.send();
                                linearLayout.addView(payBandgeView, layoutParams);
                                bandgeList.add(payBandgeView);
                            }*/
                        }else {

                         //   PluLogUtil.log("-----firstBange is  " + firstBandgeView);
                            PayBandgeView payBandgeView = new PayBandgeView(DanmuKuActivity.this);
                            payBandgeView.setDanmuContent("i am num0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000 " + i);
                            payBandgeView.setSenderName("lily num00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000 " + i);
                            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                            layoutParams.height = 200;
                            layoutParams.width = LinearLayout.LayoutParams.MATCH_PARENT;
                            layoutParams.rightMargin = 500;
                            payBandgeView.send();
                            linearLayout.addView(payBandgeView, layoutParams);
                            bandgeList.add(payBandgeView);
                        }
                    }
                });

            }
        }, 20,1000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer!=null)
            timer.cancel();

    }
}
