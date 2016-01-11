package com.plu.huangxingli.androidlearningprocess;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class BaseActivity extends AppCompatActivity {


    public void toActivity(Context context,Class<?> cls){
        Intent intent = new Intent(context,cls);
        context.startActivity(intent);

    }

    public View findView(int resId){

        return findViewById(resId);
    }




}
