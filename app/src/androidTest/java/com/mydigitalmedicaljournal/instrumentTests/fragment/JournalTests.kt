package com.mydigitalmedicaljournal.instrumentTests.fragment

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.activityScenarioRule
import com.mydigitalmedicaljournal.MainActivity
import com.mydigitalmedicaljournal.R
import org.junit.Rule
import org.junit.Test

class JournalTests {
    @get:Rule
    val activityScenarioRule = activityScenarioRule<MainActivity>()

    @Test
    fun new() {
        onView(withId(R.id.fab)).perform(click())
        onView(withId(R.id.selector_recycler)).check(matches(isDisplayed()))
    }
}