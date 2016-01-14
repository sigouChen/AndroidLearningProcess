package com.plu.huangxingli.androidlearningprocess.view;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
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
    private int color;

    //注意使用ObjectAnimation的时候，对要进行动画的属性必须要有set 和 get方法，注意 此处set方法里面要invalidate才起作用
    public void setColor(int color) {
        this.color = color;
        paint.setColor(color);
        invalidate();


    }

    public int getColor() {
        return color;
    }

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
                currentPos = (Point) animation.getAnimatedValue();
                invalidate();
            }
        });

        ObjectAnimator objectAnimator=ObjectAnimator.ofObject(this,"color",new ArgbEvaluator(), Color.RED,Color.BLUE);
        objectAnimator.setDuration(4000);
        objectAnimator.addUpdateListener(new ObjectAnimator.AnimatorUpdateListener(){

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                animation.getAnimatedValue();
            }
        });

        objectAnimator.start();
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
