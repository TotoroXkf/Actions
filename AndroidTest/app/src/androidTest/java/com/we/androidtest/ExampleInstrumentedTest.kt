package com.we.androidtest

import android.content.Intent
import androidx.test.espresso.web.assertion.WebViewAssertions
import androidx.test.espresso.web.sugar.Web
import androidx.test.espresso.web.webdriver.DriverAtoms
import androidx.test.espresso.web.webdriver.Locator
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class ExampleInstrumentedTest {
    private val MACCHIATO = "Macchiato"
    private val DOPPIO = "Doppio"

    @get:Rule
    val activityRule =
        object : ActivityTestRule<MainActivity>(MainActivity::class.java, false, false) {
            override fun afterActivityLaunched() {
                Web.onWebView().forceJavascriptEnabled()
            }
        }

    @Test
    fun typeTextInInputClickButtonSubmitsForm() {
        activityRule.launchActivity(withWebFormIntent())

        Web.onWebView()
            .withElement(DriverAtoms.findElement(Locator.ID, "text_input"))
            .perform(DriverAtoms.clearElement())
            .perform(DriverAtoms.webKeys(MACCHIATO))
            .withElement(DriverAtoms.findElement(Locator.ID, "submitBtn"))
            .perform(DriverAtoms.webClick())
            .withElement(DriverAtoms.findElement(Locator.ID, "response"))
            .check(
                WebViewAssertions.webMatches(
                    DriverAtoms.getText(),
                    Matchers.containsString(MACCHIATO)
                )
            )
    }

    @Test
    fun typeTextInInputClickButtonChangesText() {
        activityRule.launchActivity(withWebFormIntent())

        Web.onWebView()
            .withElement(DriverAtoms.findElement(Locator.ID, "text_input"))
            .perform(DriverAtoms.clearElement())
            .perform(DriverAtoms.webKeys(DOPPIO))
            .withElement(DriverAtoms.findElement(Locator.ID, "changeTextBtn"))
            .perform(DriverAtoms.webClick())
            .withElement(DriverAtoms.findElement(Locator.ID, "message"))
            .check(
                WebViewAssertions.webMatches(
                    DriverAtoms.getText(),
                    Matchers.containsString(DOPPIO)
                )
            )
    }
}

fun withWebFormIntent(): Intent {
    val basicFormIntent = Intent()
    basicFormIntent.putExtra(KEY_URL_TO_LOAD, WEB_FORM_URL)
    return basicFormIntent
}