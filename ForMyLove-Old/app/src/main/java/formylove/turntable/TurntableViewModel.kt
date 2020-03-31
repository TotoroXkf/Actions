package formylove.turntable

import android.graphics.Color
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import okhttp3.internal.toHexString

class TurntableViewModel : ViewModel() {
    val thingsList = mutableListOf<String>()
    val colorList = mutableListOf<Int>()
    val addLiveData = MutableLiveData<String>()
    val deleteLiveData = MutableLiveData<Int>()

    fun addNewThing(newThing: String) {
        thingsList.add(0, newThing)
        colorList.add(getRandomColor())

        addLiveData.value = newThing
    }

    fun compute(thingsNum: Int): Float {
        var result = 0f
        for (i in 0 until thingsNum) {
            val randomValue = Math.random().toFloat() * 500f
            result += randomValue
        }
        return result
    }

    fun deleteThing(index: Int) {
        thingsList.removeAt(index)
        colorList.removeAt(index)

        deleteLiveData.value = index
    }

    private fun getRandomColor(): Int {
        val red = (Math.random() * 255).toFloat()
        val blue = (Math.random() * 255).toFloat()
        val green = (Math.random() * 255).toFloat()
        var colorString = Color.pack(red, green, blue).toHexString()
        colorString = colorString.substring(0, 8)
        colorString = "#$colorString"
        return Color.parseColor(colorString)
    }
}