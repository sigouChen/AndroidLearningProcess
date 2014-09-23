package com.plu.huangxingli.androidlearningprocess.FloatWindow;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class FloatWindowService extends Service {
    MyWindowManager myWindowManager;
    private static String TAG="TAG";
    public FloatWindowService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
       // myWindowManager=new MyWindowManager(getApplicationContext());
       // myWindowManager.createFloatWindow(getApplicationContext());
        Log.v(TAG,"---onStartCommand");
        Log.v(TAG,"APPLICATION IS "+getApplicationContext());
        MyWindowManager.createFloatWindow(getApplicationContext());
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.v(TAG,"----onDestroy");
       if (MyWindowManager.isWindowShowing()){
           MyWindowManager.removeFloatWindow(getApplicationContext());
       }

    }
}
