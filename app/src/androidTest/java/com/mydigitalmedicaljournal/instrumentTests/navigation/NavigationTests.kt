package com.mydigitalmedicaljournal.instrumentTests.navigation

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.activityScenarioRule
import com.mydigitalmedicaljournal.MainActivity
import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.instrumentTests.Utils
import org.junit.Rule
import org.junit.Test

class NavigationTests {
    @get:Rule
    var activityScenarioRule = activityScenarioRule<MainActivity>()
    var navigation = Utils.Navigation(activityScenarioRule)


    @Test
    fun journal() {
        navigation.navigate(R.id.nav_journal)
        onView(withId(R.id.text_journal)).check(matches(isDisplayed()))
    }

    @Test
    fun reports() {
        navigation.navigate(R.id.nav_reports)
        onView(withId(R.id.text_reports)).check(matches(isDisplayed()))
    }

    @Test
    fun calendar() {
        navigation.navigate(R.id.nav_calendar)
        onView(withId(R.id.text_calendar)).check(matches(isDisplayed()))
    }

    @Test
    fun settings() {
        navigation.navigate(R.id.nav_settings)
        onView(withText(R.string.settings_weight)).check(matches(isDisplayed()))
    }

    @Test
    fun support() {
        navigation.navigate(R.id.nav_social)
        onView(withId(R.id.text_social)).check(matches(isDisplayed()))
    }

    @Test
    fun instructions() {
        navigation.navigate(R.id.nav_instructions)
        onView(withId(R.id.text_instructions)).check(matches(isDisplayed()))
    }

    @Test
    fun copyright() {
        navigation.navigate(R.id.nav_copyright)
        onView(withId(R.id.text_copyright)).check(matches(isDisplayed()))
    }

    @Test
    fun categories() {
        navigation.navigate(R.id.nav_categories)
        onView(withId(R.id.recycler)).check(matches(isDisplayed()))
    }

    @Test
    fun templates() {
        navigation.navigate(R.id.nav_templates)
        onView(withId(R.id.template_recycler)).check(matches(isDisplayed()))
    }

    @Test
    fun editTemplates() {
        navigation.navigate(R.id.editorFragment)
        onView(withId(R.id.template)).check(matches(isDisplayed()))
    }

}