package com.plu.huangxingli.androidlearningprocess.RxJava;

import android.content.pm.InstrumentationInfo;
import android.graphics.Bitmap;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.plu.huangxingli.androidlearningprocess.BaseActivity;
import com.plu.huangxingli.androidlearningprocess.R;
import com.plu.huangxingli.androidlearningprocess.Utils.PluLogUtil;

import java.io.File;
import java.util.ArrayList;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class RxJavaActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java);
        final TextView textView1= (TextView) findViewById(R.id.textview1);
        TextView textView2= (TextView) findViewById(R.id.textview2);
        TextView textView3= (TextView) findViewById(R.id.textview3);
        final File file= new File("/sdcard/DCIM");
        final File[] folders=file.listFiles();
        ArrayList<Student> studentList = new ArrayList<>();
        for (int i=0;i<10;i++){
            Student student=new Student();
            student.setName("student " + i);
            Courses courses=new Courses();
            courses.setName("couse " + i);

            ArrayList<Courses> courseList=new ArrayList<>();
            if (i%2==0){
                Courses courses1=new Courses();
                courses1.setName("another course "+i);
                courseList.add(courses1);
            }
            courseList.add(courses);
            student.setCourses(courseList);
            studentList.add(student);
        }
       /* Observable.from(studentList).flatMap(new Func1<Student, Observable<Courses>>() {
            @Override
            public Observable<Courses> call(Student student) {
                PluLogUtil.log("-----flatMap call thread is " + Thread.currentThread().getName());
                return Observable.from(student.getCourses());
            }
        }).map(new Func1<Courses, Courses>() {
            @Override
            public Courses call(Courses courses) {
                courses.setName("map modi name ");
                PluLogUtil.log("----map thread is " + Thread.currentThread().getName());
                return courses;
            }
        }).observeOn(Schedulers.io()).doOnNext(new Action1<Courses>() {
            @Override
            public void call(Courses courses) {
                courses.setName("be modified");//注意做的事情正如操作的名字一样，是对于onNext方法里面参数的修改，此处对ｃｏｕｒses的修改都会传递到onNext里面去
                PluLogUtil.log("----doOnNext course is " + courses + " thread is " + Thread.currentThread().getName());
            }
        }).doOnSubscribe(new Action0() {
            @Override
            public void call() {
                PluLogUtil.log("----doOSubscribe thread is " + Thread.currentThread().getName());
            }
        }).subscribeOn(Schedulers.newThread()).subscribe(new Subscriber<Courses>() {
            @Override
            public void onCompleted() {
                Log.v("PLU", "--onCompleted thread is " + Thread.currentThread().getName());

            }

            @Override
            public void onError(Throwable e) {
                Log.v("PLU", "--onError is " + e.getMessage());
            }

            @Override
            public void onNext(Courses courses) {
                Log.v("PLU", " courses name is " + courses.getName());
            }
        });*/

        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {// IO 线程，由 subscribeOn() 指定
                subscriber.onNext(3);
                PluLogUtil.log(" --  onSubscribe call thread is "+Thread.currentThread().getName());

            }
        }).subscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .map(new Func1<Integer, Integer>() {
                    @Override
                    public Integer call(Integer integer) {// io线程，由 observeOn() 指定
                        PluLogUtil.log("---map 111 thread is " + Thread.currentThread().getName());
                        return integer + 1;
                    }
                })
                .observeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .map(new Func1<Integer, Integer>() {
                    @Override
                    public Integer call(Integer integer) {//io线程，由 observeOn() 指定
                        PluLogUtil.log("----map 2 22 thread is " + Thread.currentThread().getName());
                        return integer + 1;
                    }
                })
                .observeOn(Schedulers.newThread())
                .map(new Func1<Integer, Integer>() {
                    @Override
                    public Integer call(Integer integer) {// 新线程，由 observeOn() 指定
                        PluLogUtil.log("----map 333 thread is " + Thread.currentThread().getName());
                        return integer + 1;
                    }
                })
                .doOnSubscribe(new Action0() {//io线程，由其后的第一个subscribeOn所限定的线程决定
                    @Override
                    public void call() {
                        PluLogUtil.log("----doOnSubscribe thread is "+Thread.currentThread().getName());
                    }
                }).subscribeOn(Schedulers.io()).
                subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Integer integer) {//新线程，由最后一个observeOn的线程限定
                        PluLogUtil.log("---onNext thread is " + Thread.currentThread().getName());
                    }
                });

        /**
         * 03-10 23:20:51.360 8134-8134/? D/TAG_PluginDeliverService: PluginDeliverService start com.sina.weibo.plugin.PluginDeliverService$PluginDeliverService3
         ---_PLU LOG ----doOnSubscribe thread is RxCachedThreadScheduler-1
         ---_PLU LOG  --  onSubscribe call thread is RxCachedThreadScheduler-4
         ---_PLU LOG ---map 111 thread is RxCachedThreadScheduler-3
         ---_PLU LOG ----map 2 22 thread is RxCachedThreadScheduler-2
         ---_PLU LOG ----map 333 thread is RxNewThreadScheduler-1
         ---_PLU LOG ---onNext thread is RxNewThreadScheduler-1

         */

        //暂且理解是：1.subscribeOn指的是被观察者运行的线程2.observeOn是观察者运行的线程
        //需要注意的一点是map指令里面的代码所在的线程是　其前面的observeon所指定的线程（注意前面字眼）
        //map:１．如果map前面没有observeon且且页没有subscribeon则该map所在的线程为主线程，若只有subscribeon则是scribeon指定的线程
        //map：２．若map前面有多个observeon定义所运行线程，则使用的是最后一个observeron定义的线程,并且取决于其前面定义的那个observeon里面定义的线程
        //map:如果其后面也有obsereon定义的话，则取决于前面的那个定义，若前面有２个的话取决于最后定义的那个最近
        //doOnSubscribe:另外还需要注意的一点是　doOnSubscribe指令里面运行的代码所在的线程取决于其后面的
        // 第一个subscribeOn里面指定的线程的名字，请注意第一字眼和其后面的字眼
        //onNext:若指定了多个observeon，则subscribe的onNext里面运行的代码使用的线程是最后一个定义的subscribeon的线程,即最靠近sucriber的observeon里
        //面定义的线程




        /*Observable.from(folders).flatMap(new Func1<File, Observable<?>>() {
            @Override
            public Observable<?> call(File file) {
                return null;
            }
        }).flatMap(new Func1<File, Observable<?>>() {
        }).subscribe(new Action1<Bitmap>() {
            @Override
            public void call(Bitmap bitmap) {
                
            }
        });*/
        /*Subscriber<String> subscriber=new Subscriber<String>() {
            @Override
            public void onCompleted() {
                Log.v("PLU","onCompleted");

            }

            @Override
            public void onError(Throwable e) {
                Log.v("PLU","onError");
            }

            @Override
            public void onNext(String s) {
                textView1.setText(s);
                Log.v("PLU","onNext "+s);
            }
        };
        final String[] items={"one,two,three"};
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("hello");
            }
        }).map(new Func1<String, String>() {
            @Override
            public String call(String s) {
                return s+" appended";
            }
        }).subscribe(subscriber);

        Observable.create(new Observable.OnSubscribe<String>(){

            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("hello");
            }
        }).flatMap(new Func1<String, Observable<String>>() {
            @Override
            public Observable<String> call(String s) {
                return null;
            }
        }).subscribe(subscriber);*/



        //observable.subscribe(subscriber);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_rx_java, menu);
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
