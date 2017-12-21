package com.hpj.chooser.view;

import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatButton;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Property;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;

import com.hpj.chooser.R;
/**
 * Created by hpj16042 on 2017/12/20.
 */

public class ActionButton extends AppCompatButton {
    private Paint backgroundPaint;
    private float paintX, paintY, radius;

    public ActionButton(Context context) {
        super(context);
        init(null, 0);
    }

    public ActionButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public ActionButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        // Load attributes  
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.ActionButton, defStyle, 0);
        a.recycle();
        backgroundPaint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.save();
        canvas.drawCircle(paintX, paintY, radius, backgroundPaint);
        canvas.restore();

        super.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            //记录坐标  
            paintX = event.getX();
            paintY = event.getY();
            //启动动画  
            startAnimator();
        }
        return super.onTouchEvent(event);
    }

    private void startAnimator() {

        //计算半径变化区域  
        int start, end;

        if (getHeight() < getWidth()) {
            start = getHeight();
            end = getWidth();
        } else {
            start = getWidth();
            end = getHeight();
        }

        float startRadius = (start / 2 > paintY ? start - paintY : paintY) * 1.15f;
        float endRadius = (end / 2 > paintX ? end - paintX : paintX) * 0.85f;

        //新建动画  
        AnimatorSet set = new AnimatorSet();
        //添加变化属性  
        set.playTogether(
                //半径变化  
                ObjectAnimator.ofFloat(this, mRadiusProperty, startRadius, endRadius),
                //颜色变化 黑色到透明  
                ObjectAnimator.ofObject(this, mBackgroundColorProperty, new ArgbEvaluator(), Color.GRAY, Color.TRANSPARENT)
        );
        // 设置时间  
        set.setDuration((long) (1000 / end * endRadius));
        //先快后慢  
        set.setInterpolator(new DecelerateInterpolator());
        set.start();
    }


    private Property<ActionButton, Float> mRadiusProperty = new Property<ActionButton, Float>(Float.class, "radius") {
        @Override
        public Float get(ActionButton object) {
            return object.radius;
        }

        @Override
        public void set(ActionButton object, Float value) {
            object.radius = value;
            //刷新Canvas  
            invalidate();
        }
    };

    private Property<ActionButton, Integer> mBackgroundColorProperty = new Property<ActionButton, Integer>(Integer.class, "bg_color") {
        @Override
        public Integer get(ActionButton object) {
            return object.backgroundPaint.getColor();
        }

        @Override
        public void set(ActionButton object, Integer value) {
            object.backgroundPaint.setColor(value);
        }
    };
}
