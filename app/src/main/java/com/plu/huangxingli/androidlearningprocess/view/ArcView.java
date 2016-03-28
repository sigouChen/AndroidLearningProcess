package com.plu.huangxingli.androidlearningprocess.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.plu.huangxingli.androidlearningprocess.R;
import com.plu.huangxingli.androidlearningprocess.Utils.PluLogUtil;

/**
 * Created by lily on 16-3-28.
 */
public class ArcView extends RelativeLayout {

    ImageButton[] buttons=new ImageButton[3];
    private int arrayLength;
    private double angle;

    private double RL=280;

    OnToggleButtonClickListener onToggleButtonClickListener;
    private boolean isOpen;
    private int btnHeight;
    private int btnWidth;


    public ArcView(Context context) {
        super(context);
        init(context);
    }

    public ArcView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ArcView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    public void setOnToggleButtonClickListener(OnToggleButtonClickListener onToggleButtonClickListener) {
        this.onToggleButtonClickListener = onToggleButtonClickListener;
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

                RelativeLayout.LayoutParams layoutParams= new  RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);

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

    private void init(Context context){
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.activity_arc, this, true);
        final ImageButton btnHandler= (ImageButton) view.findViewById(R.id.imageButton);
        ImageButton btnMic= (ImageButton) view.findViewById(R.id.btn_mic);
        ImageButton btnFlash= (ImageButton) view.findViewById(R.id.btn_flash);
        ImageButton btnCamera= (ImageButton) view.findViewById(R.id.btn_camera);
        btnHandler.post(new Runnable() {
            @Override
            public void run() {
                btnWidth=btnHandler.getWidth();
                btnHeight=btnHandler.getHeight();
            }
        });

        buttons[0]=btnCamera;
        buttons[1]=btnMic;
        buttons[2]=btnFlash;

        arrayLength=buttons.length;

        angle = Math.PI / (2 * (arrayLength - 1));
        for (int i=0;i<arrayLength;i++){
            buttons[i].setLayoutParams(btnHandler.getLayoutParams());
            buttons[i].setOnClickListener(mOnClickListener);
        }
        btnHandler.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isOpen) {
                    closeToggle();

                }else{
                    openToggle();
                }
            }
        });





    }
    OnClickListener mOnClickListener=new OnClickListener() {
        @Override
        public void onClick(View v) {
            if (onToggleButtonClickListener!=null){
                onToggleButtonClickListener.onClick(v);
            }
        }
    };

    interface OnToggleButtonClickListener{
        void onClick(View view);
    }


}
