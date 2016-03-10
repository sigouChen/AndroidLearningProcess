package com.plu.huangxingli.androidlearningprocess.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.widget.TextView;

import com.plu.huangxingli.androidlearningprocess.Utils.PluLogUtil;

import java.security.MessageDigest;
import java.security.spec.PKCS8EncodedKeySpec;

/**
 * Created by lily on 16-2-26.
 */
public class CustomView extends TextView {
    int windowWidth,windowHeight;

    public CustomView(Context context) {
        super(context);
        init(context);
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context){
        DisplayMetrics displayMetrics=getResources().getDisplayMetrics();
        windowWidth=displayMetrics.widthPixels;
        windowHeight=displayMetrics.heightPixels;

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        PluLogUtil.log("widthMeasureSpec is "+widthMeasureSpec+" height MeasureSpec is "+heightMeasureSpec);
        int widthMeasureMode=MeasureSpec.getMode(widthMeasureSpec);
        int widthMeasureSize=MeasureSpec.getSize(widthMeasureSpec);

        int heightMeasureMode=MeasureSpec.getMode(heightMeasureSpec);
        int heightMeasureSize=MeasureSpec.getSize(heightMeasureSpec);
        //mode:这几个值打印的是自己的ｖｉｅｗ的layout_width 和layout_height 的mode
        //wrap_content:---->AT_MOST  match_parent,100px :--->EXACTLY
        //size: 父布局能提供的最大可使用宽高
        PluLogUtil.log("AT_MOST IS "+MeasureSpec.AT_MOST+"MeasureSpec Exactly is "+MeasureSpec.EXACTLY+""+"MeasureSpec UNSPECIFIED "+MeasureSpec.UNSPECIFIED);
        PluLogUtil.log("widthMeasureMode is "+widthMeasureMode+" widthMeasureMeasureSize is "+widthMeasureSize);
        PluLogUtil.log("heightMeasureMode is " + heightMeasureMode + " heightMeasureSize is " + heightMeasureSize);

        //setMeasuredDimension(widthMespec,heightMespec);
        super.onMeasure(widthMeasureSpec,heightMeasureSpec);
        PluLogUtil.log(" 11111111 getWidth is "+getMeasuredWidth()+" height is "+getMeasuredHeight());
    }
}
