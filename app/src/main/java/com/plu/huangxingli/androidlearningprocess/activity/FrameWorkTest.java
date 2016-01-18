package com.plu.huangxingli.androidlearningprocess.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.plu.huangxingli.androidlearningprocess.R;
import com.plu.huangxingli.androidlearningprocess.Utils.PluLogUtil;

import com.plu.huangxingli.androidlearningprocess.componment.Location;

public class FrameWorkTest extends AppCompatActivity {

    boolean isHere=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame_work_test);

        final Location location=new Location(0);
        final TextView textView= (TextView) findViewById(R.id.textview);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isHere){
                 location.leaveHere();
                    isHere=false;
                }else {
                    location.goHere();
                    isHere=true;
                }
            }
        });
       location.setDetectHereListener(new Location.DetectHereListener() {
           @Override
           public void onGoHere() {
               PluLogUtil.log(Location.class, "---i am onGoHere");
               textView.setText("go Here");
           }

           @Override
           public void onLeaveHere() {
               PluLogUtil.log(Location.class, "---i am onLeave here");
               textView.setText(" leave here");
           }
       });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_frame_work_test, menu);
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
