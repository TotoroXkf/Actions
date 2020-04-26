package com.we.androidtest

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.ComponentNameMatchers.hasShortClassName
import androidx.test.espresso.intent.matcher.IntentMatchers.*
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class ExampleInstrumentedTest {
    @Rule
    @JvmField
    var activityRule: IntentsTestRule<MainActivity> = IntentsTestRule(MainActivity::class.java)

    @Test
    fun useAppContext() {
        Espresso.onView(ViewMatchers.withId(R.id.editText))
            .perform(ViewActions.typeText("This is a test"), ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.button)).perform(ViewActions.click())

        Intents.intended(
            allOf(
                hasComponent(hasShortClassName(".Main2Activity")),
                toPackage("com.we.androidtest"),
                hasExtra(MainActivity.EXTRA_MESSAGE, "This is a test")
            )
        )
    }
}
