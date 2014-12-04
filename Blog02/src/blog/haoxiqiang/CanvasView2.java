package blog.haoxiqiang;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class CanvasView2 extends View {

    private Paint arcPaint;
    private RectF rectF;

    public CanvasView2(Context context) {
        super(context);
    }

    public CanvasView2(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        arcPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        arcPaint.setColor(Color.BLUE);
        rectF = new RectF(260, 70, 290, 115);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(100, 100);
        canvas.drawColor(Color.WHITE);

        rectF.set(-100, -100, 0, 0);
        canvas.drawRect(rectF, arcPaint);// 缩放了
        canvas.scale(0.5f, 0.5f);
        rectF.set(0, 0, 100, 100);
        canvas.drawRect(0, 0, 100, 100, arcPaint);

        canvas.translate(200, 0);
        canvas.rotate(30);
        canvas.drawRect(rectF, arcPaint);// 旋转了

        canvas.translate(200, 0);
        canvas.skew(0.5f, 0.5f);// 扭曲了
        canvas.drawRect(rectF, arcPaint);

    }
}
