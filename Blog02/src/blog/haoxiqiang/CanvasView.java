package blog.haoxiqiang;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

public class CanvasView extends View {

    private Paint arcPaint;
    private RectF rectF;
    private Shader mShader;
    private Path mPath;
    private Bitmap bitmap;

    public CanvasView(Context context) {
        super(context);
    }

    public CanvasView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        arcPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        arcPaint.setColor(Color.BLUE);
        rectF = new RectF(260, 70, 290, 115);
        // 设置渐变色 这个正方形的颜色是改变的
        // 一个材质,打造出一个线性梯度沿著一条线。
        mShader =
                new LinearGradient(0, 0, 100, 100, new int[] {Color.RED, Color.GREEN, Color.BLUE,
                        Color.YELLOW, Color.LTGRAY}, null, Shader.TileMode.REPEAT);

        mPath = new Path();
        mPath.reset();
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // fill 填充,canvas内部维护了一个Bitmap
        canvas.drawARGB(255, 0, 180, 255);
        canvas.drawColor(Color.RED);
        // TODO PorterDuff.Mode 不了解
        canvas.drawColor(Color.BLACK, PorterDuff.Mode.CLEAR);
        canvas.drawRGB(255, 255, 255);
        // 创建画笔
        arcPaint.setColor(Color.RED);// 设置红色

        canvas.drawText("画圆：", 10, 50, arcPaint);// 画文本
        canvas.drawCircle(100, 45, 10, arcPaint);// 小圆
        arcPaint.setAntiAlias(true);// 设置画笔的锯齿效果。 true是去除，大家一看效果就明白了
        canvas.drawCircle(150, 45, 20, arcPaint);// 大圆

        canvas.drawText("画线及弧线：", 10, 100, arcPaint);
        arcPaint.setColor(Color.GREEN);// 设置绿色
        canvas.drawLine(160, 90, 210, 90, arcPaint);// 画线
        canvas.drawLine(210, 75, 270, 55, arcPaint);// 斜线


        arcPaint.setStyle(Paint.Style.STROKE);// 设置空心
        canvas.drawCircle(300, 100, 50, arcPaint);

        canvas.drawArc(rectF, 180, 180, false, arcPaint);// 小弧形
        rectF.set(310, 70, 340, 115);
        canvas.drawArc(rectF, 180, 180, false, arcPaint);// 小弧形
        rectF.set(290, 110, 310, 125);
        canvas.drawArc(rectF, 0, 180, false, arcPaint);// 小弧形

        canvas.drawText("画矩形：", 50, 150, arcPaint);
        arcPaint.setColor(Color.GRAY);// 设置灰色
        arcPaint.setStyle(Paint.Style.FILL);// 设置填满
        canvas.drawRect(160, 135, 210, 185, arcPaint);// 正方形
        canvas.drawRect(215, 150, 260, 210, arcPaint);// 长方形

        canvas.drawText("画扇形和椭圆:", 10, 250, arcPaint);

        arcPaint.setShader(mShader);
        rectF.set(150, 190, 290, 310);
        canvas.drawArc(rectF, 200, 130, true, arcPaint);
        // 画弧，第一个参数是RectF：该类是第二个参数是角度的开始，第三个参数是多少度，第四个参数是真的时候画扇形，是假的时候画弧线
        // 画椭圆，把oval改一下
        rectF.set(300, 200, 420, 300);
        canvas.drawOval(rectF, arcPaint);

        canvas.drawText("画三角形：", 10, 380, arcPaint);
        // 绘制这个三角形,你可以绘制任意多边形
        mPath.moveTo(100, 330);// 此点为多边形的起点
        mPath.lineTo(150, 430);
        mPath.lineTo(180, 350);
        mPath.close(); // 使这些点构成封闭的多边形
        canvas.drawPath(mPath, arcPaint);

        // // 你可以绘制很多任意多边形，比如下面画六连形
        arcPaint.reset();// 重置
        arcPaint.setColor(Color.LTGRAY);
        arcPaint.setStyle(Paint.Style.STROKE);// 设置空心
        mPath.reset();
        mPath.moveTo(280, 400);
        mPath.lineTo(300, 400);
        mPath.lineTo(310, 410);
        mPath.lineTo(300, 420);
        mPath.lineTo(280, 420);
        mPath.lineTo(270, 410);
        mPath.close();// 封闭
        canvas.drawPath(mPath, arcPaint);

        // Path类封装复合(多轮廓几何图形的路径 由直线段*、二次曲线,和三次方曲线，也可画以油画。drawPath(路径、油漆),要么已填充的或抚摸
        // (基于油漆的风格),或者可以用于剪断或画画的文本在路径。

        // 画圆角矩形
        arcPaint.setStyle(Paint.Style.FILL);// 充满
        arcPaint.setColor(Color.LTGRAY);
        arcPaint.setAntiAlias(true);// 设置画笔的锯齿效果
        canvas.drawText("画圆角矩形:", 10, 450, arcPaint);
        rectF.set(180, 430, 300, 470);// 设置个新的长方形
        canvas.drawRoundRect(rectF, 20, 15, arcPaint);// 第二个参数是x半径，第三个参数是y半径

        // 画贝塞尔曲线
        canvas.drawText("画贝塞尔曲线:", 10, 310, arcPaint);
        arcPaint.reset();
        arcPaint.setStyle(Paint.Style.STROKE);
        arcPaint.setColor(Color.GREEN);
        mPath.reset();
        mPath.moveTo(180, 310);// 设置Path的起点
        mPath.quadTo(250, 250, 200, 350); // 设置贝塞尔曲线的控制点坐标和终点坐标
        canvas.drawPath(mPath, arcPaint);// 画出贝塞尔曲线
        // 画点
        arcPaint.setStyle(Paint.Style.FILL);
        canvas.drawText("画点：", 10, 520, arcPaint);
        canvas.drawPoint(60, 520, arcPaint);// 画一个点
        canvas.drawPoints(new float[] {60, 550, 65, 560, 70, 570}, arcPaint);// 画多个点

        // 画图片，就是贴图

        canvas.drawBitmap(bitmap, 350, 350, arcPaint);
    }
}
