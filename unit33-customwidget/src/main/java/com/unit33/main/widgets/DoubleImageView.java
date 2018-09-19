package com.unit33.main.widgets;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import com.unit33.main.R;

/**
 *
 */
public class DoubleImageView extends View {

    private Drawable mLeftDrawable, mRightDrawable;
    private CharSequence mText;
    private StaticLayout mTextLayout;

    private TextPaint mTextPaint;
    private Point mTextOrigin;
    private int mSpacing;


    public DoubleImageView(Context context) {
        this(context, null);
    }

    public DoubleImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DoubleImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs, defStyleAttr);
    }

    @SuppressLint("Recycle")
    public void initView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        mTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        mTextOrigin = new Point(0, 0);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.DoubleImageView, 0, defStyleAttr);
        Drawable drawableLeft = array.getDrawable(R.styleable.DoubleImageView_android_drawableLeft);
        if (drawableLeft != null) {
            setLeftDrawable(drawableLeft);
        }
        Drawable drawableRight = array.getDrawable(R.styleable.DoubleImageView_android_drawableRight);
        if (drawableRight != null) {
            setRightDrawable(drawableRight);
        }

        int spacing = array.getDimensionPixelOffset(R.styleable.DoubleImageView_android_spacing, 0);
        setSpacing(spacing);

        int color = array.getColor(R.styleable.DoubleImageView_android_textColor, Color.WHITE);
        setTextColor(color);

        int size = array.getDimensionPixelSize(R.styleable.DoubleImageView_android_textSize, 10);
        setTextSize(size);

        CharSequence text = array.getText(R.styleable.DoubleImageView_android_text);
        setText(text);

        array.recycle();
    }

    public void setLeftDrawableResource(int resId) {
        Drawable drawable = getResources().getDrawable(resId);
        setLeftDrawable(drawable);
    }

    private void setText(CharSequence text) {

    }

    private void setTextSize(int size) {

    }

    private void setTextColor(int color) {

    }

    private void setSpacing(int spacing) {

    }

    private void setRightDrawable(Drawable drawableRight) {

    }

    public void setLeftDrawable(Drawable leftDrawable) {
        mLeftDrawable = leftDrawable;
        updateContentBounds();

    }

    //第四步
    private void updateContentBounds() {
        if (mText == null) {
            mText = "";
        }
        float textWidth = mTextPaint.measureText(mText, 0, mText.length()); // 测量文字的长度

        mTextLayout = new StaticLayout(mText, mTextPaint, (int) textWidth, Layout.Alignment.ALIGN_CENTER, 1f, 0f, true);

        int left = (getWidth() - getDesiredWidth()) / 2;
        int top = (getHeight() - getDesiredHeight()) / 2;

        if (mLeftDrawable != null) {
            mLeftDrawable.setBounds(left, top, left + mLeftDrawable.getIntrinsicWidth(), top + mLeftDrawable.getIntrinsicHeight());
            left += (mLeftDrawable.getIntrinsicWidth() * 0.33f);
            top += (mLeftDrawable.getIntrinsicHeight() * 0.33f);
        }

        if (mRightDrawable != null) {
            mRightDrawable.setBounds(left, top, left + mRightDrawable.getIntrinsicWidth(), top + mRightDrawable.getIntrinsicHeight());
            left += (mRightDrawable.getIntrinsicWidth() * 0.33f);
            top += (mRightDrawable.getIntrinsicHeight() * 0.33f);
        }

    }

    //第二步 计算整个控件的宽
    private int getDesiredWidth() {
        int leftWidth;
        if (mLeftDrawable == null) {
            leftWidth = 0;
        } else {
            leftWidth = mLeftDrawable.getIntrinsicWidth();
        }

        int rightWidth;
        if (mRightDrawable == null) {
            rightWidth = 0;
        } else {
            rightWidth = mRightDrawable.getIntrinsicWidth();
        }

        int textWidth;
        if (mTextLayout == null) {
            textWidth = 0;
        } else {
            textWidth = mTextLayout.getWidth();
        }
        return (int) (leftWidth * 0.67f) + (int) (rightWidth * 0.67f) + mSpacing + textWidth;
    }

    //第三步 计算整个控件的高度
    private int getDesiredHeight() {
        int leftHeight;
        if (mLeftDrawable == null) {
            leftHeight = 0;
        } else {
            leftHeight = mLeftDrawable.getIntrinsicHeight();
        }

        int rightHeight;
        if (mRightDrawable == null) {
            rightHeight = 0;
        } else {
            rightHeight = mRightDrawable.getIntrinsicHeight();
        }

        int textHeight;
        if (mTextLayout == null) {
            textHeight = 0;
        } else {
            textHeight = mTextLayout.getHeight();
        }
        return (int) (leftHeight * 0.67f) + (int) (rightHeight * 0.67f) + mSpacing + textHeight;
    }

}
