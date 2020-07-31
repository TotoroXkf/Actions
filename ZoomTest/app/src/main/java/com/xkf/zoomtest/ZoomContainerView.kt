package com.xkf.zoomtest

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import android.util.AttributeSet
import android.util.TypedValue
import android.view.MotionEvent
import android.view.View
import android.widget.FrameLayout
import android.widget.OverScroller
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.customview.widget.ViewDragHelper
import androidx.recyclerview.widget.RecyclerView

@Suppress("ConvertTwoComparisonsToRangeCheck")
class ZoomContainerView : ConstraintLayout {
    // 拖动view的主要处理类
    private val viewDragHelper: ViewDragHelper

    // 松手之后的惯性滑动处理类
    private val overScroller: OverScroller
    private var flingRunnable = FlingRunnable()

    private lateinit var zoomView: FrameLayout
    private lateinit var titleView: FrameLayout
    private lateinit var recyclerView: RecyclerView

    private var lastY = 0f

    // view可以滑动的范围
    private var minHeight = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        200f,
        Resources.getSystem().displayMetrics
    )
    private var maxHeight = 0f

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    init {
        viewDragHelper = ViewDragHelper.create(this, 1f, DragCallback())
        overScroller = OverScroller(context)
    }

    override fun onFinishInflate() {
        super.onFinishInflate()

        zoomView = findViewById(R.id.zoomView)
        titleView = findViewById(R.id.titleView)
        recyclerView = findViewById(R.id.recyclerView)

        post {
            maxHeight = zoomView.height.toFloat()

            // 动态设置一下recyclerView的高度，使得和屏幕的高度可以适配
            val screenHeight = Resources.getSystem().displayMetrics.heightPixels
            val layoutParams = recyclerView.layoutParams
            layoutParams.height = (screenHeight - titleView.height - minHeight).toInt()
            recyclerView.layoutParams = layoutParams
        }
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        // 在这里判断手势的分发逻辑
        // 这里主要判断滑动手势是交给缩放view还是recyclerview
        var dy = 0f
        when (ev.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                lastY = ev.y
            }
            MotionEvent.ACTION_MOVE -> {
                // 记录dy,判断出是当前是从哪里往哪里滑动
                dy = ev.y - lastY
                lastY = ev.y
            }
        }
        if (zoomView.height == minHeight.toInt()) {
            // 判断recyclerview能不能向上或者向下滑动
            val canScrollDown = recyclerView.canScrollVertically(1)
            val canScrollUp = recyclerView.canScrollVertically(-1)
            // 下面两个条件让recyclerview接管事件
            // recyclerView可以向上滑动，表示recyclerview在中间或者底部的位置，那此时的手势全部交给RecyclerView就好了
            // recyclerView可以向下滑动，表示在顶部或者中间，那就要判断一下了。如果是在中间，则应该交个recyclerview来处理。
            // 如果是在顶部。那么还要看手指的滑动方向。手指从下往上滑，此时要交给recyclerView，反之交给ViewDragHelper
            // 这里要注意是小于0不是等于0.因为手指按下时dy是0.此时走不到这里，会被ViewDragHelper接受。这是必要的。
            // 因为如果down不给ViewDragHelper的话，后续再交个他滑动事件都是无效的。反之，中间把事件交给RecyclerView确是可行的
            if (canScrollUp || (canScrollDown && dy < 0)) {
                return super.onInterceptTouchEvent(ev)
            }
        }
        // 大多数事件还是交给viewDragHelper比较好
        return viewDragHelper.shouldInterceptTouchEvent(ev)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        // 将view本身的手势交给DragHelper来处理
        viewDragHelper.processTouchEvent(event)
        return true
    }

    /**
     * 手指拖动的主要处理类
     */
    inner class DragCallback : ViewDragHelper.Callback() {
        /**
         * 能不能拖动view。这里返回true表示总是可以拖动。
         * 如果不能拖动，其实在更之前的view的手势分发的时候就拦截了
         */
        override fun tryCaptureView(child: View, pointerId: Int): Boolean {
            return true
        }

        /**
         * 手指滑动多少算是拖动
         */
        override fun getViewVerticalDragRange(child: View): Int {
            return viewDragHelper.touchSlop
        }

        /**
         * 实际的拖动过程
         * top表示的是按照手指的所在位置，这个view的top值。这个值不是真的值，是按照手指位置和view高度算出来的，提供参考的。实际的值还是需要自己计算
         * dy表示当前这次滑动的距离值。是一小段一小段的
         * 返回值表示这次滑动view真正的滑动值是多少。如果需要让手指在哪，view在哪，直接返回这个top值就可以。返回100表示移动100个像素点。返回0表示在原地不滑动
         */
        override fun clampViewPositionVertical(child: View, top: Int, dy: Int): Int {
            // 按照手指滑动的情况去缩放view本身的高度
            var viewHeight = zoomView.height + dy
            viewHeight = minHeight.toInt().coerceAtLeast(viewHeight)
            viewHeight = maxHeight.toInt().coerceAtMost(viewHeight)

            val layoutParams = zoomView.layoutParams
            layoutParams.height = viewHeight
            zoomView.layoutParams = layoutParams

            // 其实我们并不需要滑动view，所以返回0
            return 0
        }

        override fun onViewReleased(releasedChild: View, xvel: Float, yvel: Float) {
            // 松手的时候将惯性滑动交给对应的处理者
            if (zoomView.height > minHeight && zoomView.height < maxHeight) {
                removeCallbacks(flingRunnable)
                flingRunnable = FlingRunnable()
                flingRunnable.fling(yvel)
            }
        }
    }

    /**
     * 松手之后的惯性滑动处理类
     */
    inner class FlingRunnable : Runnable {
        /**
         * 拖动处理结束时会拿到竖直方向上的速度，这里传递进来
         */
        fun fling(yVel: Float) {
            // 这里注意了。overScroller不是真的让view去滚动，而是计算出滚动的位置来，开发者拿到位置去自己设置view的属性
            // 水平方向上不需要滚动，所以相关于x的值都传0
            // StartY这个参数指的是从竖直方向上哪个地方开始滚动。
            // 竖直的速度使用传进来的
            // 滚动的距离范围相对来说比较随意，简单一点就是传从0到最大。表示可以滚动的范围是最大的
            overScroller.fling(0, zoomView.bottom, 0, yVel.toInt(), 0, 0, 0, Int.MAX_VALUE)
            run()
        }

        override fun run() {
            // 如果当前仍然在滚动的话
            if (overScroller.computeScrollOffset() && zoomView.height > minHeight && zoomView.height < maxHeight) {
                // overScroller计算出滚动的实际位置
                var value = overScroller.currY
                // 控制最大最小值
                value = minHeight.toInt().coerceAtLeast(value)
                value = maxHeight.toInt().coerceAtMost(value)

                // 将滚动的位置设置给view的高度，实现缩放的效果
                val layoutParams = zoomView.layoutParams
                layoutParams.height = value
                zoomView.layoutParams = layoutParams

                // 在下一帧执行相同的逻辑
                postOnAnimation(this)
            } else {
                // 不在滚动则是移除自身
                removeCallbacks(this)
            }
        }
    }
}