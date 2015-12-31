package com.plu.huangxingli.androidlearningprocess.serviceAbout;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    private static String TAG="TAG";

    MyBinder myBinder=new MyBinder();
    int count=0;
    public MyService() {
    }

    /***
     *
     * @param intent
     * @return
     */
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        Log.v(TAG,"ONBIND");
        return myBinder;

    }
    public class MyBinder extends Binder{

        public int getCount(){

            return count;
        }
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.v(TAG,"SERVICE ONSTARTCOMMAND");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.v(TAG,"service onDestroy");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.v(TAG,"SERVICE ONCREATE");
    }
}
