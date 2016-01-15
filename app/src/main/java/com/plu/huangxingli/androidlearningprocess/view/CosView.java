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

/**
 * Created by lily on 16-1-15.
 */
public class CosView extends View{

    Paint paint;
    private Point currentPoint;
    private Point oldPoint;
    private Point endPoint;

    public CosView(Context context) {
        super(context);
        init();
    }

    public CosView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CosView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        endPoint=new Point((int) (100+Math.PI),99);
        paint=new Paint();
        paint.setColor(Color.RED);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (currentPoint==null){
            currentPoint=new Point(100,100);
            oldPoint=currentPoint;
            drawCircle(canvas);
            startCosAnimate();
        }else{
            drawCircle(canvas);
        }
    }

    private void drawCircle(Canvas canvas){
       // canvas.drawLine(oldPoint.x,oldPoint.y,currentPoint.x,currentPoint.y,paint);
        canvas.drawCircle(currentPoint.x,currentPoint.y,5,paint);
    }


    private void startCosAnimate(){
        ValueAnimator valueAnimator=ValueAnimator.ofObject(new CosEvaluate(),currentPoint,endPoint);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                currentPoint = (Point) animation.getAnimatedValue();
               // oldPoint = currentPoint;
                invalidate();
            }
        });
        valueAnimator.setDuration(3000);
        valueAnimator.start();
    }

    class CosEvaluate implements TypeEvaluator<Point> {


        @Override
        public Point evaluate(float fraction, Point startValue, Point endValue) {
            int startX=startValue.x;
            int endX= (int) (startX+fraction*(endValue.x-startX));
           // int startY=startValue.y;
            int endY= (int) ((1/2)*Math.cos(endX+1)*Math.PI+0.5);
            Point point=new Point(endX,endY);
            return point;
        }
    }
}
