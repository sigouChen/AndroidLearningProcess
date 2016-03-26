package com.plu.huangxingli.androidlearningprocess.AnimateAbout;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageButton;

/**
 * Created by lily on 16-3-26.
 */
public class MyAnimations {

    private static int xOffset = 15;
    private static int yOffset = -13;

    public static void initOffset(Context context) {
        //获取屏幕的密度 context.getResources().getDisplayMetrics().density 设置移动的距离
        xOffset = (int) (10 * context.getResources().getDisplayMetrics().density);
        yOffset = -(int) (8 * context.getResources().getDisplayMetrics().density);
    }

    public static Animation getRotateAnimation(float fromDegrees,
                                               float toDegrees, int durationMillis) {
        //旋转，前两个参数设置旋转角度，后四个设置旋转中心
        RotateAnimation rotate = new RotateAnimation(fromDegrees, toDegrees,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        //持续时间
        rotate.setDuration(durationMillis);
        //动画结束后，停留在最后一秒
        rotate.setFillAfter(true);
        return rotate;
    }

    public static void startAnimationsIn(ViewGroup viewgroup, int durationMillis) {
        for (int i = 0; i < viewgroup.getChildCount(); i++) {
            ImageButton inoutimagebutton = (ImageButton) viewgroup
                    .getChildAt(i);
            //显示图片
            inoutimagebutton.setVisibility(View.VISIBLE);

            ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams) inoutimagebutton
                    .getLayoutParams();
            //位移距离
            Animation animation = new TranslateAnimation(mlp.rightMargin
                    - xOffset, 0F, yOffset + mlp.bottomMargin, 0F);
            //动画结束后，停留在最后一帧
            animation.setFillAfter(true);
            //动画持续时间
            animation.setDuration(durationMillis);
            //启动时间
            animation.setStartOffset((i * 100)
                    / (-1 + viewgroup.getChildCount()));
            animation.setInterpolator(new OvershootInterpolator(2F));
            //加入动画
            inoutimagebutton.startAnimation(animation);

        }
    }

    public static void startAnimationsOut(ViewGroup viewgroup,
                                          int durationMillis) {
        for (int i = 0; i < viewgroup.getChildCount(); i++) {
            final ImageButton inoutimagebutton = (ImageButton) viewgroup
                    .getChildAt(i);
            ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams) inoutimagebutton
                    .getLayoutParams();
            Animation animation = new TranslateAnimation(0F, mlp.rightMargin
                    - xOffset, 0F, yOffset + mlp.bottomMargin);

            animation.setFillAfter(true);
            animation.setDuration(durationMillis);
            animation.setStartOffset(((viewgroup.getChildCount() - i) * 100)
                    / (-1 + viewgroup.getChildCount()));
            animation.setInterpolator(new AnticipateInterpolator(2F));
            //设置动画监听
            animation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation arg0) {
                }

                @Override
                public void onAnimationRepeat(Animation arg0) {
                }
                //动画结束后，隐藏imageButton
                @Override
                public void onAnimationEnd(Animation arg0) {
                    inoutimagebutton.setVisibility(View.GONE);
                }
            });
            inoutimagebutton.startAnimation(animation);
        }

    }

}

