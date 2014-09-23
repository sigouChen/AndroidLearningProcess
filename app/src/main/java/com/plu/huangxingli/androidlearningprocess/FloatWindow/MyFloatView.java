package com.plu.huangxingli.androidlearningprocess.FloatWindow;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.plu.huangxingli.androidlearningprocess.R;

import java.lang.reflect.Field;

/**
 * Created by huangxingli on 2014/9/22.
 */
public class MyFloatView extends LinearLayout{

    private LayoutInflater inflater;
    private View view;
    private ImageView imageView;
    private static String TAG="TAG";
    private int xInView;
    private int yInView;
    private int xInScreen;
    private int statusHeight;
    private int yInScreen;
    private int xDownInScreen,yDownInScreen;
    private WindowManager.LayoutParams layoutParams;
    private WindowManager windowManager;
    private Context context;
    private boolean isCapturing=false;


    public MyFloatView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public MyFloatView(Context context){
        super(context);
        this.context=context;
        windowManager=MyWindowManager.getWindowManager(context);
        layoutParams=new WindowManager.LayoutParams();
        inflater=LayoutInflater.from(context);
        view=inflater.inflate(R.layout.flaotlayout,null);
        imageView= (ImageView) view.findViewById(R.id.imagview);
       /* imageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(TAG,"---IMAGEVIEW is onClicked");
            }
        });*/
        LayoutParams layoutParams=new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        addView(view,layoutParams);



    }

    public void setParams(WindowManager.LayoutParams layoutParams){
        this.layoutParams=layoutParams;
    }

   /* @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
            break;
            case MotionEvent.ACTION_MOVE:
            break;
            case MotionEvent.ACTION_UP:
                break;
        }
            return super.onTouchEvent(event);
    }*/

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                xInView= (int) event.getX();
                yInView= (int) event.getY();
                xDownInScreen= (int) event.getRawX();
                yDownInScreen= (int) event.getRawY();
                xInScreen= (int) event.getRawX();
                yInScreen= (int) event.getRawX();
                break;
            case MotionEvent.ACTION_MOVE:
                xInScreen= (int) event.getRawX();
                statusHeight=getStatusHeight(context);
                yInScreen= (int) event.getRawY()-statusHeight;
                layoutParams.x=xInScreen-xInView;

                layoutParams.y=yInScreen-yInView;

                windowManager.updateViewLayout(MyFloatView.this,layoutParams);
                break;
            case MotionEvent.ACTION_UP:
                Log.v(TAG,"---ACTION UP");
                if (xInScreen==xDownInScreen&&yInScreen==yDownInScreen){

                    if (!isCapturing){
                        Log.v(TAG,"----即将进行屏幕的录制");

                    }

                }
                break;
        }
        return super.onTouchEvent(event);
    }
    public static int getStatusHeight(Context context)  {
        Object obj;
        Field field;
        Class<?> cls;
        int x=0,statusHeight=0;
        try {
            cls=Class.forName("com.android.internal.R$dimen");
            obj=cls.newInstance();
            field=cls.getField("status_bar_height");
            x=Integer.parseInt(field.get(obj).toString());
            statusHeight=context.getResources().getDimensionPixelSize(x);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return statusHeight;
    }
}
