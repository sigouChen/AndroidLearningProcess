package com.plu.huangxingli.androidlearningprocess.view;

import android.content.Context;

import android.hardware.Camera;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

/**
 * Created by lily on 16-1-11.
 */
public class CameraPreview extends SurfaceView implements SurfaceHolder.Callback {

    android.hardware.Camera camera;

    public CameraPreview(Context context) {
        super(context);
    }

    public CameraPreview(Context context, Camera camera) {
        super(context);
        this.camera = camera;
        getHolder().addCallback(this);
    }

    public CameraPreview(Context context, AttributeSet attrs, Camera camera) {
        super(context, attrs);
        this.camera = camera;
        getHolder().addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        try {
            Log.v("PLU","---camera is "+camera);
            camera.setPreviewDisplay(holder);
        } catch (IOException e) {
            e.printStackTrace();
        }

        camera.startPreview();


    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
}
