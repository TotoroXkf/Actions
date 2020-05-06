package com.xkf.recyclerviewtest

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

private const val ITEM_BELOW_THE_FOLD = 40

@RunWith(AndroidJUnit4::class)
@LargeTest
class ExampleInstrumentedTest {

    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun scrollToItemBelowFoldCheckItsText() {
        Espresso.onView(ViewMatchers.withId(R.id.recyclerView))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<CustomAdapter.ViewHolder>(
                    ITEM_BELOW_THE_FOLD,
                    ViewActions.click()
                )
            )
        val itemElementText = "Element$ITEM_BELOW_THE_FOLD"
        Espresso.onView(ViewMatchers.withText(itemElementText))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun itemInMiddleOfListHasSpecialText() {
        Espresso.onView(ViewMatchers.withId(R.id.recyclerView))
            .perform(RecyclerViewActions.scrollToHolder(isInTheMiddle()))
        val middleElementText = "222"
        Espresso.onView(ViewMatchers.withText(middleElementText))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}

private fun isInTheMiddle(): Matcher<CustomAdapter.ViewHolder> {
    return object : TypeSafeMatcher<CustomAdapter.ViewHolder>() {
        override fun describeTo(description: Description) {
            description.appendText("item in the middle")
        }

        override fun matchesSafely(item: CustomAdapter.ViewHolder): Boolean {
            return item.isInTheMiddle
        }
    }
}

