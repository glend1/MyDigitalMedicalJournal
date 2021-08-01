package com.mydigitalmedicaljournal.instrumentTests.fragment

import androidx.navigation.findNavController
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers.isDialog
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.activityScenarioRule
import com.mydigitalmedicaljournal.MainActivity
import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.instrumentTests.Utils
import org.hamcrest.Matchers.containsString
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NewEditorTest {
    @get:Rule
    val activityScenarioRule = activityScenarioRule<MainActivity>()

    @Before
    fun setup() {
        activityScenarioRule.scenario.onActivity { activity ->
            activity.findNavController(R.id.nav_host_fragment).navigate(R.id.nav_templates)
        }
        onView(withId(R.id.add)).perform(click())
    }

    @After
    fun teardown() {
    }

    @Test
    fun nameError() {
        onView(withText(R.string.save)).inRoot(isDialog()).perform(click())
        onView(withId(R.id.error_name)).inRoot(isDialog()).check(matches(isDisplayed()))
    }

    @Test
    fun nameTime() {
        onView(withText(R.string.save)).inRoot(isDialog()).perform(click())
        onView(withId(R.id.error_date)).inRoot(isDialog()).check(matches(isDisplayed()))
    }

    @Test
    fun enterData() {
        val testString = "hello"
        onView(withId(R.id.dialog_name)).inRoot(isDialog()).perform(typeText(testString))
        onView(withId(R.id.date_time)).inRoot(isDialog()).perform(click())
        onView(withText(R.string.save)).inRoot(isDialog()).perform(click())
        onView(withText(containsString(testString))).check(matches(isDisplayed()))
        onView(withText(containsString(Utils.CONTEXT.resources.getString(R.string.date_time)))).check(matches(isDisplayed()))
        onView(withId(R.id.delete_template)).perform(click())
        onView(withText(R.string.yes)).inRoot(isDialog()).perform(click())
    }
}