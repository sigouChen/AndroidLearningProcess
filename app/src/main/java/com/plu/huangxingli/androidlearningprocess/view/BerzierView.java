package com.plu.huangxingli.androidlearningprocess.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by lenovo on 2016/1/18.
 */
public class BerzierView extends View {
    int startX=100;
    int startY=100;
    static final int RADIS=20;
    public BerzierView(Context context) {
        super(context);
    }

    public BerzierView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BerzierView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int archorX= ((int) event.getRawX()+startX)/2;
        int archorY= ((int) event.getRawY()+startY)/2;
        int xDis = archorX - startX;
        int yDis=archorY-startY;
        int offsetY= (int) (RADIS*Math.sin(Math.atan((yDis / xDis))));
        int offsetX= (int) (RADIS*Math.cos(Math.atan(yDis / xDis)));
        int x1=startX-offsetX;
        int y1=startY+offsetY;
        int x2= (int) (event.getRawX()-offsetX);
        int y2= (int) (event.getRawY()+offsetY);
        int x3= (int) (event.getRawX()+offsetX);
        int y3= (int) (event.getRawY()-offsetY);
        int x4=startX+offsetX;
        int y4=startY-offsetY;
        return super.onTouchEvent(event);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
