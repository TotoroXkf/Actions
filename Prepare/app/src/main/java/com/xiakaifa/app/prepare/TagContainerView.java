package com.xiakaifa.app.prepare;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

public class TagContainerView extends ConstraintLayout {
    public TagContainerView(Context context) {
        super(context);
    }

    public TagContainerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TagContainerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        LayoutInflater.from(getContext()).inflate(R.layout.tag_container,this,true);

    }
}
