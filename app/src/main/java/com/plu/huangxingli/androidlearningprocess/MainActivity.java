package com.plu.huangxingli.androidlearningprocess;

import android.os.Bundle;
import android.util.ArrayMap;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.plu.huangxingli.androidlearningprocess.AnimateAbout.AnimateActivity;
import com.plu.huangxingli.androidlearningprocess.DrawerAbout.DrawerLayoutActivity;
import com.plu.huangxingli.androidlearningprocess.FloatWindow.FloatWindowActivity;
import com.plu.huangxingli.androidlearningprocess.TimerAbout.TimerActivity;
import com.plu.huangxingli.androidlearningprocess.activity.AnimatorAboutActivity;
import com.plu.huangxingli.androidlearningprocess.activity.SurfaceViewTest;
import com.plu.huangxingli.androidlearningprocess.activity.TimerTextViewActivity;
import com.plu.huangxingli.androidlearningprocess.serviceAbout.ServiceActivity;
import com.plu.huangxingli.androidlearningprocess.adapter.TitleAdapter;

/**
 * Created by lily on 15-12-31.
 */
public class MainActivity extends BaseActivity {

    //ArrayList<String> lessonList=new ArrayList<>();
    //SparseArray<Class> lessonArray=new SparseArray<>();
    //Map<String,Class> lessonMap=new HashMap<>();
    ArrayMap<String,Class> lessonMap=new ArrayMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initLessonTitle();

        ListView listView= (ListView) findView(R.id.listview);

        TitleAdapter titleAdapter=new TitleAdapter(lessonMap,MainActivity.this);
        listView.setAdapter(titleAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //lessonAdapter.getItem(position);
                toActivity(MainActivity.this,lessonMap.valueAt(position));
            }
        });

    }

    public void initLessonTitle(){
        lessonMap.put("service相关", ServiceActivity.class);
        lessonMap.put("悬浮框相关", FloatWindowActivity.class);
        lessonMap.put("倒计时类", TimerActivity.class);
        lessonMap.put("抽屉布局",DrawerLayoutActivity.class);
        lessonMap.put("摄像头相关", SurfaceViewTest.class);
        lessonMap.put("listview动画", AnimatorAboutActivity.class);
        lessonMap.put("计时textview使用", TimerTextViewActivity.class);
        lessonMap.put("自定义动画view",AnimateActivity.class);

    }
}
