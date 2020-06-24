package com.mydigitalmedicaljournal

import android.content.pm.ActivityInfo
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.activityScenarioRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class LandscapeTests {
    @get:Rule
    var activityScenarioRule = activityScenarioRule<MainActivity>()

    //TODO need a delay here for more consistent tests
    @Before
    fun setup() {
        activityScenarioRule.scenario.onActivity { activity ->
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            Thread.sleep(400)
        }
    }

    @Test
    fun navigationExists() {
        onView(withId(R.id.nav_view)).check(matches(isDisplayed()))
    }

    @Test
    fun fragmentExists() {
        onView(withId(R.id.nav_host_fragment)).check(matches(isDisplayed()))
    }
}