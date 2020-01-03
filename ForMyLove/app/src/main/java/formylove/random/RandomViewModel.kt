package formylove.random

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay
import kotlin.random.Random

class RandomViewModel : ViewModel() {
    val currentThingsList = mutableListOf<String>()
    private val lastThingsList = mutableListOf<String>()
    
    var isFirstLoadList = true
    
    val addLiveData = MutableLiveData<String>()
    val deleteLiveData = MutableLiveData<Int>()
    val resetLiveData = MutableLiveData<Boolean>()
    
    fun addNewThing(newThing: String) {
        currentThingsList.add(0, newThing)
        addLiveData.value = newThing
    }
    
    fun resetThingsList() {
        currentThingsList.clear()
        currentThingsList.addAll(lastThingsList)
        resetLiveData.value = true
    }
    
    suspend fun computeRandom() {
        lastThingsList.clear()
        lastThingsList.addAll(currentThingsList)
        while (currentThingsList.size > 1) {
            val index = getNextIndex(currentThingsList.size)
            deleteThing(index)
            delay(700)
        }
    }
    
    private fun getNextIndex(length: Int): Int {
        val random = Random(System.currentTimeMillis())
        return random.nextInt(1000) % length
    }
    
    fun deleteThing(index: Int) {
        currentThingsList.removeAt(index)
        deleteLiveData.value = index
    }
}