package com.plu.huangxingli.androidlearningprocess.touchEvent;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by lily on 16-3-5.
 */
public class CustomTextView extends TextView {

    ViewGroup viewParent;

    TouchLayout mTouchLayout;



    public CustomTextView(Context context) {
        super(context);
        init(context);
    }

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CustomTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void setTouchLayout(TouchLayout mTouchLayout) {
        this.mTouchLayout = mTouchLayout;
    }

    private void init(Context context){
        viewParent= (ViewGroup) getParent();

    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        int motionEvent=event.getAction();
        switch (motionEvent){
            case MotionEvent.ACTION_DOWN:
                mTouchLayout.requestDisallowInterceptTouchEvent(true);
                break;

            case MotionEvent.ACTION_UP:

                mTouchLayout.requestDisallowInterceptTouchEvent(false);

                break;
        }
        return super.dispatchTouchEvent(event);
    }
}
