package blog.haoxiqiang;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Region;
import android.util.AttributeSet;
import android.view.View;


// 待测试
public class CanvasView5 extends View {

    private Bitmap mBitmap;
    private int limitLength = 0;
    private int width;
    private int heigth;
    private static final int CLIP_HEIGHT = 30;

    private boolean status = HIDE;// 显示还是隐藏的状态，最开始为HIDE
    private static final boolean SHOW = true;// 显示图片
    private static final boolean HIDE = false;// 隐藏图片
    private Region region;
    private Rect mRect;
    private Paint mPaint;

    public CanvasView5(Context context) {
        super(context);
        init();
    }


    public CanvasView5(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @SuppressLint("NewApi")
    private void init() {
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.palette01);
        limitLength = width = mBitmap.getWidth();
        heigth = mBitmap.getHeight();
        region = new Region();
        mRect = new Rect();
        mPaint = new Paint();
        mPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        this.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
    }

    @Override
    protected void onDraw(Canvas canvas) {


        int i = 0;
        while (i * CLIP_HEIGHT <= heigth) {// 计算clip的区域
            if (i % 2 == 0) {
                mRect.set(0, i * CLIP_HEIGHT, limitLength, (i + 1) * CLIP_HEIGHT);
                region.union(mRect);
            } else {
                mRect.set(width - limitLength, i * CLIP_HEIGHT, width, (i + 1) * CLIP_HEIGHT);
                region.union(mRect);
            }
            i++;
        }

        canvas.clipRegion(region);
        canvas.drawBitmap(mBitmap, 0, 0, mPaint);
        if (status == HIDE) {// 如果此时是隐藏
            limitLength -= 5;
            if (limitLength <= 0)
                status = SHOW;
        } else {// 如果此时是显示
            limitLength += 5;
            if (limitLength >= width)
                status = HIDE;
        }
        postInvalidateDelayed(10);
    }
}
