package com.example.formylove.utils

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.view.View
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders

fun View.getActivity(): Activity? {
    var activityContext: Context? = context
    while (activityContext !is Activity && activityContext is ContextWrapper) {
        activityContext = activityContext.baseContext
    }
    
    return activityContext as? Activity
}

fun <T : ViewModel> View.getViewModel(clazz: Class<T>): T? {
    val activity = getActivity()
    activity ?: return null
    if (activity !is FragmentActivity) {
        return null
    }
    return ViewModelProviders.of(getActivity() as FragmentActivity).get(clazz)
}