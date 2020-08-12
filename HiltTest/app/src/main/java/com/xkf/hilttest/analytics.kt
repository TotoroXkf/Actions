package com.xkf.hilttest

import android.content.Context
import android.util.Log
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

const val TAG = "DaLongMao"

interface AnalyticsService {
    fun analyticsMethods()
}

@ActivityScoped
class AnalyticsAdapter @Inject constructor(
    @ActivityContext val context: Context,
    val service: AnalyticsService
) {
    fun testMethod() {
        Log.e(TAG, context.packageName)
    }
}

@Module
@InstallIn(ActivityComponent::class)
class AnalyticsModule {
    @Provides
    fun provideAnalyticsService(): AnalyticsService {
        return object : AnalyticsService {
            override fun analyticsMethods() {
                Log.e(TAG, "analyticsMethods: ")
            }
        }
    }
}