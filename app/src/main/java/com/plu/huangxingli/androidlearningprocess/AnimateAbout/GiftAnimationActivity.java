package com.plu.huangxingli.androidlearningprocess.AnimateAbout;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.plu.huangxingli.androidlearningprocess.R;
import com.plu.huangxingli.androidlearningprocess.Utils.PluLogUtil;

import java.lang.ref.WeakReference;

import butterknife.Bind;
import butterknife.ButterKnife;

public class GiftAnimationActivity extends AppCompatActivity {

    @Bind(R.id.tvContent)
    TextView tvContent;
    @Bind(R.id.image)
    ImageView image;
    @Bind(R.id.layout_gift)
    RelativeLayout layoutGift;
    @Bind(R.id.listview)
    ListView listview;
    private float giftWidth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gift_animation);

        ButterKnife.bind(this);
        MyHandler myHandler=new MyHandler(GiftAnimationActivity.this);

        //Message message=myHandler.obtainMessage(0);
        layoutGift.post(new Runnable() {
            @Override
            public void run() {
                giftWidth = layoutGift.getWidth();
            }
        });

        myHandler.sendEmptyMessageDelayed(0,1000);


    }

    static class MyHandler extends Handler{
        WeakReference<GiftAnimationActivity> wf;

        public MyHandler(GiftAnimationActivity giftAnimationActivity) {
            wf=new WeakReference<>(giftAnimationActivity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (wf.get()!=null){
                GiftAnimationActivity giftAnimationActivity=wf.get();
                giftAnimationActivity.handleGifMsg();
            }
        }
    }

    private void handleGifMsg(){
        PluLogUtil.log("0----handleGifMsg");

        final TranslateAnimation translateAnimation=new TranslateAnimation(-giftWidth,0,0,0);

        translateAnimation.setDuration(1500);
        translateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                layoutGift.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                AnimatorSet animatorSet=new AnimatorSet();
                ObjectAnimator transalteAnimator=ObjectAnimator.ofFloat(layoutGift,"y",layoutGift.getY(),layoutGift.getY()-300);
                ObjectAnimator alphaAnimator=ObjectAnimator.ofFloat(layoutGift,"alpha",1.f,0f);
                animatorSet.play(transalteAnimator).with(alphaAnimator);
                animatorSet.setDuration(2000);
              //  layoutGift.startAnimation(translateAnimation1);
                animatorSet.start();

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        layoutGift.startAnimation(translateAnimation);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_gift_animation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
