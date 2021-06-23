package com.mydigitalmedicaljournal.instrumentTests.fragment

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.activityScenarioRule
import com.mydigitalmedicaljournal.MainActivity
import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.instrumentTests.DummyCategoriesAndTemplates
import org.junit.Rule
import org.junit.Test

class EmptyRecyclersTest {
    @get:Rule
    val activityScenarioRule = activityScenarioRule<MainActivity>()

    @Test
    fun noTemplates() {
        activityScenarioRule.scenario.onActivity { activity ->
            activity.findNavController(R.id.nav_host_fragment).navigate(R.id.nav_templates)
        }
        onView(withId(R.id.message)).check(matches(isDisplayed()))
    }

    @Test
    fun noCategories() {
        activityScenarioRule.scenario.onActivity { activity ->
            activity.findNavController(R.id.nav_host_fragment).navigate(R.id.nav_categories)
        }
        onView(withText(R.string.no_categories)).check(matches(isDisplayed()))
    }

    @Test
    fun noSelector() {
        activityScenarioRule.scenario.onActivity { activity ->
            activity.findNavController(R.id.nav_host_fragment).navigate(R.id.templateSelector)
        }
        onView(withText(R.string.no_templates)).check(matches(isDisplayed()))
    }

    @Test
    fun noFilteredSelector() {
        var dtc : DummyCategoriesAndTemplates? = null
        activityScenarioRule.scenario.onActivity { activity ->
            dtc = DummyCategoriesAndTemplates("categories.json", arrayOf("templates"))
            activity.findNavController(R.id.nav_host_fragment).navigate(R.id.templateSelector)
        }
        onView(withId(R.id.search_view)).perform(typeText("pine"), closeSoftKeyboard())
        onView(withText(R.string.no_templates_filtered)).check(matches(isDisplayed()))
        dtc?.delete()
    }

    @Test
    fun noTemplatesInCategory() {
        activityScenarioRule.scenario.onActivity { activity ->
            activity.findNavController(R.id.nav_host_fragment).navigate(R.id.manageCategoryTemplatesFragment)
        }
        onView(withText(R.string.no_templates)).check(matches(isDisplayed()))
    }

    @Test
    fun noEditor() {
        activityScenarioRule.scenario.onActivity { activity ->
            activity.findNavController(R.id.nav_host_fragment).navigate(R.id.editorFragment)
        }
        onView(withText(R.string.template_not_selected)).check(matches(isDisplayed()))
    }

}