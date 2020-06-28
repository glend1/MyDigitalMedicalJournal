package com.mydigitalmedicaljournal

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class OptionsNavigation {
    @get:Rule
    var activityScenarioRule = activityScenarioRule<MainActivity>()

    @Before
    fun setup() {
        openActionBarOverflowOrOptionsMenu(getInstrumentation().targetContext)
    }

    @Test
    fun openSettings() {
        onView(withText(R.string.action_settings)).perform(click())
        onView(withText(R.string.settings_weight)).check(matches(isDisplayed()))
    }

    @Test
    fun openSupport() {
        onView(withText(R.string.action_support)).perform(click())
        onView(withId(R.id.text_support)).check(matches(isDisplayed()))
    }

    @Test
    fun openSocial() {
        onView(withText(R.string.action_social)).perform(click())
        onView(withId(R.id.text_social)).check(matches(isDisplayed()))
    }

    @Test
    fun openInstructions() {
        onView(withText(R.string.action_instructions)).perform(click())
        onView(withId(R.id.text_instructions)).check(matches(isDisplayed()))
    }

    @Test
    fun openCopyright() {
        onView(withText(R.string.action_copyright)).perform(click())
        onView(withId(R.id.text_copyright)).check(matches(isDisplayed()))
    }
}