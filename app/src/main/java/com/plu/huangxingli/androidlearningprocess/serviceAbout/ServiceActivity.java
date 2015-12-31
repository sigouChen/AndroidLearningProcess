package com.plu.huangxingli.androidlearningprocess.serviceAbout;

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

import com.plu.huangxingli.androidlearningprocess.R;

import java.util.logging.Handler;


public class ServiceActivity extends ActionBarActivity {

    private String TAG="TAG";

    Button startButton,stopButton,startIntentServiceButton,stopIntentServiceButton,jumpButton;
    ServiceConnection mServiceConnection;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        startButton= (Button) findViewById(R.id.button1);
        stopButton= (Button) findViewById(R.id.button2);
        startIntentServiceButton= (Button) findViewById(R.id.intentService_button);
        stopIntentServiceButton= (Button) findViewById(R.id.stop_intentService);
        jumpButton= (Button) findViewById(R.id.jump_button);
        jumpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  Intent intent=new Intent(ServiceActivity.this,SecondActivity.class);
               // startActivity(intent);
                //ServiceActivity.this.finish();
                android.os.Process.killProcess(android.os.Process.myPid());
            }
        });
        startIntentServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(TAG,"startIntentServiceButton is pressed");
                MyIntentService myIntentService=new MyIntentService("myIntentService");
                Intent intent=new Intent(ServiceActivity.this,MyIntentService.class);
                startService(intent);
              // bindService(intent,mServiceConnection,Context.BIND_AUTO_CREATE);
            }
        });
        stopIntentServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(ServiceActivity.this,MyIntentService.class);
                stopService(intent);
                /**
                 * 当调用stopService方法时，service的onDestroy方法会立即被调用，但是服务里面新开的线程还在跑，直到跑完。
                 */
               // unbindService(mServiceConnection);
            }
        });
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(TAG, "BUTTON IS CLICKED");
               // startService(intent);
                /**
                 * IntentService的启动方式跟service的启动方式是一样的，但他自己管理自己的service,此处调用了startService方式后，会调用onHandleIntent方法
                 * onHandleIntent-->onDestroy.
                 * 也就是说IntentService自己处理完onHandleIntent方法后，就会自己结束自己。大无畏的精神啊，然后就调用onDestroy了。
                 */
                Intent intent = new Intent(ServiceActivity.this, MyService.class);

                IntentService intentService;
                Handler handler;
                bindService(intent,mServiceConnection, Context.BIND_AUTO_CREATE);
                startService(intent);
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
                 * 调用bindService要传进去一个ServiceConnection 参数，bindService方法调用后会调用onCreate ,onBind方法，和onServiceConnected方法
                 * 调用次序是onCreate--->onBind----->onServiceConnected
                 * 通过该Binder可以实现Service和调用方的通信。
                 */
                /***
                 * 不管是startService还是bindService方式启动的服务。从启动服务的activity跳转到另一个activity，
                 * 该服务也还会继续跑。
                 * 但是两者的不同是：
                 * 当启动服务的activity destroy掉不会影响startService方式启动的service即使没有调用stopService，也不会有影响
                 * 但通过bindService启动的服务如果此时没有主动调用unBindService，然后就调用启动他的activity的finish方法，此时，该activity的
                 * onDestroy方法不会被调用，可能是因为资源没有释放的原因吧。如果此时你将你的程序按多任务键将其从历史任务中划掉，此时会调用onDestroy方法
                 * 当然也会报内存泄露的错误，因为之前没有调onDestroy方法就是因为资源没有释放，当然是猜测了。
                 * 但还有一种情况，如果你通过android.os.Process.killProcess(android.os.Process.myPid());
                 * 将程序杀死的话就不会报内存泄露的错误，也许该方法本身会帮你做些处理吧，但是activity的onDestroy方法依然不会被调用。
                 * 也就是说bindService方式启动的service是依附于启动他的activity的。
                 */
                Context context;

            }
        });
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ServiceActivity.this,MyService.class);

                unbindService(mServiceConnection);
               // stopService(intent);
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


    public static class SecondActivity extends ActionBarActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_second);
        }


        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.second, menu);
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
    }
}
