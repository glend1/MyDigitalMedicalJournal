package com.mydigitalmedicaljournal.instrumentTests.fragment

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.RootMatchers.isDialog
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.activityScenarioRule
import com.mydigitalmedicaljournal.MainActivity
import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.instrumentTests.DummyTemplates
import com.mydigitalmedicaljournal.instrumentTests.Utils
import com.mydigitalmedicaljournal.template.TemplateEnum
import com.mydigitalmedicaljournal.template.data.FileList
import com.mydigitalmedicaljournal.ui._generics.ViewHolder
import org.hamcrest.Matchers.not
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class TemplateTests {
    @get:Rule
    val activityScenarioRule = activityScenarioRule<MainActivity>()
    private lateinit var dt: DummyTemplates
    private lateinit var templateList: List<FileList>

    @Before
    fun setup() {
        dt = DummyTemplates(arrayOf("templates"))
        templateList = dt.get().get()
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
        onView(withText(R.string.new_template_instruction)).inRoot(isDialog()).check(matches(isDisplayed()))
    }

    @Test
    fun navigate() {
        val text = "${Utils.CONTEXT.resources.getString(TemplateEnum.NAME.listName)} : ${templateList[0].name}"
        onView(withId(R.id.template_recycler)).perform(actionOnItemAtPosition<ViewHolder>(0, click()))
        onView(withId(R.id.template)).check(matches(Utils.atPosition(0, withText(text))))
    }

    @Test
    fun delete() {
        onView(withId(R.id.template_recycler)).perform(actionOnItemAtPosition<ViewHolder>(0, click()))
        onView(withId(R.id.delete_template)).perform(click())
        onView(withText(R.string.Yes)).inRoot(isDialog()).perform(click())
        onView(withId(R.id.template_recycler)).check(matches(not(hasDescendant(withText(templateList[0].name)))))
    }

    @Test
    fun displayNumber() {
        onView(withId(R.id.template_recycler)).check(matches(hasChildCount(DummyTemplates.data.size)))
    }
}