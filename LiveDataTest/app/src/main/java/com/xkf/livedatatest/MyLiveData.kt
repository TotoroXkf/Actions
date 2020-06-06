package com.xkf.livedatatest

import android.util.Log
import androidx.lifecycle.MutableLiveData

const val TAG = "xkf"

class MyLiveData<T> : MutableLiveData<T>() {
    
    
    override fun onActive() {
        Log.e(TAG, "onActive: ")
        
    }
    
    override fun onInactive() {
        Log.e(TAG, "onInactive: ")
    }
}