package com.example.class15

import android.annotation.TargetApi
import android.content.ClipData
import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.DragEvent
import android.view.View
import android.view.View.OnLongClickListener
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout

//@TargetApi(Build.VERSION_CODES.N)
//class DragToCollectLayout(
//    context: Context?,
//    attrs: AttributeSet?
//) : ConstraintLayout(context, attrs),
//    View.OnDragListener {
//
//    private lateinit var avatar: ImageView
//    private lateinit var logo: ImageView
//    private lateinit var textContent: TextView
//
//    private val onLongClickListener = OnLongClickListener {
//        val clipData = ClipData.newPlainText("name", it.contentDescription)
//        it.startDragAndDrop(clipData, DragShadowBuilder(it), null, 0)
//        return@OnLongClickListener false
//    }
//
//    override fun onFinishInflate() {
//        super.onFinishInflate()
//
//        avatar = findViewById(R.id.imgAvatar)
//        logo = findViewById(R.id.imgLogo)
//        textContent = findViewById(R.id.textContent)
//
//        avatar.setOnLongClickListener(onLongClickListener)
//        logo.setOnLongClickListener(onLongClickListener)
//
//        textContent.setOnDragListener(this)
//    }
//
//    override fun onDrag(v: View, event: DragEvent): Boolean {
//        when (event.action) {
//            DragEvent.ACTION_DROP -> {
//                if (v == textContent) {
//                    textContent.text = event.clipData.getItemAt(0).text
//                }
//            }
//        }
//        return true
//    }
//}