package com.example.transitiontest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Slide;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

//        getWindow().setExitTransition(new Slide());
//        getWindow().setEnterTransition(new Slide());
    }
}
