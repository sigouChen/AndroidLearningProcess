package com.plu.huangxingli.androidlearningprocess.RxJava;

import android.os.Bundle;
import android.util.ArrayMap;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.plu.huangxingli.androidlearningprocess.BaseActivity;
import com.plu.huangxingli.androidlearningprocess.R;
import com.plu.huangxingli.androidlearningprocess.RxJava.operators.AssistOperators;
import com.plu.huangxingli.androidlearningprocess.RxJava.operators.AsyOperators;
import com.plu.huangxingli.androidlearningprocess.RxJava.operators.ChangeSignActivity;
import com.plu.huangxingli.androidlearningprocess.RxJava.operators.CombineOperators;
import com.plu.huangxingli.androidlearningprocess.RxJava.operators.ConditionAndBooleanOperators;
import com.plu.huangxingli.androidlearningprocess.RxJava.operators.ConnectionOperators;
import com.plu.huangxingli.androidlearningprocess.RxJava.operators.ErrorHandlerOperators;
import com.plu.huangxingli.androidlearningprocess.RxJava.operators.FilterOperators;
import com.plu.huangxingli.androidlearningprocess.RxJava.operators.MathematicalAndAggregateOperators;
import com.plu.huangxingli.androidlearningprocess.RxJava.operators.RxCreateSignActivity;
import com.plu.huangxingli.androidlearningprocess.adapter.TitleAdapter;

public class OperatorList extends BaseActivity {

    String[] operatorList={"创建操作符","变换操作符","过滤操作符","结合操作符","错误处理操作符","辅助操作符","条件和布尔操作符","算术和聚合操作符","异步操作符","连接操作符"};

    Class[] lessonActivity={RxCreateSignActivity.class,ChangeSignActivity.class, FilterOperators.class, CombineOperators.class, ErrorHandlerOperators.class,
            AssistOperators.class, ConditionAndBooleanOperators.class, MathematicalAndAggregateOperators.class, AsyOperators.class, ConnectionOperators.class};
    ArrayMap<String,Class> lessonMap=new ArrayMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operator_list);
        ListView listView= (ListView) findView(android.R.id.list);
        for (int i=0;i<operatorList.length;i++){
            lessonMap.put(operatorList[i],lessonActivity[i]);
        }

        TitleAdapter titleAdapter=new TitleAdapter(lessonMap,OperatorList.this);
        listView.setAdapter(titleAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //lessonAdapter.getItem(position);
                toActivity(OperatorList.this, lessonMap.valueAt(position));
            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_operator_list, menu);
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
