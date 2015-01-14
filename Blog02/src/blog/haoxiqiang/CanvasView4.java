package blog.haoxiqiang;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Region;
import android.util.AttributeSet;
import android.view.View;

public class CanvasView4 extends View {

    private Paint mPaint;
    private Path mPath;
    private final int factor = 2;

    public CanvasView4(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        setFocusable(true);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(6);
        mPaint.setTextSize(16);
        mPaint.setTextAlign(Paint.Align.RIGHT);

        mPath = new Path();
    }



    public CanvasView4(Context context) {
        super(context);
        init();
    }

    private void drawScene(Canvas canvas) {
        canvas.clipRect(0, 0, 100 * factor, 100 * factor);

        canvas.drawColor(Color.WHITE);

        mPaint.setColor(Color.RED);
        canvas.drawLine(0, 0, 100 * factor, 100 * factor, mPaint);

        mPaint.setColor(Color.GREEN);
        canvas.drawCircle(30 * factor, 70 * factor, 30 * factor, mPaint);

        mPaint.setColor(Color.BLUE);
        canvas.drawText("Clipping", 100 * factor, 30 * factor, mPaint);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        canvas.drawColor(Color.GRAY);

        canvas.save();
        canvas.translate(10 * factor, 10 * factor);
        drawScene(canvas);
        canvas.restore();

        canvas.save();
        canvas.translate(160 * factor, 10 * factor);
        // 切去一圈
        canvas.clipRect(10 * factor, 10 * factor, 90 * factor, 90 * factor);
        // 中间切去一圈,保留region1 与 region2不同的区域
        canvas.clipRect(30 * factor, 30 * factor, 70 * factor, 70 * factor, Region.Op.DIFFERENCE);
        drawScene(canvas);
        canvas.restore();

        canvas.save();
        canvas.translate(10 * factor, 160 * factor);
        mPath.reset();
        // canvas.clipPath(mPath); // makes the clip empty
        mPath.addCircle(50 * factor, 50 * factor, 50 * factor, Path.Direction.CCW);
        canvas.clipPath(mPath, Region.Op.REPLACE);
        drawScene(canvas);
        canvas.restore();

        canvas.save();
        canvas.translate(160 * factor, 160 * factor);
        canvas.clipRect(0, 0, 60 * factor, 60 * factor);
        canvas.clipRect(40 * factor, 40 * factor, 100 * factor, 100 * factor, Region.Op.UNION);
        drawScene(canvas);
        canvas.restore();

        canvas.save();
        canvas.translate(10 * factor, 310 * factor);
        canvas.clipRect(0, 0, 60 * factor, 60 * factor);
        canvas.clipRect(40 * factor, 40 * factor, 100 * factor, 100 * factor, Region.Op.XOR);
        drawScene(canvas);
        canvas.restore();

        canvas.save();
        canvas.translate(160 * factor, 310 * factor);
        canvas.clipRect(0, 0, 60 * factor, 60 * factor);
        canvas.clipRect(40 * factor, 40 * factor, 100 * factor, 100 * factor,
                Region.Op.REVERSE_DIFFERENCE);
        drawScene(canvas);
        canvas.restore();
    }
}
