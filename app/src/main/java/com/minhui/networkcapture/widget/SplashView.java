package com.minhui.networkcapture.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * 自定义控件
 * 模仿腾讯TIM 办公卡片页面风格
 *
 */
public class SplashView extends View {

    private Paint mRectanglePaint;
    private Path mRectanglePath;

    private Paint mTrianglePaint1;
    private Path mTrianglePath1;

    private Paint mTrianglePaint2;
    private Path mTrianglePath2;

    public SplashView(Context context) {
        super(context);
        paint();
    }

    public SplashView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint();
    }

    public SplashView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint();
    }

    private void paint(){
        mRectanglePaint = new Paint();
        mRectanglePaint.setDither(true);//设定是否使用图像抖动处理，会使绘制出来的图片颜色更加平滑和饱满，图像更加清晰
        mRectanglePaint.setAntiAlias(true);//设置抗锯齿
        mRectanglePaint.setStrokeWidth(5);
        mRectanglePaint.setStyle(Paint.Style.FILL);
        mRectanglePaint.setStrokeCap(Paint.Cap.ROUND);
//        mRectanglePaint.setColor(Color.parseColor("#408BE7"));
        mRectanglePaint.setColor(Color.parseColor("#FFE610"));

        mTrianglePaint1 = new Paint(mRectanglePaint);
//        mTrianglePaint1.setColor(Color.parseColor("#38479B"));
        mTrianglePaint1.setColor(Color.parseColor("#6a8dc3"));

        mTrianglePaint2 = new Paint(mRectanglePaint);
        mTrianglePaint2.setColor(Color.parseColor("#ffffff"));

        mRectanglePath = new Path();
        mTrianglePath1 = new Path();
        mTrianglePath2 = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //1、绘矩形，底层背景
        int rectangleY = getHeight() - 450;
        mRectanglePath.lineTo(getWidth(), 0);           //1、从左上角位置0,0向右上角绘制一条线到右上角
        mRectanglePath.lineTo(getWidth(), rectangleY);     //2、从右上角向下绘制一条线到右边的，高度为控件高度减去150
        mRectanglePath.moveTo(0, 0);                  //3、将点移动到0,0左上角位置
        mRectanglePath.lineTo(0, getHeight());           //4、从左上角位置0,0绘制一条线到左下角，高度为控件高度
        mRectanglePath.lineTo(getWidth(), rectangleY);      //5、左下角向右绘制一条斜线到右边，高度为控件高度减去150
        canvas.drawPath(mRectanglePath, mRectanglePaint);

        //2、绘制第一个三角形 ，第二块图，靠左
        mTrianglePath1.moveTo(0, getHeight());
        mTrianglePath1.lineTo(0, rectangleY);
        mTrianglePath1.lineTo(450 + 200, getHeight());
        mTrianglePath1.lineTo(0, getHeight());
        canvas.drawPath(mTrianglePath1, mTrianglePaint1);

        //3、绘制右下角直角三角形白色背景图遮挡住部分颜色
        mTrianglePath2.moveTo(0, getHeight());
        mTrianglePath2.lineTo(getWidth(), rectangleY);
        mTrianglePath2.lineTo(getWidth(), getHeight());
        mTrianglePath2.lineTo(0, getHeight());
        canvas.drawPath(mTrianglePath2, mTrianglePaint2);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = measureDimension(100, widthMeasureSpec);
        int height = measureDimension(100, heightMeasureSpec);
        setMeasuredDimension(width, height);
    }

    private int measureDimension(int defSize, int measureSpec) {
        int result;

        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.EXACTLY)
            result = specSize;
        else {
            result = defSize;
            if (specMode == MeasureSpec.AT_MOST)
                result = Math.min(defSize, specSize);
        }
        return result;
    }



}
