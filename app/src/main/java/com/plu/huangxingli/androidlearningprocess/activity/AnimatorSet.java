package com.plu.huangxingli.androidlearningprocess.activity;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;

import com.plu.huangxingli.androidlearningprocess.R;
import com.plu.huangxingli.androidlearningprocess.Utils.PluLogUtil;
import com.plu.huangxingli.androidlearningprocess.Utils.UiTools;

public class AnimatorSet extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animator_set);
        android.animation.AnimatorSet animatorSet=new android.animation.AnimatorSet();
        TextView textView= (TextView) findViewById(R.id.textview);

        for (int i=0;i<10;i++){
            PluLogUtil.log("-1111111--for i is "+i);
            if (i==5){
                break;
            }
            PluLogUtil.log("------after 111111");
        }

        for (int i=0;i<10;i++){
            PluLogUtil.log("----222222for i is "+i);
            if (i==5){
                return;
            }
            PluLogUtil.log("-----after --22222");
        }

        int windowWidth=getResources().getDisplayMetrics().widthPixels;
        int textviewWidth=UiTools.getMeasureWidth(textView);

        ObjectAnimator firstAnimator=ObjectAnimator.ofFloat(textView, "x", windowWidth, UiTools.getMeasureWidth(textView));
        ObjectAnimator secondAnimator=ObjectAnimator.ofFloat(textView,"x",textviewWidth,-textviewWidth);
        android.animation.AnimatorSet animatorSet1=new android.animation.AnimatorSet();
        firstAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                PluLogUtil.log("-----onAnimationEnd");
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animatorSet1.setInterpolator(new LinearInterpolator());
        animatorSet1.play(firstAnimator).before(secondAnimator);
        animatorSet1.setDuration(3000);
        animatorSet1.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                PluLogUtil.log("---set-onAnimationEnd");

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animatorSet1.start();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_animator_set, menu);
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
