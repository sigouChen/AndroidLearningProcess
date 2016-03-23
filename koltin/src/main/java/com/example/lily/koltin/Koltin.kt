package com.example.lily.koltin

import android.util.Log

/**
 * Created by lily on 16-3-18.
 */
class Koltin(var name:String){

    init{
        Log.v("PLU"," Koltin init");
    }

    fun getName(va:String):Integer{
        Log.v("PLU","---I AM GETNAME METHOD")
        return Integer(3);
    }

    constructor():this("lily"){
        getName("lucy");
        Log.v("PLU","-constructor");
    }




   // var name="lily";
}