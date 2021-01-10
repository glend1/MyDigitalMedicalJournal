package com.mydigitalmedicaljournal.instrumentTests.fragment

import android.content.Context
import androidx.navigation.findNavController
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
import com.mydigitalmedicaljournal.ui._generics.dialogs.ListDialog
import com.mydigitalmedicaljournal.ui.categories.ManageCategoriesAdapter
import org.junit.Rule
import org.junit.Test

class EmptyRecyclers {
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
        var dialog: ListDialog?
        val adapter = ManageCategoriesAdapter(listOf(), mutableListOf())
        activityScenarioRule.scenario.onActivity { activity: MainActivity? ->
            dialog = ListDialog("title", "message", activity as Context, adapter)
            dialog!!.setConfirm { _, _ -> }
            dialog!!.show()
        }
        onView(withText(R.string.no_templates)).inRoot(RootMatchers.isDialog()).check(matches(isDisplayed()))
    }

}