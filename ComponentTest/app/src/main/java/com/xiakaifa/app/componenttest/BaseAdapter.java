package com.xiakaifa.app.componenttest;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseAdapter<VH extends BaseViewHolder> extends RecyclerView.Adapter<VH> {
    @Override
    public void onBindViewHolder(@NonNull VH vh, int i) {
        vh.bindDate();
    }
}

abstract class BaseViewHolder extends RecyclerView.ViewHolder {

    public BaseViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    abstract void bindDate();
}

