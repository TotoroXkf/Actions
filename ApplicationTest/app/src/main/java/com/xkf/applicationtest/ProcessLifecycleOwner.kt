package com.xkf.applicationtest

import android.app.Activity
import android.app.Application
import android.app.Application.ActivityLifecycleCallbacks
import android.content.Context
import android.os.Bundle
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry

object ProcessLifecycleOwner : LifecycleOwner {
    private var startedCounter = 0
    private var resumedCounter = 0
    private var pauseSent = true
    private var stopSent = true

    private val registry = LifecycleRegistry(this)

    fun init(context: Context) {
        attach(context)
    }

    private fun activityStarted() {
        startedCounter++
        if (startedCounter == 1 && stopSent) {
            registry.handleLifecycleEvent(Lifecycle.Event.ON_START)
            stopSent = false
        }
    }

    private fun activityResumed() {
        resumedCounter++
        if (resumedCounter == 1) {
            if (pauseSent) {
                registry.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
                pauseSent = false
            }
        }
    }

    private fun activityPaused() {
        resumedCounter--
        if (resumedCounter == 0) {
            dispatchPauseIfNeeded()
            dispatchStopIfNeeded()
        }
    }

    private fun activityStopped() {
        startedCounter--
        dispatchStopIfNeeded()
    }

    private fun dispatchPauseIfNeeded() {
        if (resumedCounter == 0) {
            pauseSent = true
            registry.handleLifecycleEvent(Lifecycle.Event.ON_PAUSE)
        }
    }

    private fun dispatchStopIfNeeded() {
        if (startedCounter == 0 && pauseSent) {
            registry.handleLifecycleEvent(Lifecycle.Event.ON_STOP)
            stopSent = true
        }
    }

    private fun attach(context: Context) {
        registry.handleLifecycleEvent(Lifecycle.Event.ON_CREATE)
        val app = context.applicationContext as Application
        app.registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
            override fun onActivityCreated(
                activity: Activity,
                savedInstanceState: Bundle?
            ) {
            }

            override fun onActivityStarted(activity: Activity) {
                activityStarted()
            }

            override fun onActivityResumed(activity: Activity) {
                activityResumed()
            }

            override fun onActivitySaveInstanceState(
                activity: Activity,
                outState: Bundle?
            ) {
            }

            override fun onActivityDestroyed(activity: Activity) {}

            override fun onActivityPaused(activity: Activity) {
                activityPaused()
            }

            override fun onActivityStopped(activity: Activity) {
                activityStopped()
            }
        })
    }

    override fun getLifecycle(): Lifecycle {
        return registry
    }
}
