package com.example.cameraviewtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.otaliastudios.cameraview.CameraView;

public class MainActivity extends AppCompatActivity {
    private CameraView camera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        camera = findViewById(R.id.camera);
    }

    @Override
    protected void onResume() {
        super.onResume();
        camera.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        camera.stop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        camera.destroy();
    }
}
