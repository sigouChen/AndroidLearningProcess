package com.plu.huangxingli.androidlearningprocess.view;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.plu.huangxingli.androidlearningprocess.R;
import com.plu.huangxingli.androidlearningprocess.Utils.PluLogUtil;
import com.plu.huangxingli.androidlearningprocess.Utils.UiTools;


/**
 * Created by lily on 16-3-28.付费弹幕
 */
public  class PayBandgeView extends LinearLayout {



   protected TextView mDanmuName,mTvDanmuGiftName;
    protected TextView mDanmuContent;
    private boolean isRunning;

    private int windowWidth;
    protected View danmu;

    private ImageView mImageViewDanmuGift;

    private OnPayDanmuListener mOnPayDanmuListener;

    public void setOnPayDanmuListener(OnPayDanmuListener mOnPayDanmuListener) {
        this.mOnPayDanmuListener = mOnPayDanmuListener;
    }

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

    protected void init(Context context){
        LayoutInflater inflater=LayoutInflater.from(context);
       // danmu = inflater.inflate(R.layout.paybandgelayout, this, true);
        danmu = inflater.inflate(R.layout.paybandgelayout, this, true);
        mImageViewDanmuGift= (ImageView) danmu.findViewById(R.id.image_danmu_gift);
        mDanmuName = (TextView) danmu.findViewById(R.id.tvdanmu_name);
        mDanmuContent = (TextView) danmu.findViewById(R.id.tvdanmu_content);
        mTvDanmuGiftName= (TextView) danmu.findViewById(R.id.tvdanmu_name);
        windowWidth=getResources().getDisplayMetrics().widthPixels;

    }


    public void hideGifPic(){
        mImageViewDanmuGift.setVisibility(GONE);
    }


    public void hideGiftName(){
        mImageViewDanmuGift.setVisibility(GONE);
    }

    public void setSenderName(String content){
        mDanmuName.setText(content);
    }

    public void setDanmuContent(String content){
        mDanmuContent.setText(content);
    }





    private void animaSecond(int viewWidth){
        final ObjectAnimator xAnimator=ObjectAnimator.ofFloat(danmu,"x",viewWidth,-viewWidth);
        xAnimator.setDuration(windowWidth/2);
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
                //PluLogUtil.log("-----onAnimationEnd");


                isRunning = false;
                setVisibility(INVISIBLE);
                danmu.clearAnimation();
                xAnimator.removeAllListeners();
                if (mOnPayDanmuListener!=null)
                    mOnPayDanmuListener.onSecondAnimEnd(PayBandgeView.this);


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

    public void send(){
        isRunning=true;
        setVisibility(VISIBLE);
        final int viewWidth= UiTools.getMeasureWidth(this);

        final ObjectAnimator xAnimator=ObjectAnimator.ofFloat(danmu,"x",windowWidth,windowWidth-viewWidth);
        xAnimator.setDuration(viewWidth/(windowWidth/2));
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

                xAnimator.removeAllListeners();
                if (mOnPayDanmuListener!=null){
                    mOnPayDanmuListener.onFirstAnimEnd();
                }
                animaSecond(windowWidth-viewWidth);

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

    public interface OnPayDanmuListener{
        void onFirstAnimEnd();
        void onSecondAnimEnd(PayBandgeView payBandgeView);
    }


}
