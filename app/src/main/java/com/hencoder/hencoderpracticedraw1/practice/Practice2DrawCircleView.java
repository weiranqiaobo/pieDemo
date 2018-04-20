package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice2DrawCircleView extends View {

    private Paint mPaint;

    public Practice2DrawCircleView(Context context) {
        this(context, null);
        initPaint();
    }

    public Practice2DrawCircleView(Context context, @Nullable AttributeSet attrs) {
        this(context, null, -1);
        initPaint();
    }

    public Practice2DrawCircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        练习内容：使用 canvas.drawCircle() 方法画圆
//        一共四个圆：1.实心圆 2.空心圆 3.蓝色实心圆 4.线宽为 20 的空心圆
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(3);
        mPaint.setAntiAlias(true);
        canvas.drawCircle(100, 100, 50, mPaint);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(300, 100, 50, mPaint);
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(100, 300, 50, mPaint);

        mPaint.setColor(Color.BLACK);
        Path path = new Path();
        path.addCircle(300, 300, 80, Path.Direction.CW);
        path.addCircle(300, 300, 100, Path.Direction.CW);
        path.setFillType(Path.FillType.EVEN_ODD);
        canvas.drawPath(path, mPaint);

    }

    private void initPaint() {
        mPaint = new Paint();
    }
}
