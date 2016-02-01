package com.plu.huangxingli.androidlearningprocess.constructions;

import android.content.Context;

import com.plu.huangxingli.androidlearningprocess.Utils.PluLogUtil;

import java.security.PrivilegedAction;

/**
 * Created by lily on 16-2-1.
 */
public class Parcel4 {
    //通过public方法获取private权限的内部类，且该内部类实现了外部接口，从而隐藏了具体实现
    private class PContents implements Contents{
        int mValue=10;

        @Override
        public int value() {
            PluLogUtil.log(Parcel4.class,"----contents value is "+mValue);
            return mValue;
        }
    }

    public Contents contents(){
        return new PContents();
    }
}
