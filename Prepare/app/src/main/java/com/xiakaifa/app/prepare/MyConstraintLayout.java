package com.xiakaifa.app.prepare;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;

public class MyConstraintLayout extends ConstraintLayout {
    private ConstraintLayout tagContainer;

    public MyConstraintLayout(Context context) {
        super(context);
    }

    public MyConstraintLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    public MyConstraintLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        tagContainer = findViewById(R.id.tag_container);
        tagContainer.setAlpha(0f);
    }
}
