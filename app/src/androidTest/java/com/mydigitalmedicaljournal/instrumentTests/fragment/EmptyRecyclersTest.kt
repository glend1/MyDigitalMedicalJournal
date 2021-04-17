package com.mydigitalmedicaljournal.instrumentTests.fragment

import android.content.Context
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.activityScenarioRule
import com.mydigitalmedicaljournal.MainActivity
import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.instrumentTests.DummyCategoriesAndTemplates
import com.mydigitalmedicaljournal.ui.categories.ManageCategoriesAdapter
import com.mydigitalmedicaljournal.ui.categories.dialog.ManageCategoryTemplatesDialog
import org.junit.Rule
import org.junit.Test

class EmptyRecyclersTest {
    @get:Rule
    val activityScenarioRule = activityScenarioRule<MainActivity>()

    @Test
    fun noTemplates() {
        activityScenarioRule.scenario.onActivity { activity ->
            val nav = activity.findNavController(R.id.nav_host_fragment)
            nav.navigate(R.id.nav_templates)
        }
        onView(withId(R.id.message)).check(matches(isDisplayed()))
    }

    @Test
    fun noCategories() {
        activityScenarioRule.scenario.onActivity { activity ->
            val nav = activity.findNavController(R.id.nav_host_fragment)
            nav.navigate(R.id.nav_categories)
        }
        onView(withId(R.id.message)).check(matches(isDisplayed()))
    }

    @Test
    fun noSelector() {
        activityScenarioRule.scenario.onActivity { activity ->
            val nav = activity.findNavController(R.id.nav_host_fragment)
            nav.navigate(R.id.templateSelector)
        }
        onView(withText(R.string.no_templates)).check(matches(isDisplayed()))
    }

    @Test
    fun noFilteredSelector() {
        var dtc : DummyCategoriesAndTemplates? = null
        activityScenarioRule.scenario.onActivity { activity ->
            val nav = activity.findNavController(R.id.nav_host_fragment)
            dtc = DummyCategoriesAndTemplates("categories.json", arrayOf("templates"))
            nav.navigate(R.id.templateSelector)
        }
        onView(withId(R.id.search_view)).perform(typeText("pine"), closeSoftKeyboard())
        onView(withText(R.string.no_templates_filtered)).check(matches(isDisplayed()))
        dtc?.delete()
    }

    @Test
    fun noTemplatesInDialog() {
        var dialog: ManageCategoryTemplatesDialog?
        val adapter = ManageCategoriesAdapter(listOf(), mutableListOf())
        activityScenarioRule.scenario.onActivity { activity: MainActivity? ->
            dialog = ManageCategoryTemplatesDialog("title", "message", activity as Context, adapter, container)
            dialog!!.setConfirm { _, _ -> }
            dialog!!.show()
        }
        onView(withText(R.string.no_templates)).inRoot(RootMatchers.isDialog()).check(matches(isDisplayed()))
    }

}