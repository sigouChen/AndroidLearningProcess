package com.plu.huangxingli.androidlearningprocess.activity;


import android.hardware.Camera;
import android.os.Bundle;
import android.util.Log;
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
public class SurfaceViewTest extends BaseActivity{



    private Camera camera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.surface_layout);
        LinearLayout linearLayout= (LinearLayout) findViewById(R.id.container);
        camera=getCameraInstance();
       // camera=Camera.open();
        FrameLayout frameLayout= (FrameLayout) findView(R.id.camera_preview);
        CameraPreview cameraPreview=new CameraPreview(this,camera);
        frameLayout.addView(cameraPreview);
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
}
