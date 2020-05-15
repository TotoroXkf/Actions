package com.xkf.ppjoke.base

import androidx.navigation.ActivityNavigator
import androidx.navigation.NavController
import androidx.navigation.fragment.FragmentNavigator

object NavGraphBuilder {
    fun build(navController: NavController) {
        val provider = navController.navigatorProvider
        val fragmentNavigator = provider.getNavigator(FragmentNavigator::class.java)
        val activityNavigator = provider.getNavigator(ActivityNavigator::class.java)
        val destConfig = AppConfig.getDestConfig()
        for (destination in destConfig.values) {
            if (destination.isFragment) {
                val fragmentDestination = fragmentNavigator.createDestination()
                fragmentDestination.id = destination.id
            }
        }
    }
}