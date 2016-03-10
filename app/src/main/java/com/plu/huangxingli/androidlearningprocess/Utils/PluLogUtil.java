package com.plu.huangxingli.androidlearningprocess.Utils;

import android.util.Log;



/**
 * Created by lily on 15-7-8.
 */
public class PluLogUtil {
    public static boolean debug=true;

    public static void log(String logInfo) {

        //Log.i("ss", "______________________________s:" + logInfo);
        if (debug) {
            Log.v("PLU", "---_PLU LOG " + logInfo);
        }
    }

    public static void log(String format, Object... args) {
        if (debug) {
            Log.v("PLU", "---_PLU LOG " + String.format(format, args));
        }
    }

    public static void log(Class<?> cls,String content){
        if (debug){
            Log.v(cls.getSimpleName(), content);
        }
    }

    public static void eLog(String log) {
        if (debug) {
            Log.e("PLU", "_______________" + log);
        }
    }

    public static void sLog(String tag, String content) {
        if (debug) {
            Log.v(tag, content);
        }
    }

}
