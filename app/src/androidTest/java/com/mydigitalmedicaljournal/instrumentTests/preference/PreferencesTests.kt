package com.mydigitalmedicaljournal.instrumentTests.preference

import androidx.navigation.findNavController
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers.isDialog
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.activityScenarioRule
import com.mydigitalmedicaljournal.MainActivity
import com.mydigitalmedicaljournal.R
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

    private fun checkOption(string: Int) {
        onView(withText(string)).perform(click())
        onView(withText(string)).inRoot(isDialog()).check(matches(isDisplayed()))
    }

    @Test
    fun weightOption() {
        checkOption(R.string.settings_weight)
    }

    @Test
    fun volumeOption() {
        checkOption(R.string.settings_volume)
    }

    @Test
    fun lengthOption() {
        checkOption(R.string.settings_length)
    }

    @Test
    fun timeOption() {
        checkOption(R.string.settings_time)
    }

    @Test
    fun dateOption() {
        checkOption(R.string.settings_date)
    }
}