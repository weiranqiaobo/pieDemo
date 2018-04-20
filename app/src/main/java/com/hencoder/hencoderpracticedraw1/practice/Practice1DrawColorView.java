package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice1DrawColorView extends View {
    private Paint mPaint;

    public Practice1DrawColorView(Context context) {
        this(context, null);
        initPaint();
    }

    public Practice1DrawColorView(Context context, @Nullable AttributeSet attrs) {
        this(context, null, -1);
    }

    public Practice1DrawColorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        练习内容：使用 canvas.drawColor() 方法把 View 涂成黄色
//        黄色： Color.YELLOW
        canvas.drawColor(Color.YELLOW);


    }

    private void initPaint() {
        mPaint = new Paint();
    }
}
