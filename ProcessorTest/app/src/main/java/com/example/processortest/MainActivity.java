package com.example.processortest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.annotation.BindView;
import com.example.api.InjectHelper;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.text_hello)
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InjectHelper.inject(this);
        textView.setText("xkf");
    }
}
