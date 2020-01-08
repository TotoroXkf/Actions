package formylove.random

import com.example.formylove.R
import formylove.base.BaseActivity

class ThingInputActivity : BaseActivity() {
    
    override fun initViews() {
        setStatusBarWhite()
    }
    
    override fun getLayoutId(): Int = R.layout.activity_thing_input
    
    override fun setEnterAnimation() {
        overridePendingTransition(0, 0);
    }
    
    override fun setExitAnimation() {
        overridePendingTransition(0, 0);
    }
}
