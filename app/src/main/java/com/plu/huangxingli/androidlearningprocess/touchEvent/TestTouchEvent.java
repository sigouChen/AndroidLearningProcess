package com.plu.huangxingli.androidlearningprocess.touchEvent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.plu.huangxingli.androidlearningprocess.BaseActivity;
import com.plu.huangxingli.androidlearningprocess.R;
import com.plu.huangxingli.androidlearningprocess.Utils.PluLogUtil;

import java.io.BufferedReader;

public class TestTouchEvent extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_touch_event);
        final TouchLayout mTouchLayout= (TouchLayout) findViewById(R.id.touchlayout);
        CustomTextView customTextView= (CustomTextView) findViewById(R.id.customTv);
        customTextView.setTouchLayout(mTouchLayout);
        mTouchLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PluLogUtil.log("----mTouchLayout onClick");
            }
        });
        customTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PluLogUtil.log("----touchView onClick ");
            }
        });

        customTextView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action=event.getAction();
                switch (action){
                    case MotionEvent.ACTION_DOWN:
                        boolean parentInterception=mTouchLayout.onInterceptTouchEvent(event);
                        PluLogUtil.log("---down--parentInterception is "+parentInterception);
                        break;
                    case MotionEvent.ACTION_MOVE:
                        boolean parentInterception1=mTouchLayout.onInterceptTouchEvent(event);
                        PluLogUtil.log("---move--parentInterception is "+parentInterception1);
                        break;
                    case MotionEvent.ACTION_UP:
                        boolean parentInterception2=mTouchLayout.onInterceptTouchEvent(event);
                        PluLogUtil.log("--up---parentInterception is "+parentInterception2);
                        break;
                }

                return false;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_test_touch_event, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
