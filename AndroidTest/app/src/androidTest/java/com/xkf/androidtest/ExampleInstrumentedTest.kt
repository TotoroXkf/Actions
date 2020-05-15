package com.xkf.androidtest

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.IdlingResource
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class ExampleInstrumentedTest {
    private var idlingResource: IdlingResource? = null
    private lateinit var activityScenarioRule: ActivityScenario<MainActivity>
    lateinit var viewModel: MainViewModel

    @Before
    fun pre() {
        activityScenarioRule = ActivityScenario.launch(MainActivity::class.java)
        activityScenarioRule.onActivity {
            idlingResource = it.getIdlingResource()
            viewModel = it.mainViewModel
            IdlingRegistry.getInstance().register(idlingResource)
        }
    }

    @Test
    fun test() {
        Espresso.onView(ViewMatchers.withId(R.id.recyclerView))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(0))
        val list = viewModel.liveData.value!!
        for (index in list.indices) {
            Espresso.onView(ViewMatchers.withId(R.id.recyclerView))
                .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(index))
            Espresso.onView(ViewMatchers.withText(list[index]))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        }
    }

    @After
    fun last() {
        IdlingRegistry.getInstance().unregister(idlingResource)
    }
}
