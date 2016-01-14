package com.plu.huangxingli.androidlearningprocess.view;

import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;

import com.plu.huangxingli.androidlearningprocess.R;

/**
 * Created by lily on 16-1-14.
 */
public class CustomAnimatorView extends View {


    Point currentPos;
    Paint paint;
    static final int RADIS=40;

    public CustomAnimatorView(Context context) {
        super(context);
        init();
    }

    public CustomAnimatorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomAnimatorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        paint=new Paint();
        paint.setColor(Color.RED);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (currentPos==null) {
            drawCircle(canvas);
            startAnimate();
        }else{
            drawCircle(canvas);
        }

    }

    private void startAnimate(){
        Point startPoint=new Point(RADIS, RADIS);
        Point endPoint=new Point(getWidth()-RADIS,getHeight()-RADIS);
        ValueAnimator valueAnimator=ValueAnimator.ofObject(new PointEvaluate(),startPoint,endPoint);
        valueAnimator.setDuration(4000);
        valueAnimator.start();
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                currentPos= (Point) animation.getAnimatedValue();
                invalidate();
            }
        });
    }

    public void drawCircle(Canvas canvas){
        if (currentPos==null){
            currentPos=new Point(RADIS,RADIS);
        }
        canvas.drawCircle(currentPos.x, currentPos.y, 40, paint);


    }

    class PointEvaluate implements TypeEvaluator<Point>{

        @Override
        public Point evaluate(float fraction, Point startValue, Point endValue) {
            int x= (int) (startValue.x+fraction*(endValue.x-startValue.x));
            int y= (int) (startValue.y+fraction*(endValue.y-startValue.y));

            return new Point(x,y);
        }
    }
}
