package com.example.formylove.statement

import android.graphics.*
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.formylove.R
import com.example.formylove.base.DP_ONE_HALF
import com.example.formylove.base.SCREEN_WIDTH
import com.example.formylove.utils.dp2px
import kotlinx.android.synthetic.main.item_statement_list.view.*


class StatementAdapter(
    private val viewModel: StatementViewModel
) : RecyclerView.Adapter<StatementViewHolder>() {
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatementViewHolder {
        return StatementViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_statement_list,
                parent,
                false
            )
        )
    }
    
    override fun getItemCount(): Int = viewModel.statementList.size
    
    override fun onBindViewHolder(holderStatement: StatementViewHolder, position: Int) {
        val text = viewModel.statementList[position]
        holderStatement.bind(text)
    }
}

class StatementViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val textView = view.text
    
    fun bind(text: String) {
        textView.text = text
    }
}

class StatementDivider : RecyclerView.ItemDecoration() {
    private val paint = Paint()
    
    init {
        paint.isAntiAlias = true
        paint.shader = LinearGradient(
            0f,
            0f,
            SCREEN_WIDTH.toFloat(),
            0f,
            Color.RED,
            Color.BLUE,
            Shader.TileMode.CLAMP
        )
    }
    
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.set(0, 0, 0, DP_ONE_HALF.toInt())
    }
    
    override fun onDraw(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(canvas, parent, state)
        
        val left = parent.paddingLeft + dp2px(50f)
        val right = parent.measuredWidth - parent.paddingRight
        val childSize = parent.childCount
        for (i in 0 until childSize) {
            val child = parent.getChildAt(i)
            val layoutParams =
                child.layoutParams as RecyclerView.LayoutParams
            val top = child.bottom + layoutParams.bottomMargin
            val bottom: Int = top + DP_ONE_HALF.toInt()
            canvas.drawRect(
                left,
                top.toFloat(),
                right.toFloat(),
                bottom.toFloat(),
                paint
            )
            
        }
    }
}