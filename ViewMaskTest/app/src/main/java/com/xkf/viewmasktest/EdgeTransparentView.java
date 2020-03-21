package com.xkf.viewmasktest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

public class EdgeTransparentView extends FrameLayout {
    //渐变颜色
    private int[] mGradientColors = {0xffffffff, 0x00000000};
    //渐变位置
    private float[] mGradientPosition = new float[]{0, 1};

    private Paint mPaint;
    private float drawSize = 20f;

    private int mWidth;
    private int mHeight;

    private boolean enableTransparent = true;

    public EdgeTransparentView(Context context) {
        this(context, null);
    }

    public EdgeTransparentView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EdgeTransparentView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        initShader();
        mWidth = getWidth();
        mHeight = getHeight();
    }

    public void setEnableTransparent(boolean enableTransparent) {
        this.enableTransparent = enableTransparent;
        invalidate();
    }

    private void initShader() {
        mPaint.setShader(new LinearGradient(0, 0, 0, drawSize, mGradientColors, mGradientPosition, Shader.TileMode.CLAMP));
    }

    @Override
    protected boolean drawChild(Canvas canvas, View child, long drawingTime) {
        if (!enableTransparent) {
            return super.drawChild(canvas, child, drawingTime);
        }

        int layerSave = canvas.saveLayer(0, 0, getWidth(), getHeight(), null, Canvas.ALL_SAVE_FLAG);
        boolean drawChild = super.drawChild(canvas, child, drawingTime);

        canvas.drawRect(0, 0, mWidth, drawSize, mPaint);

        int save = canvas.save();
        canvas.rotate(180, mWidth / 2, mHeight / 2);
        canvas.drawRect(0, 0, mWidth, drawSize, mPaint);
        canvas.restoreToCount(save);

        int offset = (mHeight - mWidth) / 2;
        save = canvas.save();
        canvas.rotate(90, mWidth / 2, mHeight / 2);
        canvas.translate(0, offset);
        canvas.drawRect(0 - offset, 0, mWidth + offset, drawSize, mPaint);
        canvas.restoreToCount(save);

        save = canvas.save();
        canvas.rotate(270, mWidth / 2, mHeight / 2);
        canvas.translate(0, offset);
        canvas.drawRect(0 - offset, 0, mWidth + offset, drawSize, mPaint);
        canvas.restoreToCount(save);

        canvas.restoreToCount(layerSave);
        return drawChild;
    }
}
