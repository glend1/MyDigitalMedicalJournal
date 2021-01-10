package com.mydigitalmedicaljournal.instrumentTests.navigation

import android.content.pm.ActivityInfo
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.activityScenarioRule
import com.mydigitalmedicaljournal.MainActivity
import com.mydigitalmedicaljournal.R
import org.hamcrest.core.AllOf.allOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NavigationUITests {
    @get:Rule
    var activityScenarioRule = activityScenarioRule<MainActivity>()

    @Before
    fun setup() {
        activityScenarioRule.scenario.onActivity { activity ->
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }
    }

    @Test
    fun journalTest() {
        onView(allOf(isDescendantOfA(withId(R.id.nav_view)), withText(
            R.string.drawer_journal
        ))).perform(click())
        onView(withId(R.id.text_journal)).check(matches(isDisplayed()))
    }

    @Test
    fun reportsTest() {
        onView(allOf(isDescendantOfA(withId(R.id.nav_view)), withText(
            R.string.drawer_reports
        ))).perform(click())
        onView(withId(R.id.text_reports)).check(matches(isDisplayed()))
    }

    @Test
    fun calendarTest() {
        onView(allOf(isDescendantOfA(withId(R.id.nav_view)), withText(
            R.string.drawer_calendar
        ))).perform(click())
        onView(withId(R.id.text_calendar)).check(matches(isDisplayed()))
    }

    @Test
    fun templatesTest() {
        onView(allOf(isDescendantOfA(withId(R.id.nav_view)), withText(
            R.string.drawer_templates
        ))).perform(click())
        onView(withId(R.id.template_recycler)).check(matches(isDisplayed()))
    }

    @Test
    fun categoryTest() {
        onView(allOf(isDescendantOfA(withId(R.id.nav_view)), withText(R.string.drawer_categories))).perform(click())
        onView(withId(R.id.recycler)).check(matches(isDisplayed()))
    }
}