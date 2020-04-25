package com.we.formylove

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import com.we.answerbook.AnswerBookFragment
import com.we.common.component.CommonHandler
import com.we.formylove.fragment.MainFragmentDirections
import com.we.lovestatement.fragment.LoveStatementFragment
import com.we.splash.SplashFragment

class MainActivity : AppCompatActivity(), CommonHandler {
    private val routeMap = HashMap<String, NavDirections>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        registerRoute()
        open(SplashFragment.NAME)
    }

    override fun fullScreen() {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
    }

    override fun cancelFullScreen() {
        window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }

    override fun hideKeyboard() {
        val v = findViewById<View>(android.R.id.content)
        val im = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        if (v != null && im != null) {
            im.hideSoftInputFromWindow(
                v.windowToken,
                InputMethodManager.HIDE_NOT_ALWAYS
            )
        }
    }

    override fun open(route: String) {
        if (routeMap.containsKey(route)) {
            val action = routeMap[route]
            action?.let {
                findNavController(R.id.nav_host_fragment).navigate(action)
            }
        }
    }

    override fun showKeyboard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        imm?.toggleSoftInput(0, InputMethodManager.SHOW_FORCED)
    }

    private fun registerRoute() {
        // splash
        routeMap[SplashFragment.NAME] = MainFragmentDirections.actionMainFragmentToSplashFragment()
        // 恋爱语句
        routeMap[LoveStatementFragment.NAME] =
            MainFragmentDirections.actionMainFragmentToLoveStatementFragment()
        // 解答之书
        routeMap[AnswerBookFragment.NAME] =
            MainFragmentDirections.actionMainFragmentToAnswerBookFragment()
    }
}
