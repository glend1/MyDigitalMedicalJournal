package com.mydigitalmedicaljournal.instrumentTests.fragment

import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.RootMatchers.isDialog
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.activityScenarioRule
import com.mydigitalmedicaljournal.MainActivity
import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.instrumentTests.DummyTemplateFile
import com.mydigitalmedicaljournal.instrumentTests.Utils
import com.mydigitalmedicaljournal.template.fields.data.question.DataValue
import com.mydigitalmedicaljournal.template.file.TemplateManager
import com.mydigitalmedicaljournal.ui._generics.ViewHolder
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class EditorValueTests {
    @get:Rule
    val activityScenarioRule = activityScenarioRule<MainActivity>()
    private lateinit var templateManager: TemplateManager
    private lateinit var dtf: DummyTemplateFile

    fun navigate() {
        activityScenarioRule.scenario.onActivity { activity ->
            val bundle = bundleOf("data" to templateManager.getId())
            activity.findNavController(R.id.nav_host_fragment).navigate(R.id.editorFragment, bundle)
        }
        onView(withId(R.id.add)).perform(click())
        onView(withText(R.string.add_field)).inRoot(isDialog()).check(matches(isDisplayed()))
        onView(withId(R.id.recycler)).inRoot(isDialog()).perform(actionOnItemAtPosition<ViewHolder>(5, click()))
    }

    @Before
    fun setup() {
        dtf = DummyTemplateFile("793b7045-d572-4110-b4c7-9e1dcfa251f1", "test", arrayOf("templates"))
        templateManager = dtf.get()
        navigate()
    }

    @After
    fun teardown() {
        dtf.delete()
    }

    @Test
    fun valueSave() {
        val test = "ml"
        onView(withId(R.id.editText)).perform(typeText("question"))
        onView(withId(R.id.unit_input)).perform(typeText(test))
        onView(withId(R.id.save)).perform(click())
        onView(withId(R.id.template)).perform(actionOnItemAtPosition<ViewHolder>(2, click()))
        onView(withId(R.id.unit_input)).check(matches(withText(test)))
    }

    @Test
    fun noValue() {
        onView(withId(R.id.save)).perform(click())
        onView(withId(R.id.unit_error)).check(matches(withText(Utils.CONTEXT.getString(R.string.NOT_FOUND, Utils.CONTEXT.getString(R.string.unit)))))
    }

    @Test
    fun valueTooLong() {
        onView(withId(R.id.unit_input)).perform(typeText("01234567890"))
        onView(withId(R.id.save)).perform(click())
        onView(withId(R.id.unit_error)).check(matches(withText(Utils.CONTEXT.getString(R.string.LENGTH, Utils.CONTEXT.getString(R.string.unit), DataValue.LENGTH.toString()))))
    }

}