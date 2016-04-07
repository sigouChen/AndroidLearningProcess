package com.plu.huangxingli.androidlearningprocess.Utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Parcelable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.TouchDelegate;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;


import java.lang.reflect.Method;
import java.util.List;

/**
 * @author gufei 562401002@qq.com
 * @Description: TODO(超级工具类与控件相关)
 * @date 2015年3月17日 下午4:06:44
 */
public class UiTools {

    /**
     * 得到控件中的字符串/多个时则字符串拼接
     */
    public static String getString(View... v) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < v.length; i++) {
            if (v[i] instanceof EditText) {
                stringBuffer.append(((EditText) v[i]).getText().toString()
                        .trim());
            } else if (v[i] instanceof TextView) {
                stringBuffer.append(((TextView) v[i]).getText().toString()
                        .trim());
            } else if (v[i] instanceof Button) {
                stringBuffer
                        .append(((Button) v[i]).getText().toString().trim());
            }
        }
        return stringBuffer.toString();
    }

    /**
     * 清空控件
     *
     * @param v
     */
    public static void cleanView(View... v) {
        if (v == null) {
            return;
        }
        for (int i = 0; i < v.length; i++) {
            if (v[i] instanceof EditText) {
                EditText editText = (EditText) v[i];
                editText.setText("");
            } else if (v[i] instanceof TextView) {
                TextView tv = (TextView) v[i];
                tv.setText("");
            }
        }

    }


    public static int getMeasureWidth(View view) {
        view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        return view.getMeasuredWidth();
    }

    public static int getMeasureHeight(View view) {
        view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        return view.getMeasuredHeight();
    }

    /**
     * 清空当前粘贴板
     *
     * @param context
     */
    public static void cleanClipboard(Context context) {
        ClipboardManager mClipboard = (ClipboardManager) context.getApplicationContext().getSystemService(Context.CLIPBOARD_SERVICE);
        if (mClipboard != null) {
            mClipboard.setPrimaryClip(ClipData.newPlainText("", ""));
        }
    }
}
