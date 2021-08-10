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
import com.mydigitalmedicaljournal.template.fields.data.question.DataCheck
import com.mydigitalmedicaljournal.template.fields.data.question.DataRadio
import com.mydigitalmedicaljournal.template.file.TemplateManager
import com.mydigitalmedicaljournal.ui._generics.ViewHolder
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.not
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class EditorSingleTests {
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
        onView(withId(R.id.recycler)).inRoot(isDialog()).perform(actionOnItemAtPosition<ViewHolder>(2, click()))
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
    fun singleSave() {
        val testTextOne = "test"
        val testTextTwo = "hello"
        onView(withId(R.id.editText)).perform(typeText("question"))
        onView(withId(R.id.radio_recycler)).perform(actionOnItemAtPosition<ViewHolder>(0, typeText(testTextOne)))
        onView(withId(R.id.radio_recycler)).perform(actionOnItemAtPosition<ViewHolder>(1, typeText(testTextTwo)))
        onView(withId(R.id.save)).perform(click())
        onView(withId(R.id.template)).perform(actionOnItemAtPosition<ViewHolder>(2, click()))
        onView(withText(testTextOne)).check(matches(isDisplayed()))
        onView(withText(testTextTwo)).check(matches(isDisplayed()))
    }

    @Test
    fun noOption() {
        onView(withId(R.id.save)).perform(click())
        onView(withId(R.id.radio_recycler)).check(matches(Utils.atPosition(0, withText(Utils.CONTEXT.getString(R.string.NOT_FOUND, Utils.CONTEXT.getString(R.string.Radio))))))
    }

    @Test
    fun optionTooLong() {
        onView(withId(R.id.radio_recycler)).perform(actionOnItemAtPosition<ViewHolder>(0, typeText("012345678901234567890123456")))
        onView(withId(R.id.save)).perform(click())
        onView(withId(R.id.radio_recycler)).check(matches(Utils.atPosition(0, withText(Utils.CONTEXT.getString(R.string.LENGTH, Utils.CONTEXT.getString(R.string.Radio), DataRadio.LENGTH.toString())))))
    }

    @Test
    fun optionSpecialCharacters() {
        onView(withId(R.id.radio_recycler)).perform(actionOnItemAtPosition<ViewHolder>(0, typeText("test!")))
        onView(withId(R.id.save)).perform(click())
        onView(withId(R.id.radio_recycler)).check(matches(Utils.atPosition(0, withText(Utils.CONTEXT.getString(R.string.SPECIAL_SYMBOLS, Utils.CONTEXT.getString(R.string.Radio))))))
    }

    @Test
    fun twoOptionMinimum() {
        onView(withId(R.id.save)).perform(click())
        onView(withId(R.id.radio_error)).check(matches(isDisplayed()))
    }

    @Test
    fun addOption() {
        val testText = "new"
        onView(withId(R.id.editText)).perform(typeText("question"))
        onView(withId(R.id.radio_recycler)).perform(actionOnItemAtPosition<ViewHolder>(0, typeText("test")))
        onView(withId(R.id.radio_recycler)).perform(actionOnItemAtPosition<ViewHolder>(1, typeText("hello")))
        onView(withId(R.id.addRadio)).perform(click())
        onView(withId(R.id.radio_recycler)).perform(actionOnItemAtPosition<ViewHolder>(2, typeText(testText)))
        onView(withId(R.id.save)).perform(click())
        onView(withId(R.id.template)).perform(actionOnItemAtPosition<ViewHolder>(2, click()))
        onView(withText(testText)).check(matches(isDisplayed()))
    }

    //no need to test the recycler since the code is used in multiple
}