package com.plu.huangxingli.androidlearningprocess.activity;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.plu.huangxingli.androidlearningprocess.R;

public class TestFontActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_font);
        TextView textView= (TextView) findViewById(R.id.textview);
        textView.setTypeface(Typeface.createFromAsset(getResources().getAssets(), "ARLRDBD_0.TTF"));
        textView.setText("ARLRDB_0字体");
        TextView textView1= (TextView) findViewById(R.id.textview1);
        textView1.setTypeface(Typeface.createFromAsset(getResources().getAssets(), "FZLTHJW_0.TTF"));

        textView1.setText("FZLTHJw字体");
        TextView textViewt2= (TextView) findViewById(R.id.textview2);
        textViewt2.setText("正常字体黄兴丽啥啊什么东西");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_test_font, menu);
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
