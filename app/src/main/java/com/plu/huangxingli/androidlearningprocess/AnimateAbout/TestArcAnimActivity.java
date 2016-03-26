package com.plu.huangxingli.androidlearningprocess.AnimateAbout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.plu.huangxingli.androidlearningprocess.MainActivity;
import com.plu.huangxingli.androidlearningprocess.R;

public class TestArcAnimActivity extends AppCompatActivity {

    private boolean isShowing;
    private RelativeLayout buttons_wrapper_layout;
    private ImageView buttons_show_hide_button;
    private RelativeLayout buttons_show_hide_button_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_arc_anim);

        MyAnimations.initOffset(TestArcAnimActivity.this);

        buttons_wrapper_layout = (RelativeLayout) findViewById(R.id.buttons_wrapper_layout);
        buttons_show_hide_button_layout = (RelativeLayout) findViewById(R.id.buttons_show_hide_button_layout);
        buttons_show_hide_button = (ImageView) findViewById(R.id.buttons_show_hide_button);

        buttons_show_hide_button_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isShowing) {
                    MyAnimations.startAnimationsIn(buttons_wrapper_layout, 300);
                    buttons_show_hide_button
                            .startAnimation(MyAnimations.getRotateAnimation(0,
                                    -270, 300));
                } else {
                    MyAnimations
                            .startAnimationsOut(buttons_wrapper_layout, 300);
                    buttons_show_hide_button
                            .startAnimation(MyAnimations.getRotateAnimation(
                                    -270, 0, 300));
                }
                isShowing = !isShowing;
            }
        });
        for (int i = 0; i < buttons_wrapper_layout.getChildCount(); i++) {
            buttons_wrapper_layout.getChildAt(i).setOnClickListener(new OnClickImageButton());
        }

    }


    class OnClickImageButton implements View.OnClickListener{

        @Override
        public void onClick(View arg0) {
            switch(arg0.getId()){

            }
        }

    }

}

