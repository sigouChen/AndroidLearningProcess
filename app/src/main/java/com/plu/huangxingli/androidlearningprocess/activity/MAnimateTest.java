package com.plu.huangxingli.androidlearningprocess.activity;

import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.plu.huangxingli.androidlearningprocess.R;
import com.plu.huangxingli.androidlearningprocess.view.CommonGifView;

public class MAnimateTest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manimate_test);
        final CommonGifView giftView= (CommonGifView) findViewById(R.id.giftView);
        giftView.setGiftCount(10);
        final CommonGifView giftview2= (CommonGifView) findViewById(R.id.giftview2);
        giftview2.setGiftCount(6);

       // giftView.startAnimate(10);

        Button button= (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnimationDrawable animationDrawable= (AnimationDrawable) getResources().getDrawable(R.drawable.animation_list);
                findViewById(R.id.animImageView).setBackground(animationDrawable);
                animationDrawable.start();
              /*  giftView.startAnimate(10);
                giftview2.startAnimate(6);*/

            }
        });
    }


}
