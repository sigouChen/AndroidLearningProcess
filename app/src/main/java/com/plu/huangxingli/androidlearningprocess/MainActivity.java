package com.plu.huangxingli.androidlearningprocess;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.ArrayMap;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.plu.huangxingli.androidlearningprocess.AnimateAbout.AnimateActivity;
import com.plu.huangxingli.androidlearningprocess.AnimateAbout.ArcActivity;
import com.plu.huangxingli.androidlearningprocess.AnimateAbout.GiftAnimationActivity;
import com.plu.huangxingli.androidlearningprocess.AnimateAbout.TestArcAnimActivity;
import com.plu.huangxingli.androidlearningprocess.DrawerAbout.DrawerLayoutActivity;
import com.plu.huangxingli.androidlearningprocess.FileAbout.FileActivity;
import com.plu.huangxingli.androidlearningprocess.FloatWindow.FloatWindowActivity;
import com.plu.huangxingli.androidlearningprocess.RxJava.RXCatogry;
import com.plu.huangxingli.androidlearningprocess.RxJava.RxJavaActivity;
import com.plu.huangxingli.androidlearningprocess.TimerAbout.TimerActivity;
import com.plu.huangxingli.androidlearningprocess.ToastAbout.ToastActivity;
import com.plu.huangxingli.androidlearningprocess.activity.CustomViewActivity;
import com.plu.huangxingli.androidlearningprocess.activity.DanmuKuActivity;
import com.plu.huangxingli.androidlearningprocess.activity.FrameWorkTest;
import com.plu.huangxingli.androidlearningprocess.activity.HandlerThreadActivity;
import com.plu.huangxingli.androidlearningprocess.activity.ImageActivity;
import com.plu.huangxingli.androidlearningprocess.activity.LongImageViewActivity;
import com.plu.huangxingli.androidlearningprocess.activity.MAnimateTest;
import com.plu.huangxingli.androidlearningprocess.activity.PopupAnimateActivity;
import com.plu.huangxingli.androidlearningprocess.activity.SelfRcViewActivity;
import com.plu.huangxingli.androidlearningprocess.activity.TabHostFragmentTest;
import com.plu.huangxingli.androidlearningprocess.activity.TestActivityTask;
import com.plu.huangxingli.androidlearningprocess.activity.TestInnerClass;
import com.plu.huangxingli.androidlearningprocess.activity.TimerTaskActivity;
import com.plu.huangxingli.androidlearningprocess.activity.ValueAnimatorActivity;
import com.plu.huangxingli.androidlearningprocess.activity.SurfaceViewTest;
import com.plu.huangxingli.androidlearningprocess.activity.TimerTextViewActivity;
import com.plu.huangxingli.androidlearningprocess.activity.ViewMeasureActivity;
import com.plu.huangxingli.androidlearningprocess.bmobAbout.BomboActivity;
import com.plu.huangxingli.androidlearningprocess.fragment.TabHostFragment1;
import com.plu.huangxingli.androidlearningprocess.mvp.MvpActivity;
import com.plu.huangxingli.androidlearningprocess.realMvp.view.ArticleListActivity;
import com.plu.huangxingli.androidlearningprocess.retrofit.Main2Activity;
import com.plu.huangxingli.androidlearningprocess.serviceAbout.ServiceActivity;
import com.plu.huangxingli.androidlearningprocess.adapter.TitleAdapter;
import com.plu.huangxingli.androidlearningprocess.touchEvent.TestTouchEvent;

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
        lessonMap.put("ValueAnimation实现放大动画", ValueAnimatorActivity.class);
        lessonMap.put("计时textview使用", TimerTextViewActivity.class);
        lessonMap.put("自定义动画view",AnimateActivity.class);
        lessonMap.put("测试线程池与HandlerThread一起使用", HandlerThreadActivity.class);
        lessonMap.put("android 接口的封装", FrameWorkTest.class);
        lessonMap.put("bmob 测试使用１１", BomboActivity.class);
        lessonMap.put("file文件处理相关", FileActivity.class);
        lessonMap.put("自定义toast", ToastActivity.class);
        lessonMap.put("many timer tog", TimerTaskActivity.class);
        lessonMap.put("java编程思想代码", TestInnerClass.class);
        lessonMap.put("fragmentTabHost　demo", TabHostFragmentTest.class);
        lessonMap.put("retrofit 测试使用", Main2Activity.class);
        lessonMap.put("测试MVP的简单使用", ArticleListActivity.class);
        lessonMap.put("测试自定义View使用", ViewMeasureActivity.class);
        lessonMap.put("测试事件拦截机制", TestTouchEvent.class);
        lessonMap.put("RxJava使用",RXCatogry.class);
        lessonMap.put("自定义view的简单使用", CustomViewActivity.class);
        lessonMap.put("Activity的跳转处理",SecondActivity.class);
        lessonMap.put("Activity启动模式测试", TestActivityTask.class);
        lessonMap.put("动画测试",GiftAnimationActivity.class);
        lessonMap.put("ImageLoader 测市",ImageActivity.class);
        lessonMap.put("礼物动画测试",MAnimateTest.class);
        lessonMap.put("popup测试", PopupAnimateActivity.class);
        lessonMap.put("扇形弹出动画",SelfRcViewActivity.class);
        lessonMap.put("扇形图实现方式2", TestArcAnimActivity.class);
        lessonMap.put("弹幕测试",DanmuKuActivity.class);

        lessonMap.put("长图测试", LongImageViewActivity.class);




    }
}
