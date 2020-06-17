package com.mydigitalmedicaljournal

import androidx.navigation.findNavController
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.activityScenarioRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PreferencesTests {
    @get:Rule
    var activityScenarioRule = activityScenarioRule<MainActivity>()

    @Before
    fun navigate() {
        activityScenarioRule.scenario.onActivity { activity ->
            val nav = activity.findNavController(R.id.nav_host_fragment)
            nav.navigate(R.id.nav_settings)
        }
    }

    //TODO needs more tests here
    @Test
    fun settingsChange() {
        onView(withText("Weight")).perform(click())
        onView(withText("Pounds (lb)")).perform(click())//.inRoot(isDialog()).check(matches(isDisplayed()))
        onView(withText("Pounds (lb)")).check(matches(isDisplayed()))
    }
}