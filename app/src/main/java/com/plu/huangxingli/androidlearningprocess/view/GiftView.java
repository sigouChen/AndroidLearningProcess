package com.plu.huangxingli.androidlearningprocess.view;

import android.content.Context;
import android.graphics.Matrix;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.OverScroller;
import android.widget.ScrollView;
import android.widget.Scroller;

import com.plu.huangxingli.androidlearningprocess.R;
import com.plu.huangxingli.androidlearningprocess.Utils.PluLogUtil;

import java.lang.ref.WeakReference;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by lily on 16-3-29.
 */
public class GiftView extends ScrollView {

    Matrix matrix;
    private Scroller mScroller;
    private int mPicHeight;

    private int mFrameCount=20;
    private int eachDis;
    private int drawCount;
    private int scrollY;
    private int lastScrollY;
    private DrawHandler drawHandler;
    private ImageView imageView;


    public GiftView(Context context) {
        super(context);
        init(context);
    }

    public GiftView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public GiftView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void init(Context context){
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.longimageview, this, true);
        imageView = (ImageView) view.findViewById(R.id.imagelong);
        imageView.post(new Runnable() {
            @Override
            public void run() {
                mPicHeight = imageView.getHeight();

                eachDis = mPicHeight / mFrameCount;
                PluLogUtil.log("---eachDis is "+eachDis+"  mPicHeight is "+mPicHeight);
            }
        });
        mScroller=new Scroller(context);
        imageView.setScaleType(ImageView.ScaleType.MATRIX);
        matrix= imageView.getImageMatrix();
        matrix.setScale(1.5f, 0.3f);
        invalidate();
       // mScroller.startScroll(0, 0, 0, 0);
        drawHandler = new DrawHandler(this);

       // startScroll(drawHandler);
    }

    public  void startScroll() {
        final Timer timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                // Message message=Message.obtain();
                PluLogUtil.log("----timer interval");

                drawCount++;
                Message message = Message.obtain(drawHandler, 0, drawCount);
                message.sendToTarget();
                if (drawCount == mFrameCount) {
                    PluLogUtil.log("---drawCount == mFrameCount");
                    drawHandler.removeCallbacksAndMessages(null);
                    timer.cancel();
                }
            }
        }, 0, 800);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int height=MeasureSpec.makeMeasureSpec(280,MeasureSpec.AT_MOST);
        PluLogUtil.log("---height is "+height+" each dis is "+eachDis);
        super.onMeasure(widthMeasureSpec,height);
    }

    static class DrawHandler extends Handler{
        WeakReference<GiftView> wf;

        public DrawHandler(GiftView giftView){
            wf=new WeakReference<GiftView>(giftView);
        }
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            GiftView giftView=wf.get();
            if (giftView!=null){
                giftView.handleDraw(msg);
            }
        }
    }




    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {
            PluLogUtil.log("---scrollTo x is "+mScroller.getCurrX()+" y is "+mScroller.getCurrY());
            scrollBy(0,scrollY);
           // scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            postInvalidate();
        }

    }

    private void handleDraw(Message message){
        PluLogUtil.log("---handel draw");
        int drawCount= (int) message.obj;
        scrollY=drawCount*eachDis;
       // imageView.setY(scrollY);
        PluLogUtil.log("----lastScrollY is " + lastScrollY + "  scrollY is " + scrollY);
       // mScroller.startScroll(0, lastScrollY, 0, scrollY);
        mScroller.startScroll(0, lastScrollY, 0, eachDis);


        lastScrollY = scrollY;
        invalidate();
    }


}
