package com.plu.huangxingli.androidlearningprocess.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by lenovo on 2016/3/7.
 */
public class SimpleCustomView extends View {

    Paint mPaint;
    public SimpleCustomView(Context context) {
        super(context);
        init(context);
    }

    public SimpleCustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SimpleCustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }
    public void init(Context context){
        mPaint=new Paint();
        mPaint.setColor(Color.RED);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //这种写法是正确的，自己定义的padding都有用，而下面注释掉的那种padding即时设置上了也没用
        int width=getMeasuredWidth()-getPaddingLeft()-getPaddingRight();
        int height=getMeasuredHeight()-getPaddingTop()-getPaddingBottom();
        int paddingLfet=getPaddingLeft();
        int paddingTop=getPaddingTop();
        int radius=Math.min(width, height)/2;
        canvas.drawCircle(paddingLfet+width/2,paddingTop+height/2,radius,mPaint);



        /*int width=getMeasuredWidth();
        int height=getMeasuredHeight();
        int radius=Math.min(width,height)/2;
        canvas.drawCircle(width/2,height/2,radius,mPaint);*/

    }
}
