package com.plu.huangxingli.androidlearningprocess.activity;

import android.os.Bundle;

import com.plu.huangxingli.androidlearningprocess.BaseActivity;
import com.plu.huangxingli.androidlearningprocess.constructions.Parcel4;

/**
 * Created by lily on 16-2-1.
 */
public class TestInnerClass extends BaseActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Parcel4 parcel4=new Parcel4();
        parcel4.contents().value();
    }
}
