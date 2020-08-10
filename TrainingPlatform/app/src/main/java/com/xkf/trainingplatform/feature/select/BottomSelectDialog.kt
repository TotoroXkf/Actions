package com.xkf.trainingplatform.feature.select

import android.app.Dialog
import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.TypedValue
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.xkf.trainingplatform.R
import com.xkf.trainingplatform.databinding.DialogSelectDoctorBinding

class BottomSelectDialog(context: Context, val list: List<String>) : Dialog(context) {
    private lateinit var viewBinding: DialogSelectDoctorBinding

    var onClickListener: ((String) -> Unit)? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = DialogSelectDoctorBinding.inflate(LayoutInflater.from(context))
        setContentView(viewBinding.root)

        window?.apply {
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            setGravity(Gravity.BOTTOM)
            setLayout(
                WindowManager.LayoutParams.MATCH_PARENT,
                TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    350f,
                    Resources.getSystem().displayMetrics
                ).toInt()
            )
            setWindowAnimations(R.style.Animation_Design_BottomSheetDialog)
        }

        viewBinding.recyclerView.adapter = DialogAdapter()
    }

    inner class DialogAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            val textView = TextView(parent.context)
            textView.setTextColor(Color.parseColor("#111111"))
            textView.setPadding(
                TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    16f,
                    Resources.getSystem().displayMetrics
                ).toInt(),
                0,
                0,
                TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    20f,
                    Resources.getSystem().displayMetrics
                ).toInt()
            )
            return object : RecyclerView.ViewHolder(textView) {}
        }

        override fun getItemCount(): Int = list.size

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            (holder.itemView as? TextView)?.text = list[position]
            holder.itemView.setOnClickListener {
                dismiss()
                onClickListener?.invoke(list[position])
            }
        }
    }
}