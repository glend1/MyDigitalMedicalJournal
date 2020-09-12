package com.mydigitalmedicaljournal.instrumentTests.fragment

import androidx.navigation.findNavController
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.activityScenarioRule
import com.mydigitalmedicaljournal.MainActivity
import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.instrumentTests.DummyTemplates
import com.mydigitalmedicaljournal.template.file.TemplateList
import com.mydigitalmedicaljournal.ui._generics.ViewHolder
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class TemplateTests {
    @get:Rule
    val activityScenarioRule = activityScenarioRule<MainActivity>()
    private val dt = DummyTemplates(arrayOf("templates"))
    private lateinit var templateList: List<TemplateList.FileList>

    @Before
    fun setup() {
        templateList = dt.get()
        activityScenarioRule.scenario.onActivity { activity ->
            val nav = activity.findNavController(R.id.nav_host_fragment)
            nav.navigate(R.id.nav_templates)
        }
    }

    @After
    fun teardown() {
        dt.delete()
    }

    @Test
    fun add() {
        onView(withId(R.id.add)).perform(click())
        onView(withId(R.id.editText)).check(matches(withText("")))
    }

    @Test
    fun edit() {
        onView(withId(R.id.template_recycler)).perform(actionOnItemAtPosition<ViewHolder>(0, click()))
        onView(withId(R.id.editText)).check(matches(withText(templateList[0].name)))
    }

}