package com.plu.huangxingli.androidlearningprocess.FloatWindow;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.WindowManager;
import android.widget.LinearLayout;

import java.lang.reflect.Field;

/**
 * Created by huangxingli on 2014/9/22.
 */
public class MyWindowManager  {
    public static WindowManager windowManager;

    public static LayoutInflater inflater;
    private static int xInView,yInView,xInScreen,yInScreen,statusHeight;
    private static  String TAG="TAG";
    public Context context;
    public static MyFloatView floatView;

    private static WindowManager.LayoutParams layoutParams;

    public MyWindowManager(Context context){
        this.context=context;
    }

    public static void createFloatWindow(final Context context){
        windowManager=getWindowManager(context);
         layoutParams=new WindowManager.LayoutParams();
        inflater=LayoutInflater.from(context);
       // floatView=inflater.inflate(R.layout.flaotlayout,null);
        floatView=new MyFloatView(context);

        layoutParams.type=WindowManager.LayoutParams.TYPE_PHONE;
        layoutParams.width= LinearLayout.LayoutParams.WRAP_CONTENT;
        layoutParams.height=LinearLayout.LayoutParams.WRAP_CONTENT;
        layoutParams.flags=WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE|WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL;
        int screenWidth=windowManager.getDefaultDisplay().getWidth();
        int screenHeight=windowManager.getDefaultDisplay().getHeight();
        layoutParams.gravity= Gravity.LEFT|Gravity.TOP;
        layoutParams.x=screenWidth;
        layoutParams.y=screenHeight/2;
        floatView.setParams(layoutParams);
       // layoutParams.flags= WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE|WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL|WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        windowManager.addView(floatView,layoutParams);

    }
    public static WindowManager getWindowManager(Context context){
        if (windowManager==null){
            windowManager= (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        }
        return  windowManager;
    }

    public static void removeFloatWindow(Context context){
        windowManager=getWindowManager(context);
        windowManager.removeView(floatView);
    }

    public static boolean  isWindowShowing(){
        return floatView==null?false:true;
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
