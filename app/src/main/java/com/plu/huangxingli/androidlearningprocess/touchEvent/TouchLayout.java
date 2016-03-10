package com.plu.huangxingli.androidlearningprocess.touchEvent;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by lily on 16-3-5.
 */
public class TouchLayout extends LinearLayout{


    public TouchLayout(Context context) {
        super(context);
    }

    public TouchLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TouchLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }



   /* @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int motionEvent=ev.getAction();
        if (motionEvent==MotionEvent.ACTION_DOWN){
            return false;
        }else{
            return true;
        }


    }*/
}
