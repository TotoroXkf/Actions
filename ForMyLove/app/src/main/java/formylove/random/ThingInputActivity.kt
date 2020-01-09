package formylove.random

import android.text.Editable
import android.text.TextWatcher
import com.example.formylove.R
import formylove.base.AddThingEvent
import formylove.base.BaseActivity
import kotlinx.android.synthetic.main.activity_thing_input.*
import org.greenrobot.eventbus.EventBus

class ThingInputActivity : BaseActivity() {

    override fun initViews() {
        setStatusBarWhite()

        inputText.requestFocus()
        inputText.postDelayed({
            showKeyboard()
        }, 200)
        inputText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {

            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                val str = s.toString()
                if (str.isEmpty()) {
                    btnAdd.isEnabled = false
                }
                btnAdd.isEnabled = true
            }
        })

        btnAdd.setOnClickListener {
            val thing = inputText.editableText.toString()
            if (thing.isEmpty()) {
                return@setOnClickListener
            }
            EventBus.getDefault().post(AddThingEvent(thing))
            inputText.setText("")
        }

        rootLayout.setOnClickListener {
            finish()
        }
    }

    override fun getLayoutId(): Int = R.layout.activity_thing_input

    override fun setEnterAnimation() {
        overridePendingTransition(0, 0)
    }

    override fun setExitAnimation() {
        overridePendingTransition(0, 0)
    }
}
