package com.plu.huangxingli.androidlearningprocess.RxJava;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.ArrayMap;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import com.plu.huangxingli.androidlearningprocess.R;
import com.plu.huangxingli.androidlearningprocess.RxBindingUsing.RxBindingActivityUsing;
import com.plu.huangxingli.androidlearningprocess.RxJava.operators.ChangeSignActivity;
import com.plu.huangxingli.androidlearningprocess.RxJava.operators.RxCreateSignActivity;
import com.plu.huangxingli.androidlearningprocess.adapter.TitleAdapter;

public class RXCatogry extends ListActivity {
    ArrayMap<String,Class> lessonMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxcatogry);
        lessonMap=new ArrayMap<>();
        lessonMap.put("Rx操作符使用", OperatorList.class);
        lessonMap.put("RxJava线程相关",RxJavaActivity.class);
        lessonMap.put("RxJava与Retrofit配合使用",RetrofitWithRxJava.class);
        lessonMap.put("RxBinding和RxJava一起使用", RxBindingActivityUsing.class);
       // lessonMap.put("RxJava操作符的使用",RxSignActivity.class);
       // lessonMap.put("变换操作符",ChangeSignActivity.class);
        TitleAdapter titleAdapter=new TitleAdapter(lessonMap,RXCatogry.this);
        setListAdapter(titleAdapter);
        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(RXCatogry.this,lessonMap.valueAt(position));
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_rxcatogry, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so longpic
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
