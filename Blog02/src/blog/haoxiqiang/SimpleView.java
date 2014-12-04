package blog.haoxiqiang;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;

public class SimpleView extends View {
    // attr
    private boolean showContent = false;
    private int position = 0;
    // param
    private Paint mCirclePaint;
    private Paint mTextPaint;
    private final int radius = 100;
    private int width = 0;
    private boolean initLayout = false;
    private final Handler mHandler = new Handler();

    public SimpleView(Context context) {
        super(context);
        init(context);
    }

    public SimpleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SimpleView);
        if (typedArray != null) {
            showContent = typedArray.getBoolean(R.styleable.SimpleView_showContent, false);
            position = typedArray.getInt(R.styleable.SimpleView_showPosition, 0);
        }
        typedArray.recycle();
        init(context);
    }

    private void init(Context context) {
        mCirclePaint = new Paint();
        mCirclePaint.setColor(Color.BLUE);
        mCirclePaint.setStrokeWidth(8);
        mTextPaint = new Paint();
        mTextPaint.setColor(Color.RED);
        mTextPaint.setStrokeWidth(5);
        mTextPaint.setTextSize(35.0f);

        this.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {

            @Override
            public void onGlobalLayout() {
                if (!initLayout) {
                    width = SimpleView.this.getWidth();
                    Log.i("onGlobalLayout", "initLayout:" + width);
                    SimpleView.this.invalidate();
                    initLayout = true;
                }
            }
        });
    }

    public void show(final int index) {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                switch (index) {
                    case 0:
                        ViewCompat.setTranslationY(SimpleView.this, 200);
                        show(1);
                        break;
                    case 1:
                        ViewCompat.setTranslationX(SimpleView.this, 200);
                        show(2);
                        break;
                    case 2:
                        ViewCompat.setScaleX(SimpleView.this, 0.5f);
                        ViewCompat.setScaleY(SimpleView.this, 0.5f);
                        show(3);
                        break;
                    case 3:
                        ViewCompat.setScaleX(SimpleView.this, 1.0f);
                        ViewCompat.setScaleY(SimpleView.this, 1.0f);
                        ViewCompat.setTranslationX(SimpleView.this, 0);
                        show(4);
                        break;
                    case 4:
                        ViewCompat.setTranslationY(SimpleView.this, 0);
                        show(5);
                        break;
                    case 5:
                        ViewCompat.setTranslationX(SimpleView.this, 100);
                        ViewCompat.setTranslationY(SimpleView.this, 100);
                        break;
                }

            }
        }, 1000);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int x = position == 0 ? radius : width - 2 * radius;
        x = x < 0 ? 0 : x;
        canvas.drawCircle(x, 100, radius, mCirclePaint);
        if (showContent) {
            canvas.drawText("SimpleView", x, 100, mTextPaint);
        }
    }
}
