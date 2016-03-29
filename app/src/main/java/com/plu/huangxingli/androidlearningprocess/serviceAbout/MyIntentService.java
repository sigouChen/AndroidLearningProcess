package com.plu.huangxingli.androidlearningprocess.serviceAbout;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;


/**
 * Created by huangxingli on 2014/9/19.
 */
public class MyIntentService extends IntentService {
    private static String TAG="TAG";
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public MyIntentService(String name) {
        super("MyIntentService");
    }
    public MyIntentService(){
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.v(TAG,"onHandleIntent");
        /*longpic futrueTime=System.currentTimeMillis()+5000;
        //执行5s钟。
        while (System.currentTimeMillis()<futrueTime) {
            Log.v(TAG, "ONHANDLEINTENT");
            synchronized (this) {
                try {
                    Log.v(TAG,"WAIT");
                    wait(1000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }*/
        for (int i=0;i<15;i++){
            Log.v(TAG,"ONHandleThread "+i);
            try {
                Log.v(TAG,"CurrentThread id is "+Thread.currentThread().getName());
                Thread.currentThread().sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.v(TAG,"----onStartCommand");
        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.v(TAG,"----onCreate");
    }

    @Override
    public void onStart(Intent intent, int startId) {

        super.onStart(intent, startId);
        Log.v(TAG,"----INTENTSERVICE ONSTART");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.v(TAG,"===onDestroy");
    }
}
