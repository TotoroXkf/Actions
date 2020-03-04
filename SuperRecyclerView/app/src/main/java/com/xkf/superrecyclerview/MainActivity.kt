package com.xkf.superrecyclerview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // view.addRelation(Model.class,ViewCreator.getView,ControllerCreator.getController())
        // view.addModel()
        // .....
    }
}
