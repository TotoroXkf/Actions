package formylove.main

import com.example.formylove.R
import formylove.random.RandomActivity
import formylove.statement.StatementActivity

object MainConfigure {
    val itemNames = arrayOf(
        "恋爱语句",
        "随机事件"
    )
    
    val itemIcons = arrayOf(
        R.drawable.icon_statement,
        R.drawable.icon_random
    )
    
    val router = arrayOf(
        StatementActivity::class.java,
        RandomActivity::class.java
    )
}