package com.unit33.main.widgets;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 *
 */
public class AspecImageView extends android.support.v7.widget.AppCompatImageView {
    public AspecImageView(Context context) {
        this(context, null);
    }

    public AspecImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AspecImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int desiredSizeWidth;
        float aspect;  //分辨率
        Drawable drawable = getDrawable();
        if (drawable == null) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            return;
        }
        desiredSizeWidth = drawable.getIntrinsicWidth(); //真实绘制的宽
        aspect = (float) drawable.getIntrinsicWidth() / (float) drawable.getIntrinsicHeight(); //宽高比

        int widthSize= View.resolveSize(desiredSizeWidth,widthMeasureSpec);
        int heightSize=(int)(widthSize/aspect);
        int specMode=MeasureSpec.getMode(heightMeasureSpec);
        int specSize=MeasureSpec.getSize(heightMeasureSpec);


        if (specMode == MeasureSpec.AT_MOST || specMode == MeasureSpec.EXACTLY) {
            if (heightSize > specSize) {
                heightSize=specSize;
                widthSize=(int)(heightSize*aspect);
            }
        }
        setMeasuredDimension(widthSize,heightSize);
    }
}
