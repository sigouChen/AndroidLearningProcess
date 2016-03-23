package com.plu.huangxingli.androidlearningprocess.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.plu.huangxingli.androidlearningprocess.R;
import com.plu.huangxingli.androidlearningprocess.SecondActivity;
import com.plu.huangxingli.androidlearningprocess.Utils.PluLogUtil;

import java.net.URI;
import java.net.URISyntaxException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TestActivityTask extends AppCompatActivity {

    @Bind(R.id.button)
    Button button;

    @OnClick(R.id.button) void jumpToOtherActivity(){

            Uri uri=Uri.parse("tel:13305393782");
            Intent intent=new Intent(Intent.ACTION_VIEW,uri);
            startActivityForResult(intent,100);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_activity_task);
        ButterKnife.bind(this);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        PluLogUtil.log("---onActivityResult resultCode is "+resultCode+"requst code is "+requestCode);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_test_activity_task, menu);
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
