package com.plu.huangxingli.androidlearningprocess.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.widget.LinearLayout;

import com.plu.huangxingli.androidlearningprocess.Utils.PluLogUtil;

/**
 * Created by lily on 16-2-26.
 */
public class TopLayout extends LinearLayout {

    int windowWidth,windowHeight;

    public TopLayout(Context context) {
        super(context);
        init(context);
    }

    public TopLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public TopLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context){
        DisplayMetrics displayMetrics=getResources().getDisplayMetrics();
        windowWidth=displayMetrics.widthPixels;
        windowHeight=displayMetrics.heightPixels;

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        PluLogUtil.log("parent =====>>>>>widthMeasureSpec is " + widthMeasureSpec + " height MeasureSpec is " + heightMeasureSpec);
        int widthMeasureMode=MeasureSpec.getMode(widthMeasureSpec);
        int widthMeasureSize=MeasureSpec.getSize(widthMeasureSpec);

        int heightMeasureMode=MeasureSpec.getMode(heightMeasureSpec);
        int heightMeasureSize=MeasureSpec.getSize(heightMeasureSpec);
        //mode:这几个值打印的是自己的ｖｉｅｗ的layout_width 和layout_height 的mode
        //wrap_content:---->AT_MOST  match_parent,100px :--->EXACTLY
        //size: 父布局能提供的最大可使用宽高
        PluLogUtil.log("parent =====>>>>>AT_MOST IS "+MeasureSpec.AT_MOST+"MeasureSpec Exactly is "+MeasureSpec.EXACTLY+""+"MeasureSpec UNSPECIFIED "+MeasureSpec.UNSPECIFIED);
        PluLogUtil.log("parent =====>>>>>widthMeasureMode is "+widthMeasureMode+" widthMeasureMeasureSize is "+widthMeasureSize);
        PluLogUtil.log("parent =====>>>>>heightMeasureMode is "+heightMeasureMode+" heightMeasureSize is "+heightMeasureSize);
        //此处很奇怪，
        int widthMespec=MeasureSpec.makeMeasureSpec(windowWidth-200,MeasureSpec.EXACTLY);
        int heightMespec=MeasureSpec.makeMeasureSpec(windowHeight-200,MeasureSpec.EXACTLY);
        super.onMeasure(widthMespec, heightMespec);

        PluLogUtil.log("222222toplayout width is "+getMeasuredWidth()+"  toplayout height is "+getMeasuredHeight());
    }
}
