package com.mydigitalmedicaljournal

import androidx.navigation.findNavController
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.activityScenarioRule
import org.junit.Rule
import org.junit.Test

//TODO this tests the setup for the nav controller not the actual views
class NavigationTests {
    @get:Rule
    var activityScenarioRule = activityScenarioRule<MainActivity>()

    private fun navigate(fragment: Int) {
        activityScenarioRule.scenario.onActivity { activity ->
            val nav = activity.findNavController(R.id.nav_host_fragment)
            nav.navigate(fragment)
        }
    }

    @Test
    fun journal() {
        navigate(R.id.nav_journal)
        onView(withId(R.id.text_journal)).check(matches(isDisplayed()))
    }

    @Test
    fun reports() {
        navigate(R.id.nav_reports)
        onView(withId(R.id.text_reports)).check(matches(isDisplayed()))
    }

    @Test
    fun calendar() {
        navigate(R.id.nav_calendar)
        onView(withId(R.id.text_calendar)).check(matches(isDisplayed()))
    }

    @Test
    fun templates() {
        navigate(R.id.nav_templates)
        onView(withId(R.id.template_list)).check(matches(isDisplayed()))
    }

    @Test
    fun settings() {
        navigate(R.id.nav_settings)
        //TODO wish this was more specific
        onView(withText("Weight")).check(matches(isDisplayed()))
    }

    @Test
    fun support() {
        navigate(R.id.nav_social)
        onView(withId(R.id.text_social)).check(matches(isDisplayed()))
    }

    @Test
    fun instructions() {
        navigate(R.id.nav_instructions)
        onView(withId(R.id.text_instructions)).check(matches(isDisplayed()))
    }

    @Test
    fun copyright() {
        navigate(R.id.nav_copyright)
        onView(withId(R.id.text_copyright)).check(matches(isDisplayed()))
    }

}