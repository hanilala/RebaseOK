package com.hani.colopi.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;

import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import androidx.annotation.Nullable;

import com.hani.colopi.R;

/**
 * 免费领礼包、领劵进度提示 bar,控件包含了灰底红色的进度条，以及「剩余XX」的文字提示
 */
public class ProgressBarTip extends View {


    private Paint mPaint;
    private int mProgress = 80;  // 0 - 100f;
    //红色进度条开始的颜色
    private int mStartColor;
    //红色进度条结束的颜色
    private int mEndColor;
    //文字大小
    private float mTxtSize;
    //文字颜色
    private int mTxtColor;
    //红色进度条的宽度
    private float mProgressBarWidth;
    //红色进度条的高度
    private float mProgressBarHeight;
    //圆角半径
    private int mRadius;
    //底部灰色进度条的颜色
    private int mBarBackgroudColor;
    private RectF mBarRectF;

    private RectF mBackgroundRectF;
    private Paint mBackgroundPaint;
    //进度条和文字之间的距离
    private float mDistance;

    private int mWidth;
    private int mHeight;
    private float mDescent;
    private LinearGradient mLinearGradient;

    public ProgressBarTip(Context context) {
        this(context,null);
    }

    public ProgressBarTip(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ProgressBarTip(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        setDefaultValue(context);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ProgressBarTip);
        mStartColor = typedArray.getColor(R.styleable.ProgressBarTip_progressBarTip_startColor, mStartColor);
        mEndColor = typedArray.getColor(R.styleable.ProgressBarTip_progressBarTip_endColor, mEndColor);

        if (typedArray.hasValue(R.styleable.ProgressBarTip_progressBarTip_txtSize)){
            float dimension = typedArray.getDimension(R.styleable.ProgressBarTip_progressBarTip_txtSize,9);
            mTxtSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dimension,getResources().getDisplayMetrics());
        }

        mTxtColor = typedArray.getColor(R.styleable.ProgressBarTip_progressBarTip_txtColor,mTxtColor);

        mRadius = typedArray.getDimensionPixelSize(R.styleable.ProgressBarTip_progressBarTip_radius,mRadius);
        mDistance = typedArray.getDimension(R.styleable.ProgressBarTip_progressBarTip_distance,mDistance);
        typedArray.recycle();

        init();
    }

    /**
     * 设置默认的参数
     * @param context
     */
    private void setDefaultValue(Context context){
        mStartColor = Color.parseColor("#FF4D4D");
        mEndColor = Color.parseColor("#FF8C66");
        mTxtSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,9, getResources().getDisplayMetrics());
        mTxtColor = Color.parseColor("#FFF35A58");

        mProgressBarWidth = DimensionUtil.dip2px(context,188);
        mProgressBarHeight = DimensionUtil.dip2px(context,4);

        mRadius = DimensionUtil.dip2px(context,2);
        mDistance = DimensionUtil.dip2px(context,6);
        mBarBackgroudColor = Color.parseColor("#EBEBEB");


    }

    private void init(){
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(mTxtSize);
        mPaint.setTextAlign(Paint.Align.LEFT);
        mBarRectF = new RectF();

        mBackgroundRectF =new RectF();
        mBackgroundPaint = new Paint();
        mBackgroundPaint.setAntiAlias(true);
        mBackgroundPaint.setColor(mBarBackgroudColor);
    }

    /**
     * 设置进度
     * @param progress
     */
    public void setProgress(int progress){
        if (progress >= 0 && progress <= 100){
            mProgress = progress;

            invalidate();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int outWidth = 0,outHeight = 0;

        Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
        float txtHeight = fontMetrics.descent - fontMetrics.ascent;
        mDescent = fontMetrics.descent;
        float txtWidth = mPaint.measureText(String.format("剩余%s%%",mProgress));

        //测量控件应该占的宽度、高度
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        if (widthMode == MeasureSpec.EXACTLY){
            //如果是精准模式，如设置了 188dp,或者是 match_parent
            mProgressBarWidth = widthSize;
            outWidth = (int) (mProgressBarWidth + mDistance + txtWidth);
        }else if (widthMode == MeasureSpec.AT_MOST){
            //wrap_content 模式
            outWidth = widthSize;
            mProgressBarWidth = widthSize - mDistance - txtWidth;
        }
        //控件的高度，取文字高度、进度条高度较大的一个
        outHeight = (int) (Math.max(mProgressBarHeight, txtHeight));

        setMeasuredDimension(outWidth,outHeight);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;

        float padding = (mHeight - mProgressBarHeight) / 2;
        mBackgroundRectF.set(0, padding,mProgressBarWidth,mHeight - padding);


        float length = mProgress / 100f * mProgressBarWidth;
        mBarRectF.set(0,padding,length,mHeight - padding);

        mLinearGradient= new LinearGradient(0,mHeight /2 ,length,mHeight/ 2,mStartColor,mEndColor, Shader.TileMode.CLAMP);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        mBackgroundPaint.setShader(null);
        mBackgroundPaint.setColor(mBarBackgroudColor);
        //先画灰色的进度bar
        canvas.drawRoundRect(mBackgroundRectF,mRadius,mRadius,mBackgroundPaint);
        if (mProgress > 0 ){
            //有进度的话，再画红色的进度bar
            mBackgroundPaint.setShader(mLinearGradient);
            canvas.drawRoundRect(mBarRectF,mRadius,mRadius,mBackgroundPaint);
        }
        mBackgroundPaint.setColor(mTxtColor);
        //最后画上文字，文字垂直居中
        canvas.drawText(String.format("剩余%s%%",mProgress),mProgressBarWidth + mDistance,mHeight -mDescent ,mPaint);

    }
}
