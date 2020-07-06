package com.mydigitalmedicaljournal

import androidx.navigation.findNavController
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import com.mydigitalmedicaljournal.template.file.TemplateList
import com.mydigitalmedicaljournal.ui._generics.ViewHolder
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class TemplateTests {
    @get:Rule
    val activityScenarioRule = activityScenarioRule<MainActivity>()

    @Before
    fun setup() {
        activityScenarioRule.scenario.onActivity { activity ->
            val nav = activity.findNavController(R.id.nav_host_fragment)
            nav.navigate(R.id.nav_templates)
        }
    }

    @Test
    fun add() {
        onView(withId(R.id.add)).perform(click())
        onView(withId(R.id.editText)).check(matches(withText("")))
    }

    @Test
    fun edit() {
        onView(withId(R.id.template_recycler)).perform(actionOnItemAtPosition<ViewHolder>(0, click()))
        val template = TemplateList.getTemplates(getInstrumentation().targetContext, arrayOf("templates"))
        onView(withId(R.id.editText)).check(matches(withText(template[0].name)))
    }
}