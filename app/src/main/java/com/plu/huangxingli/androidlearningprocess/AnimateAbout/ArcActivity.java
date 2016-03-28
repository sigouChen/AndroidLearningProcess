package com.plu.huangxingli.androidlearningprocess.AnimateAbout;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.plu.huangxingli.androidlearningprocess.R;
import com.plu.huangxingli.androidlearningprocess.Utils.PluLogUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ArcActivity extends AppCompatActivity {

    @Bind(R.id.btn_mic)
    ImageButton btnMic;
    @Bind(R.id.btn_flash)
    ImageButton btnFlash;
    @Bind(R.id.btn_camera)
    ImageButton btnCamera;
    @Bind(R.id.imageButton)
    ImageButton imageButton;
    ImageButton[] buttons=new ImageButton[3];
    private int rightEdge;
    private int arrayLength;
    private double RL=280;
    private int bottomEdge;
    private boolean isOpen;

    private double angle;
    private int rightMargin;
    private int bottomMargin;
    private int buttonHeight;
    private int buttonWidth;
    private int screenWidth;

    @OnClick(R.id.imageButton) void onImageClick(){
        if (isOpen) {
            closeToggle();

        }else{
            openToggle();
        }



    }



    private Animation createCloseAnimate(int index) {

        TranslateAnimation translateAnimation=new TranslateAnimation(0,(float)(RL*Math.sin((index)*angle)),0,(float)(RL* Math.cos(index)*angle));
        translateAnimation.setDuration(2000);
        translateAnimation.setFillAfter(true);
        return translateAnimation;

    }

    private void closeToggle() {
        isOpen=false;
        for (int i=0;i<buttons.length;i++){
            PluLogUtil.log("----close toggle");
            buttons[i].setVisibility(View.INVISIBLE);
            float xOffset= (float) (RL*Math.sin(angle*(i)));
            float yOffset=(float)(RL* Math.cos(angle*(i)));
            buttons[i].startAnimation(createAnimate(xOffset, yOffset,0,0,buttons[i]));
        }
    }

    private void openToggle() {
        isOpen=true;
        for (int i=0;i<buttons.length;i++){
            PluLogUtil.log("-----openToggle");
            float xOffset= (float) (RL*Math.sin(angle*(i)));
            float yOffset=(float)(RL* Math.cos(angle*(i)));
            buttons[i].setVisibility(View.VISIBLE);
            buttons[i].startAnimation(createAnimate(-xOffset,-yOffset,(int)xOffset,(int)yOffset,buttons[i]));
        }
    }



    private Animation createAnimate(final float xOffset, final float yOffset, final int rightMargin1, final int bottomMargin1, final ImageButton button) {

        final TranslateAnimation translateAnimation=new TranslateAnimation(0,xOffset,0,yOffset);
        translateAnimation.setDuration(2000);
        translateAnimation.setFillAfter(true);
        translateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                RelativeLayout.LayoutParams layoutParams= new RelativeLayout.LayoutParams(buttonWidth,buttonHeight);

                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);

                layoutParams.setMargins(0, 0, rightMargin1, bottomMargin1);
                button.setLayoutParams(layoutParams);

                button.requestLayout();
                button.clearAnimation();//它大爷，此处记得clear掉动画，要不然会才出现怪异现象,比如会出现2个原始图片
                PluLogUtil.log("---button left is "+button.getLeft());

            }

            @Override
            public void onAnimationRepeat(Animation animation) {


            }
        });
        return translateAnimation;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arc);
        ButterKnife.bind(this);
        PluLogUtil.log("----SIN(90) IS " + Math.sin(Math.PI / 2));
        imageButton.post(new Runnable() {
            @Override
            public void run() {
                rightEdge = imageButton.getRight();
                bottomEdge = imageButton.getBottom();
                buttonWidth = imageButton.getWidth();
                buttonHeight = imageButton.getHeight();
                PluLogUtil.log("---button width is " + buttonWidth + " height is " + buttonHeight);
                rightMargin = ((RelativeLayout.LayoutParams) imageButton.getLayoutParams()).rightMargin;
                bottomMargin = ((RelativeLayout.LayoutParams) imageButton.getLayoutParams()).bottomMargin;
                PluLogUtil.log("----rightEdge is " + rightEdge);
                PluLogUtil.log("----bottomEdge is " + bottomEdge);
            }
        });
        screenWidth=getResources().getDisplayMetrics().widthPixels;




        buttons[0]=btnCamera;
        buttons[1]=btnMic;
        buttons[2]=btnFlash;

        arrayLength=buttons.length;
        angle=Math.PI/(2*(arrayLength-1));



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_arc, menu);
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
