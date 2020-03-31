package formylove.utils

import android.graphics.Rect
import android.view.ViewTreeObserver
import android.view.Window
import formylove.base.KeyBoardEvent
import org.greenrobot.eventbus.EventBus

object KeyboardHelper {
    private val listenerMap = mutableMapOf<Window, ViewTreeObserver.OnGlobalLayoutListener>()
    
    fun registerKeyBoardListener(window: Window) {
        val listener = ViewTreeObserver.OnGlobalLayoutListener {
            //判断窗口可见区域大小
            val r = Rect()
            window.decorView.getWindowVisibleDisplayFrame(r)
            //如果屏幕高度和Window可见区域高度差值大于整个屏幕高度的1/3，则表示软键盘显示中，否则软键盘为隐藏状态。
            val screenHeight = getScreenHeight()
            val heightDifference = getScreenHeight() - (r.bottom - r.top)
            val isKeyboardShowing = heightDifference > screenHeight / 3
            
            val event = KeyBoardEvent(
                show = isKeyboardShowing,
                keyBoardHeight = getScreenHeight() - (r.bottom - r.top),
                viewHeight = r.bottom - r.top
            )
            EventBus.getDefault().post(event)
        }
        window.decorView.viewTreeObserver.addOnGlobalLayoutListener(listener)
        listenerMap[window] = listener
    }
    
    fun unRegisterKeyBoardListener(window: Window) {
        if (window in listenerMap) {
            window.decorView.viewTreeObserver.removeOnGlobalLayoutListener(listenerMap[window])
        }
    }
}