package com.plu.huangxingli.androidlearningprocess.view;

import android.content.Context;
import android.text.Html;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.plu.huangxingli.androidlearningprocess.R;
import com.plu.huangxingli.androidlearningprocess.app.App;


/**
 * Created by lily on 16-3-28.付费弹幕
 */
public class PayBandgeView extends LinearLayout {


    private TextView mDanmuName;
    private TextView mDanmuContent;
    private boolean isRunning;

    private int windowWidth;

    public PayBandgeView(Context context) {
        super(context);
        init(context);
    }

    public PayBandgeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public PayBandgeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context){
        LayoutInflater inflater=LayoutInflater.from(context);
        View danmu=inflater.inflate(R.layout.paybandgelayout, this, true);
        mDanmuName = (TextView) danmu.findViewById(R.id.tvdanmu_name);
        mDanmuContent = (TextView) danmu.findViewById(R.id.tvdanmu_content);
        windowWidth=getResources().getDisplayMetrics().widthPixels;
    }


    public void setSenderName(String content){
        mDanmuName.setText(content);
    }

    public void setDanmuContent(String content){
        mDanmuContent.setText(content);
    }


    public void send(){
        isRunning=true;
        setVisibility(VISIBLE);
        TranslateAnimation translateAnimation=new TranslateAnimation(0, windowWidth,0,0);
        translateAnimation.setDuration(2000);
        startAnimation(translateAnimation);
        translateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                isRunning=false;

                clearAnimation();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    public boolean isRunning(){
        return isRunning;
    }


}
