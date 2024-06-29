package com.example.idnpexamen_dos;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;

public class ProgressBarView extends View {

    private Paint paint;
    private int stepCount = 0;

    public ProgressBarView(Context context) {
        super(context);
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
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth();
        int height = getHeight();
        int arrowWidth = width / 5;

        for (int i = 0; i < stepCount; i++) {
            float left = i * arrowWidth;
            float right = left + arrowWidth;
            float centerX = (left + right) / 2;

            Path path = new Path();
            path.moveTo(left, 0);
            path.lineTo(right, 0);
            path.lineTo(centerX, height);
            path.close();

            paint.setColor(Color.rgb(255 - i * 50, 100 + i * 30, 50 + i * 40));
            canvas.drawPath(path, paint);
        }
    }
}

