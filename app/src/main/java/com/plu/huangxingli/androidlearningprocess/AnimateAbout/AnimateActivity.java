package com.plu.huangxingli.androidlearningprocess.AnimateAbout;

import android.os.Bundle;

import com.plu.huangxingli.androidlearningprocess.BaseActivity;
import com.plu.huangxingli.androidlearningprocess.R;
import com.plu.huangxingli.androidlearningprocess.view.CustomAnimatorView;

/**
 * Created by lily on 16-1-14.
 */
public class AnimateActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animate_layout);
        CustomAnimatorView customAnimatorView= (CustomAnimatorView) findView(R.id.animate_view);


    }
}
