package com.example.gesture;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView text;
    private View content;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = findViewById(R.id.text);
        content = findViewById(R.id.content);

        GestureDetector.OnGestureListener listener = new GestureDetector.OnGestureListener() {

            @Override
            public boolean onDown(MotionEvent e) {
                text.setText("单次点击");
                return true;
            }

            @Override
            public void onShowPress(MotionEvent e) {

            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                text.setText("抬起");
                return true;
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                text.setText("滚动");
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                text.setText("长按");
            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                text.setText("滑动");
                return true;
            }
        };
        final GestureDetector detector = new GestureDetector(this, listener);
        View.OnTouchListener touchListener = new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return detector.onTouchEvent(event);
            }
        };

        content.setOnTouchListener(touchListener);
    }

}
