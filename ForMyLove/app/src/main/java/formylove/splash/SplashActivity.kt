package formylove.splash

import android.content.Intent
import formylove.base.BaseActivity
import com.example.formylove.R
import formylove.main.MainActivity
import kotlinx.android.synthetic.main.activity_splash.*


class SplashActivity : BaseActivity() {
    override fun getLayoutId(): Int = R.layout.activity_splash
    
    override fun initViews() {
        fullScreen()
        
        splashView.animationFinishListener = {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
    
    override fun needDoubleBackExit(): Boolean {
        return true
    }
}