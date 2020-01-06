package formylove.random

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RandomViewModel : ViewModel() {
    private val currentThingsList = mutableListOf<String>()
    private val preThingsList = mutableListOf<String>()
    
    val addLiveData = MutableLiveData<String>()
    val deleteLiveData = MutableLiveData<Int>()
    val resetLiveData = MutableLiveData<Boolean>()
    
    fun addNewThing(newThing: String) {
        currentThingsList.add(0, newThing)
        addLiveData.value = newThing
    }
    
    fun resetThingsList() {
        currentThingsList.clear()
        currentThingsList.addAll(preThingsList)
        resetLiveData.value = true
    }
    
    fun computeRandom(): Int {
        return 0;
    }
    
    
    fun deleteThing(index: Int) {
        currentThingsList.removeAt(index)
        deleteLiveData.value = index
    }
}