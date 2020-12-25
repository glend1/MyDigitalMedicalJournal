package com.mydigitalmedicaljournal.instrumentTests.global

import android.content.pm.ActivityInfo
import android.view.Gravity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.DrawerActions
import androidx.test.espresso.contrib.DrawerMatchers.isClosed
import androidx.test.espresso.contrib.DrawerMatchers.isOpen
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.activityScenarioRule
import com.mydigitalmedicaljournal.MainActivity
import com.mydigitalmedicaljournal.R
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PortraitTests {
    @get:Rule
    var activityScenarioRule = activityScenarioRule<MainActivity>()

    @Before
    fun setup() {
        activityScenarioRule.scenario.onActivity { activity ->
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }
    }

    @Test
    fun drawerClosedAtLaunch() {
        onView(withId(R.id.drawer_layout)).check(matches(isClosed(Gravity.LEFT)))
    }

    @Test
    fun drawerOpens() {
        //TODO this fails sometimes
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open())
        onView(withId(R.id.drawer_layout)).check(matches(isOpen(Gravity.LEFT)))
    }

    @Test
    fun fragmentExists() {
        onView(withId(R.id.nav_host_fragment)).check(matches(isDisplayed()))
    }

}