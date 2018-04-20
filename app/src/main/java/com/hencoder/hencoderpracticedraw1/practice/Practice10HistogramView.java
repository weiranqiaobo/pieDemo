package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.Random;

public class Practice10HistogramView extends View {
    private int mDash = 10;
    private int mSquareWitdh;
    private String[] strings = new String[]{"sg", "sdf", "sld", "sldj", "sdf", "dfs", "www"};

    public Practice10HistogramView(Context context) {
        super(context);
    }

    public Practice10HistogramView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice10HistogramView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mSquareWitdh = (getWidth() - 300 - mDash * 7) / 7;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        综合练习
//        练习内容：使用各种 Canvas.drawXXX() 方法画直方图
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(2);
        canvas.drawLines(new float[]{150, getHeight() - 300, 150, 150, 150,
                getHeight() - 300, getWidth() - 150, getHeight() - 300}, 0, 8, paint);
        paint.setTextSize(40);
        canvas.drawText("直方图", getWidth() / 2 - 100, getHeight() - 150, paint);

        Random random = new Random();
        paint.setColor(Color.GREEN);
        for (int i = 0; i < 7; i++) {
            int top = getHeight() - 300 - random.nextInt(10) * 40;
            canvas.drawRect(new RectF((i + 1) * mDash + i * mSquareWitdh + 150, top, (i + 1) * mDash + i * mSquareWitdh + mSquareWitdh + 150, getHeight() - 300), paint);
            String text = strings[i];
            Rect rect = new Rect();
            paint.getTextBounds(text, 0, text.length(), rect);
            canvas.drawText(strings[i], (i + 1) * mDash + i * mSquareWitdh + 150 + (mSquareWitdh - rect.width()) / 2, getHeight() - 300  +40, paint);
        }

    }
}
