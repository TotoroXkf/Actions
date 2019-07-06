package com.example.myflowlayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class MyFlowLayout extends ViewGroup {
    public MyFlowLayout(Context context) {
        super(context);
    }

    public MyFlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyFlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureChildren(widthMeasureSpec, heightMeasureSpec);

        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);
        int resultHeightSize = 0;

        int currentWidthLen = 0;
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            int childWidth = child.getMeasuredWidth();
            int childHeight = child.getMeasuredHeight();
            if (i == 0) {
                resultHeightSize += childHeight;
            }
            if (currentWidthLen + childWidth <= widthSpecSize) {
                currentWidthLen += childWidth;
            } else {
                currentWidthLen = childWidth;
                resultHeightSize += childHeight;
            }
        }

        if (heightSpecMode == MeasureSpec.AT_MOST) {
            resultHeightSize = Math.min(heightSpecSize, resultHeightSize);
        }

        setMeasuredDimension(widthSpecSize, resultHeightSize);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int layoutWidth = getMeasuredWidth();
        int layoutHeight = getMeasuredHeight();
        int currentHeight = 0;
        int currentWidth = 0;
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            int childWidth = child.getMeasuredWidth();
            int childHeight = child.getMeasuredHeight();
            if (currentWidth + childWidth <= layoutWidth) {
                child.layout(currentWidth, currentHeight, currentWidth + childWidth, currentHeight + childHeight);
                currentWidth += childWidth;
            } else {
                currentWidth = 0;
                currentHeight += childHeight;
                child.layout(currentWidth, currentHeight, currentWidth + childWidth, currentHeight + childHeight);
                currentWidth += childWidth;
            }
        }
    }
}
