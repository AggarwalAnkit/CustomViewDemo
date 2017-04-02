package com.example.aa.customviewdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.View;

import java.util.List;

/**
 * Created by aa on 02/04/17.
 */

public class ConcentricCircles extends View {

    private Pair<Integer, Integer> mCenter;
    private List<Integer> mRadii;
    private List<Pair<Integer, Integer>> mPoints;
    private Paint mDrawCirclePaint;
    private Paint mDrawPointPaint;
    private Paint mBubblePaint;
    private Paint mTextPaint;
    private RectF mBubbleRectF;
    private Path mBubbleTrianglePath;
    private int mPointRadius = 5;
    private int mBubbleLeft = 50;
    private int mBubbleTop = 65;
    private int mBubbleCornerRadius = 4;

    public ConcentricCircles(Context context) {
        super(context);
        initPaint(context);
        initBubbleRect();
        initTrianglePath();
    }

    public ConcentricCircles(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint(context);
        initBubbleRect();
        initTrianglePath();
    }

    public ConcentricCircles(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint(context);
        initBubbleRect();
        initTrianglePath();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ConcentricCircles(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initPaint(context);
        initBubbleRect();
        initTrianglePath();
    }

    private void initPaint(Context context) {
        mDrawCirclePaint = new Paint();
        mDrawCirclePaint.setColor(ContextCompat.getColor(context, R.color.colorPrimary));
        mDrawCirclePaint.setAntiAlias(true);
        mDrawCirclePaint.setStyle(Paint.Style.STROKE);

        mDrawPointPaint = new Paint();
        mDrawPointPaint.setColor(ContextCompat.getColor(context, R.color.colorAccent));
        mDrawPointPaint.setAntiAlias(true);
        mDrawPointPaint.setStyle(Paint.Style.FILL);

        mBubblePaint = new Paint();
        mBubblePaint.setColor(Color.GREEN);
        mBubblePaint.setAntiAlias(true);
        mBubblePaint.setStyle(Paint.Style.FILL);

        mTextPaint = new Paint();
        mTextPaint.setColor(Color.CYAN);
        mTextPaint.setAntiAlias(true);
        mTextPaint.setStyle(Paint.Style.STROKE);
    }

    private void initBubbleRect() {
        mBubbleRectF = new RectF(0, 0, 100, 50);
    }

    private void initTrianglePath() {
        mBubbleTrianglePath = new Path();
        mBubbleTrianglePath.moveTo(40, 50);
        mBubbleTrianglePath.lineTo(50, 65);
        mBubbleTrianglePath.lineTo(60, 50);
        mBubbleTrianglePath.close();
    }

    public void setCenter(Pair<Integer, Integer> center) {
        this.mCenter = center;
    }

    public void setRadius(List<Integer> radii) {
        mRadii = radii;
    }

    public void setPoints(List<Pair<Integer, Integer>> points) {
        this.mPoints = points;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mCenter == null || mRadii == null || mRadii.isEmpty()) {
            return;
        }

        //draw center
        canvas.drawCircle(mCenter.first, mCenter.second, mPointRadius, mDrawPointPaint);

        //draw concentric circles
        for (int i = 0, size = mRadii.size(); i < size; i++) {
            canvas.drawCircle(mCenter.first, mCenter.second, mRadii.get(i), mDrawCirclePaint);
        }

        //draw points
        for (int i = 0, size = mPoints == null ? 0 : mPoints.size(); i < size; i++) {
            Pair<Integer, Integer> pair = mPoints.get(i);
            canvas.drawCircle(pair.first, pair.second, mPointRadius, mDrawPointPaint);

            canvas.save();
            canvas.translate(pair.first - mBubbleLeft, pair.second - mBubbleTop);
            //add click listeners on these RoundedRect
            canvas.drawRoundRect(mBubbleRectF, mBubbleCornerRadius, mBubbleCornerRadius, mBubblePaint);
            canvas.drawPath(mBubbleTrianglePath, mBubblePaint);
            canvas.restore();
        }
    }
}
