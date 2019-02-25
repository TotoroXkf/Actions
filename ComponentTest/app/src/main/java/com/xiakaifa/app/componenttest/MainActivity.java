package com.xiakaifa.app.componenttest;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
}

class MyAdapter extends BaseAdapter<MyViewHolder>{

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}

class MyViewHolder extends BaseViewHolder {

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    @Override
    void bindDate() {

    }
}
