package com.plu.huangxingli.androidlearningprocess;

import android.app.IntentService;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MyActivity extends ActionBarActivity {

    private String TAG="TAG";

    Button startButton,stopButton;
    ServiceConnection mServiceConnection;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        startButton= (Button) findViewById(R.id.button);
        stopButton= (Button) findViewById(R.id.button2);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(TAG, "BUTTON IS CLICKED");
                Intent intent = new Intent(MyActivity.this, MyService.class);
                //startService(intent);
                IntentService intentService;
                bindService(intent,mServiceConnection, Context.BIND_AUTO_CREATE);
                /***
                 * 调用startService之后，service调用onCreate 和onStartCommand方法,通过该方法启动的service会一直在运行，即使他的调用方已经结束了。
                 *所以如果使用这种方式启动，If you implement this, it is your responsibility to stop the service
                 *  Started
                 A service is "started" when an application component (such as an activity) starts it by calling startService().
                 Once started, a service can run in the background indefinitely, even if the component that started it is destroyed.
                 Usually, a started service performs a single operation and does not return a result to the caller. For example,
                 it might download or upload a file over the network. When the operation is done, the service should stop itself.
                 -----------------------------------------------------------------------------------------------------------------
                 If a component starts the service by calling startService() (which results in a call to onStartCommand()),
                 then the service remains running until it stops itself with stopSelf() or another component stops it by calling stopService().
                 If a component calls bindService() to create the service (and onStartCommand() is not called), then the service runs only as long as the component is bound to it.
                 Once the service is unbound from all clients, the system destroys it.
                 */
                /***
                 * 调用bindService要传进去一个ServiceConnection 参数，bindService方法调用后会调用onCreate ,自己实现的Binder的onBind方法，和onServiceConnected方法
                 * 调用次序是onCreate--->onBind----->onServiceConnected
                 * 通过该Binder可以实现Service和调用方的通信。
                 */
                Context context;

            }
        });
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MyActivity.this,MyService.class);

                unbindService(mServiceConnection);
            }
        });
        mServiceConnection=new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                MyService.MyBinder myBinder= (MyService.MyBinder) service;
                int count=myBinder.getCount();
                Log.v(TAG,"COUNT IS "+count);
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                Log.v(TAG,"!!1--onServiceDisconnected");
            }
        };
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v(TAG,"---onDestroy");
    }
}
