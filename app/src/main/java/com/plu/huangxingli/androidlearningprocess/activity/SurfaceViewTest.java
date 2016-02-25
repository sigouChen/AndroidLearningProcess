package com.plu.huangxingli.androidlearningprocess.activity;


import android.graphics.Point;
import android.hardware.Camera;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.plu.huangxingli.androidlearningprocess.BaseActivity;
import com.plu.huangxingli.androidlearningprocess.R;
import com.plu.huangxingli.androidlearningprocess.view.CameraPreview;

import java.io.IOException;

/**
 * Created by lily on 16-1-11.
 */
public class SurfaceViewTest extends BaseActivity implements SurfaceHolder.Callback{



    private Camera camera;
    private SurfaceHolder surfaceHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.surface_layout);
      //  LinearLayout linearLayout= (LinearLayout) findViewById(R.id.container);

        SurfaceView surfacView= (SurfaceView) findViewById(R.id.surfaceview);

        surfaceHolder = surfacView.getHolder();
        Display display=getWindowManager().getDefaultDisplay();
        Point point=new Point();
        display.getSize(point);
        //要想要预览不变形，要做到２点，一是manifest的ａｃｔｉｖｉｔｙ设置|adjustResize，另一个要surfaceHolder.setFixSize要设置，这样才不会变形
        surfaceHolder.setFixedSize(point.x,600);
        surfaceHolder.addCallback(this);
       // camera=Camera.open();
       /* FrameLayout frameLayout= (FrameLayout) findView(R.id.camera_preview);
        CameraPreview cameraPreview=new CameraPreview(this,camera);
        frameLayout.addView(cameraPreview);*/
        //linearLayout.addView(cameraPreview);


       // camera.startPreview();
        //SurfaceView surfaceView= (CameraPreview) findViewById(R.id.surfaceview);

    }

    public static Camera getCameraInstance() {
        Camera c = null;
        try {
            c = Camera.open();
        } catch (Exception e) {
            Log.d("PLU", "��Cameraʧ��ʧ��");
        }
        return c;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        camera.release();
        camera=null;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        camera=getCameraInstance();

        if (camera!=null){
            camera.setDisplayOrientation(90);
            try {
                camera.setPreviewDisplay(surfaceHolder);
            } catch (IOException e) {
                e.printStackTrace();
            }
            camera.startPreview();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {


    }
}
