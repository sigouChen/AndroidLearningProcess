package com.plu.huangxingli.androidlearningprocess.view;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.plu.huangxingli.androidlearningprocess.R;
import com.plu.huangxingli.androidlearningprocess.Utils.PluLogUtil;


/**
 * Created by lily on 16-3-28.付费弹幕
 */
public class PayBandgeView extends LinearLayout {


    private TextView mDanmuName;
    private TextView mDanmuContent;
    private boolean isRunning;

    private int windowWidth;
    private View danmu;

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
        danmu = inflater.inflate(R.layout.paybandgelayout, this, true);

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
        final ObjectAnimator xAnimator=ObjectAnimator.ofFloat(danmu,"x",0,windowWidth);
        xAnimator.setDuration(2000);
        xAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                //  PluLogUtil.log("-----value iis "+value);
                danmu.setTranslationX((Float) animation.getAnimatedValue());
            }
        });
        xAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                PluLogUtil.log("----onAnimationStart");
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                PluLogUtil.log("-----onAnimationEnd");
                isRunning = false;
                danmu.clearAnimation();
                xAnimator.removeAllListeners();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        xAnimator.start();

    }

    public boolean isRunning(){
        return isRunning;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMeasure=MeasureSpec.makeMeasureSpec(windowWidth,MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasure, heightMeasureSpec);
    }
}
