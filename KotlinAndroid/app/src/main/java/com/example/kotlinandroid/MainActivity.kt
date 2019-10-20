package com.example.kotlinandroid

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        funcTest {
            return@funcTest it.toString()
        }
    }


    inline fun funcTest(lambda: (Int) -> String) {
        val result = lambda.invoke(10)
        println(result)
    }
}
