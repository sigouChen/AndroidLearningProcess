package com.plu.huangxingli.androidlearningprocess.activity;

import android.animation.FloatEvaluator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.plu.huangxingli.androidlearningprocess.BaseActivity;
import com.plu.huangxingli.androidlearningprocess.R;

import java.util.ArrayList;

/**
 * Created by lily on 16-1-9.
 */
public class ValueAnimatorActivity extends BaseActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animation_layout);
        ListView listView= (ListView) findViewById(R.id.listview);
        ArrayList<String> itemList=new ArrayList<>();
        for (int i=0;i<50;i++){
            itemList.add("item "+i);
        }
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(ValueAnimatorActivity.this,android.R.layout.simple_list_item_1,android.R.id.text1,itemList);
        listView.setAdapter(adapter);
        final TextView textView= (TextView) findViewById(R.id.textview);

       ValueAnimator valueAnimator=ValueAnimator.ofObject(new FloatEvaluator(),0f,30f);
       // ValueAnimator valueAnimator=ValueAnimator.ofFloat(0f, 30f);

      //  valueAnimator.setInterpolator(new );


        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                //Log.v("PLU","  ONAnimationUpdate value is "+value);
                textView.setTextSize(value);

            }
        });


        valueAnimator.setDuration(5000);
        valueAnimator.start();


    }


    class MyTypeValue implements TypeEvaluator<Float> {

        @Override
        public Float evaluate(float fraction, Float startValue, Float endValue) {

            float result=startValue+(endValue-startValue)*fraction;

            Log.v("PLU","---evaluate is "+result);
            return result;
        }
    }


}
