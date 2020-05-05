package com.we.androidtest

import android.app.Activity
import android.app.Instrumentation
import android.content.Intent
import android.net.Uri
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.ComponentNameMatchers
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.GrantPermissionRule
import org.hamcrest.Matchers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class ExampleInstrumentedTest {
    private val VALID_PHONE_NUMBER = "123-345-6789"
    private val INTENT_DATA_PHONE_NUMBER: Uri = Uri.parse("tel:$VALID_PHONE_NUMBER")


    @get:Rule
    val grantPermissionRule = GrantPermissionRule.grant("android.permission.CALL_PHONE")

    @get:Rule
    val activityRule = IntentsTestRule(MainActivity::class.java)

    @Before
    fun stubAllExternalIntents() {
        Intents.intending(Matchers.not(IntentMatchers.isInternal()))
            .respondWith(Instrumentation.ActivityResult(Activity.RESULT_OK, null))
    }

    @Test
    fun typeNumberValidInputInitiatesCall() {
        Espresso.onView(ViewMatchers.withId(R.id.edit_text_caller_number))
            .perform(
                ViewActions.typeText(VALID_PHONE_NUMBER),
                ViewActions.closeSoftKeyboard()
            )
        Espresso.onView(ViewMatchers.withId(R.id.button_call_number)).perform(ViewActions.click())

        Intents.intended(
            Matchers.allOf(
                IntentMatchers.hasAction(Intent.ACTION_CALL),
                IntentMatchers.hasData(INTENT_DATA_PHONE_NUMBER)
            )
        )
    }

    @Test
    fun pickContactButton_click_SelectsPhoneNumber() {
        Intents.intending(IntentMatchers.hasComponent(ComponentNameMatchers.hasShortClassName(".Main2Activity")))
            .respondWith(
                Instrumentation.ActivityResult(
                    Activity.RESULT_OK,
                    Main2Activity.createResultData(VALID_PHONE_NUMBER)
                )
            )
        Espresso.onView(ViewMatchers.withId(R.id.button_pick_contact)).perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.edit_text_caller_number))
            .check(ViewAssertions.matches(ViewMatchers.withText(VALID_PHONE_NUMBER)));
    }
}
