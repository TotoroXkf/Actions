package com.xiakaifa.app.prepare;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MyConstraintLayout extends ConstraintLayout {
    private ConstraintLayout tagContainer;
    private LinearLayout linearLayout;

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
        tagContainer.setVisibility(View.GONE);

        linearLayout = findViewById(R.id.linearLayout);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        Button button1 = new Button(getContext());
        Button button2 = new Button(getContext());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT);
        params.weight = 1;
        linearLayout.addView(button1,params);
        linearLayout.addView(button2,params);
    }

    public void show() {
        tagContainer.setVisibility(View.VISIBLE);
    }
}
