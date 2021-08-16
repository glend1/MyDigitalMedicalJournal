package com.mydigitalmedicaljournal.instrumentTests.fragment

import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.activityScenarioRule
import com.mydigitalmedicaljournal.MainActivity
import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.instrumentTests.DummyTemplateFile
import com.mydigitalmedicaljournal.instrumentTests.Utils
import com.mydigitalmedicaljournal.template.fields.data.GenericQuestionData
import com.mydigitalmedicaljournal.template.file.TemplateManager
import com.mydigitalmedicaljournal.ui._generics.ViewHolder
import org.hamcrest.Matchers.containsString
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class EditorQuestionTests {
    @get:Rule
    val activityScenarioRule = activityScenarioRule<MainActivity>()
    private lateinit var templateManager: TemplateManager
    private lateinit var dtf: DummyTemplateFile

    @Before
    fun setup() {
        dtf = DummyTemplateFile("793b7045-d572-4110-b4c7-9e1dcfa251f1", "test", arrayOf("templates"))
        templateManager = dtf.get()
        navigate()
    }

    fun navigate() {
        activityScenarioRule.scenario.onActivity { activity ->
            val bundle = bundleOf("data" to templateManager.getId())
            activity.findNavController(R.id.nav_host_fragment).navigate(R.id.editorFragment, bundle)
        }
        onView(withId(R.id.add)).perform(click())
        onView(withId(R.id.recycler)).inRoot(RootMatchers.isDialog()).perform(RecyclerViewActions.actionOnItemAtPosition<ViewHolder>(0, click()))
    }

    @After
    fun teardown() {
        dtf.delete()
    }

    @Test
    fun saveQuestion() {
        val testString = "hello"
        onView(withId(R.id.editText)).perform(typeText(testString))
        onView(withId(R.id.save)).perform(click())
        onView(withText(containsString(testString))).check(matches(isDisplayed()))
    }

    @Test
    fun noQuestion() {
        onView(withId(R.id.editText)).perform(clearText())
        onView(withId(R.id.save)).perform(click())
        onView(withText(Utils.CONTEXT.resources.getString(R.string.not_found, Utils.CONTEXT.resources.getString(R.string.question)))).check(matches(isDisplayed()))
    }

    @Test
    fun nameTooLong() {
        onView(withId(R.id.editText)).perform(clearText())
        onView(withId(R.id.editText)).perform(typeText("123456789012345678901234567890123456789012345678901234567890123456789012345678901"))
        onView(withId(R.id.save)).perform(click())
        onView(withText(Utils.CONTEXT.resources.getString(R.string.length, Utils.CONTEXT.resources.getString(R.string.question), GenericQuestionData.LENGTH.toString()))).check(matches(isDisplayed()))
    }

    @Test
    fun nameSpecialCharacters() {
        onView(withId(R.id.editText)).perform(clearText())
        onView(withId(R.id.editText)).perform(typeText("test!"))
        onView(withId(R.id.save)).perform(click())
        onView(withText(Utils.CONTEXT.resources.getString(R.string.special_symbols, Utils.CONTEXT.resources.getString(R.string.question)))).check(matches(isDisplayed()))
    }
}