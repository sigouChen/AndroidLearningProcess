package com.plu.huangxingli.androidlearningprocess.FloatWindow;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.plu.huangxingli.androidlearningprocess.BaseActivity;
import com.plu.huangxingli.androidlearningprocess.R;

public class FloatWindowActivity extends BaseActivity {

    private static String TAG="TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_float_window);

        Button floatWindowButton= (Button) findViewById(R.id.button1);


        floatWindowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //windowManager.addView(floatView,layoutParams);
                Intent intent=new Intent(FloatWindowActivity.this,FloatWindowService.class);
                startService(intent);

            }
        });
        Button hideButton= (Button) findViewById(R.id.button2);
        hideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(TAG, "---隐藏按钮被点击");
                Intent intent=new Intent(FloatWindowActivity.this,FloatWindowService.class);
                stopService(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.float_window, menu);
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

