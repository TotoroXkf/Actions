package com.xkf.libnetwork


abstract class JsonCallback<T> {
    fun onSuccess(response: ApiResponse<T>) {
    
    }
    
    fun onErrpr(response: ApiResponse<T>) {
    
    }
    
    fun onCacheSuccess(response: ApiResponse<T>) {
        
    }
}