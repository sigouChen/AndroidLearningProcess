package com.plu.huangxingli.androidlearningprocess.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.plu.huangxingli.androidlearningprocess.R;
import com.plu.huangxingli.androidlearningprocess.Utils.PluLogUtil;
import com.plu.huangxingli.androidlearningprocess.view.CustomView;
import com.plu.huangxingli.androidlearningprocess.view.TopLayout;

public class ViewMeasureActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_measure);
        PluLogUtil.log("---______________--onCreate ");
        final CustomView customView= (CustomView) findViewById(R.id.customview);
        final TopLayout topLayout= (TopLayout) findViewById(R.id.toplayout);
        topLayout.post(new Runnable() {
            @Override
            public void run() {
                PluLogUtil.log("---->>>>>>width is "+topLayout.getWidth()+"====>>>> height is "+topLayout.getHeight());
            }
        });
        customView.post(new Runnable() {
            @Override
            public void run() {
                PluLogUtil.log(" !!!!!!!!customView measurewidth is "+customView.getMeasuredWidth()+"  customView measureheight is "+customView.getMeasuredHeight());
                PluLogUtil.log(" !!!!!!!!!customView width is "+customView.getWidth()+"  customView height is "+customView.getHeight());
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_measure, menu);
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
