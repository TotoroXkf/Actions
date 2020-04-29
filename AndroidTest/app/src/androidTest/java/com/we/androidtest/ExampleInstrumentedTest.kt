package com.we.androidtest

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class ExampleInstrumentedTest {
    @Rule
    @JvmField
    var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Test
    fun useAppContext() {
        onView(ViewMatchers.withId(R.id.recyclerView)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                40
            )
        )
        onView(ViewMatchers.withText("400")).check(matches(isDisplayed()))
    }
}
