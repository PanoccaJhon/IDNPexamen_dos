package com.example.idnpexamen_dos;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;

public class ProgressBarView extends View {

    private Paint paint;
    private int stepCount = 0;

    public ProgressBarView(Context context) {
        super(context);
        init();
    }
    public ProgressBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    public ProgressBarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(5);
    }

    public void advance() {
        if (stepCount < 5) {
            stepCount++;
            invalidate();
        }
    }

    public void reset() {
        stepCount = 0;
        invalidate();
    }

    @Override
    protected void onDraw( Canvas canvas) {
        super.onDraw(canvas);

        int width = (int) (getWidth()*0.8);
        int height = (int) (getHeight()*0.6);
        int unitWidth = width / 5;

        for (int i = 0; i < stepCount; i++) {
            Path path = getPath(i, unitWidth, height);
            paint.setColor(Color.rgb(255 - i * 50, 100 + i * 30, 50 + i * 40));
            canvas.drawPath(path, paint);
        }
    }

    private static @NonNull Path getPath(int i, int arrowWidth, int height) {
        float left = i * arrowWidth;
        float right = left + arrowWidth-10;

        Path path = new Path();
        path.moveTo(left, 0);
        path.lineTo(right, 0);
        path.lineTo(right+ (float) arrowWidth /3, (float) height /2);
        path.lineTo(right, height);
        path.lineTo(left, height);
        path.lineTo(left+ (float) arrowWidth /3, (float) height /2);
        path.lineTo(left, 0);
        path.close();
        return path;
    }
}

