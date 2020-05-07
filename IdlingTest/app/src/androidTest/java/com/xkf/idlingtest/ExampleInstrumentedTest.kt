package com.xkf.idlingtest

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.IdlingResource
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

const val STRING_TO_BE_TYPED = "Espresso"

@RunWith(AndroidJUnit4::class)
@LargeTest
class ExampleInstrumentedTest {

    private var idlingResource: IdlingResource? = null

    @Before
    fun registerIdlingResource() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        activityScenario.onActivity {
            idlingResource = it.getIdlingResource()
            IdlingRegistry.getInstance().register(idlingResource)
        }
    }

    @Test
    fun changeTextSameActivity() {
        Espresso
            .onView(ViewMatchers.withId(R.id.editTextUserInput))
            .perform(
                ViewActions.typeText(STRING_TO_BE_TYPED),
                ViewActions.closeSoftKeyboard()
            )
        Espresso
            .onView(ViewMatchers.withId(R.id.changeTextBt))
            .perform(ViewActions.click())
        Espresso
            .onView(ViewMatchers.withId(R.id.textToBeChanged))
            .check(ViewAssertions.matches(ViewMatchers.withText(STRING_TO_BE_TYPED)))
    }

    @After
    fun unregisterIdlingResource() {
        if (idlingResource != null) {
            IdlingRegistry.getInstance().unregister(idlingResource)
        }
    }
}
