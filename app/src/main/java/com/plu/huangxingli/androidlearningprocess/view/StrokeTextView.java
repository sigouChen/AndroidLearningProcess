package com.plu.huangxingli.androidlearningprocess.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.TextView;

import com.plu.huangxingli.androidlearningprocess.R;


/**
 * Created by lily on 15-11-11.
 */
public class StrokeTextView extends TextView {
    private TextView borderText = null;///用于描边的TextView
    private int strokeWidth;
    private int strokeColor;

    public StrokeTextView(Context context) {
        super(context);
        initAttrs(context, null);
        borderText = new TextView(context);
        init();
    }

    public StrokeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttrs(context, attrs);
        borderText = new TextView(context, attrs);
        init();
    }

    public StrokeTextView(Context context, AttributeSet attrs,
                          int defStyle) {
        super(context, attrs, defStyle);
        initAttrs(context, attrs);
        borderText = new TextView(context, attrs, defStyle);
        init();
    }

    private void initAttrs(Context context, AttributeSet attributeSet) {
        TypedArray typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.StrokeTextView);
        strokeWidth = typedArray.getDimensionPixelSize(R.styleable.StrokeTextView_stroke_text_stroke_width, 2);
        strokeColor = typedArray.getColor(R.styleable.StrokeTextView_stroke_text_color, getResources().getColor(R.color.border_text));
        typedArray.recycle();
    }

    public void init() {
        TextPaint tp1 = borderText.getPaint();
        tp1.setStrokeWidth(strokeWidth);                                  //设置描边宽度
        tp1.setStyle(Paint.Style.STROKE);                             //对文字只描边
        borderText.setTextColor(strokeColor);  //设置描边颜色
        borderText.setGravity(getGravity());
    }

    @Override
    public void setLayoutParams(ViewGroup.LayoutParams params) {
        super.setLayoutParams(params);
        borderText.setLayoutParams(params);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
       // CharSequence tt = borderText.getText();

        //两个TextView上的文字和图片必须一致，防止borderText和本StrokeTextView的图片重叠
       /* if (tt == null || !tt.equals(this.getText())
                || getCompoundDrawables()[0] != borderText.getCompoundDrawables()[0]
                || getCompoundDrawables()[1] != borderText.getCompoundDrawables()[1]
                || getCompoundDrawables()[2] != borderText.getCompoundDrawables()[2]
                || getCompoundDrawables()[3] != borderText.getCompoundDrawables()[3]) {
            borderText.setCompoundDrawables(getCompoundDrawables()[0], getCompoundDrawables()[1],
                    getCompoundDrawables()[2], getCompoundDrawables()[3]);*/
          //  borderText.setText(getText());
           // borderText.invalidate();
            //this.postInvalidate();
       // }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        borderText.measure(widthMeasureSpec, heightMeasureSpec);
    }

    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        borderText.layout(left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        CharSequence charSequence = getText();
        if (charSequence == null || !charSequence.equals(borderText.getText())) {
            borderText.setText(getText());
            borderText.invalidate();
        }
        borderText.draw(canvas);
        super.onDraw(canvas);
    }
}
