package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.hencoder.hencoderpracticedraw1.R;

import java.util.Random;

public class Practice11PieChartView extends View {
    /**
     * 扇形的画笔
     */
    private Paint mPiePaint;
    /**
     * 折线的画笔
     */
    private Paint mLinePaint;
    /**
     * 文字的画笔
     */
    private Paint mTextPaint;
    /**
     * 标题的画笔
     */
    private Paint mTitlePaint;

    /**
     * 饼图的颜色
     */
    private int[] mColors = {Color.YELLOW, Color.RED, Color.GREEN, Color.BLUE, Color.GRAY, Color.LTGRAY};
    /**
     * 初始化各个扇形的角度
     */
    private float[] mPieAngles = {123, 73, 63, 53, 23, 13};
    /**
     * 初始化文字
     */
    private String[] texts = {"feng", "fang", "wei", "ran", "hello world", "some a bitch"};

    /**
     * 扇形最大的间隔度数
     */
    private int angleDash = 2;

    /**
     * 扇形的半径
     */
    private int mRadius = 200;
    /**
     * view 的宽
     */
    private int mViewWidth;
    /**
     * view 的高
     */
    private int mViewHeight;
    /**
     * 构建扇形的矩形
     */
    private RectF mRectF;
    /**
     * 扇形的path
     */
    private Path mPiepath;

    /**
     * 扇形的起点
     */
    private int startAngle = 0;

    /**
     * 折线第一段的长度
     */
    private int lineFirstLength = 20;
    /**
     * 折线第二段的长度
     */
    private int lineSecondLength = 50;


    public Practice11PieChartView(Context context) {
        this(context, null);
    }

    public Practice11PieChartView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public Practice11PieChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //view 的可用高度
        mViewHeight = h - getPaddingTop() - getPaddingBottom();
        //view 的可用宽度
        mViewWidth = w - getPaddingLeft() - getPaddingRight();
        //画扇形的矩形坐标
        mRectF = new RectF(-mRadius, -mRadius, mRadius, mRadius);
    }

    private void init() {
        //扇形的画笔
        mPiePaint = new Paint();
        mPiePaint.setAntiAlias(true);
        mPiepath = new Path();
        //折线的画笔
        mLinePaint = new Paint();
        mLinePaint.setStrokeWidth(2);
        mLinePaint.setColor(Color.WHITE);
        mLinePaint.setAntiAlias(true);
        //文字的画笔
        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setColor(Color.WHITE);
        mTextPaint.setTextSize(25);
        //标题的画笔
        mTitlePaint = new Paint();
        mTitlePaint.setTextSize(40);
        mTitlePaint.setAntiAlias(true);
        mTitlePaint.setTextAlign(Paint.Align.CENTER);
        mTitlePaint.setColor(Color.WHITE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //将坐标原点平移到(mViewWidth / 2, mViewHeight / 2)位置处
        canvas.translate(mViewWidth / 2, mViewHeight / 2);
        //画文字和折线以及扇形
        drawPieAndLineAndText(canvas);
        //画标题
        drawTitle(canvas);
    }

    /**
     * 画标题
     *
     * @param canvas
     */
    private void drawTitle(Canvas canvas) {
        String title = "饼图";
        canvas.drawText(title, 0, mRadius + 100, mTitlePaint);
    }

    /**
     * 画 扇形  折线 以及文字
     *
     * @param canvas
     */
    private void drawPieAndLineAndText(Canvas canvas) {
        for (int i = 0; i < mPieAngles.length; i++) {
            //画扇形
            drawPie(canvas, i);
            //画折线
            drawLineAndText(canvas, i);
        }
    }

    /**
     * 画文字和折线
     *
     * @param canvas
     * @param i
     */
    private void drawLineAndText(Canvas canvas, int i) {
        if (i == 0) {
            //计算每个扇形的弧长的中心坐标
            float xStart = (float) ((mRadius + 10) * Math.cos(Math.toRadians(startAngle - angleDash - mPieAngles[i] / 2)));
            float yStart = (float) ((mRadius + 10) * Math.sin(Math.toRadians(startAngle - angleDash - mPieAngles[i] / 2)));
            float xStop = (float) ((mRadius + 10 + lineFirstLength) * Math.cos(Math.toRadians(startAngle - angleDash - mPieAngles[i] / 2)));
            float yStop = (float) ((mRadius + 10 + lineFirstLength) * Math.sin(Math.toRadians(startAngle - angleDash - mPieAngles[i] / 2)));
            canvas.drawLine(xStart, yStart, xStop, yStop, mLinePaint);
            if (xStop > xStart) {
                canvas.drawLine(xStop, yStop, xStop + lineSecondLength, yStop, mLinePaint);
                mTextPaint.setTextAlign(Paint.Align.LEFT);
                canvas.drawText(texts[i], xStop + lineSecondLength + 10, yStop, mTextPaint);
            } else {
                canvas.drawLine(xStop, yStop, xStop - lineSecondLength, yStop, mLinePaint);
                mTextPaint.setTextAlign(Paint.Align.RIGHT);
                canvas.drawText(texts[i], xStop - lineSecondLength - 10, yStop, mTextPaint);
            }

        } else {
            //计算每个扇形的弧长的中心坐标
            float xStart = (float) (mRadius * Math.cos(Math.toRadians(startAngle - angleDash - mPieAngles[i] / 2)));
            float yStart = (float) (mRadius * Math.sin(Math.toRadians(startAngle - angleDash - mPieAngles[i] / 2)));
            float xStop = (float) ((mRadius + lineFirstLength) * Math.cos(Math.toRadians(startAngle - angleDash - mPieAngles[i] / 2)));
            float yStop = (float) ((mRadius + lineFirstLength) * Math.sin(Math.toRadians(startAngle - angleDash - mPieAngles[i] / 2)));
            canvas.drawLine(xStart, yStart, xStop, yStop, mLinePaint);
            if (xStop > xStart) {
                canvas.drawLine(xStop, yStop, xStop + lineSecondLength, yStop, mLinePaint);
                mTextPaint.setTextAlign(Paint.Align.LEFT);
                canvas.drawText(texts[i], xStop + lineSecondLength + 10, yStop, mTextPaint);
            } else {
                canvas.drawLine(xStop, yStop, xStop - lineSecondLength, yStop, mLinePaint);
                mTextPaint.setTextAlign(Paint.Align.RIGHT);
                canvas.drawText(texts[i], xStop - lineSecondLength - 10, yStop, mTextPaint);
            }
        }
    }


    /**
     * 画扇形
     *
     * @param canvas
     * @param i
     */
    private void drawPie(Canvas canvas, int i) {
        //画扇形
        if (i == 0) {
            mPiepath.moveTo(10, 10);
            mPiepath.arcTo(new RectF(mRectF.left + 10, mRectF.top + 10,
                    mRectF.right + 10, mRectF.bottom + 10), startAngle, mPieAngles[i]);
        } else {
            mPiepath.moveTo(0, 0);
            mPiepath.arcTo(mRectF, startAngle, mPieAngles[i]);
        }
        mPiePaint.setColor(mColors[i]);
        canvas.drawPath(mPiepath, mPiePaint);
        mPiepath.reset();
        startAngle = (int) (startAngle + mPieAngles[i] + angleDash);
    }

}
