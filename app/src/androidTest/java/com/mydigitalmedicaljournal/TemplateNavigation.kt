package com.mydigitalmedicaljournal

import androidx.navigation.findNavController
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.activityScenarioRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class TemplateNavigation {
    //TODO move these up a layer in the navigation
    @get:Rule
    var activityScenarioRule = activityScenarioRule<MainActivity>()

    @Before
    fun setup() {
        activityScenarioRule.scenario.onActivity { activity ->
            val nav = activity.findNavController(R.id.nav_host_fragment)
            nav.navigate(R.id.nav_templates)
        }
    }

    @Test
    fun navigateCategories() {
        onView(withText(R.string.manage_categories)).perform(click())
        onView(withId(R.id.category_recycler)).check(matches(isDisplayed()))
    }

    @Test
    fun navigateTemplates() {
        onView(withText(R.string.manage_templates)).perform(click())
        onView(withId(R.id.template_recycler)).check(matches(isDisplayed()))
    }
}