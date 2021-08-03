package com.mydigitalmedicaljournal.instrumentTests.fragment

import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.activityScenarioRule
import com.mydigitalmedicaljournal.MainActivity
import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.instrumentTests.DummyTemplateFile
import com.mydigitalmedicaljournal.instrumentTests.Utils
import com.mydigitalmedicaljournal.template.fields.data.DataName
import com.mydigitalmedicaljournal.template.file.TemplateManager
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class EditorNameTests {
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
            val bundle = bundleOf("position" to 0, "filename" to templateManager.getId())
            activity.findNavController(R.id.nav_host_fragment).navigate(R.id.fieldEditorFragment, bundle)
        }
    }

    @After
    fun teardown() {
        dtf.delete()
    }

    @Test
    fun saveName() {
        val testString = "hello"
        onView(withId(R.id.name)).perform(clearText())
        onView(withId(R.id.name)).perform(typeText(testString))
        onView(withId(R.id.save)).perform(click())
        navigate()
        onView(withId(R.id.name)).check(matches(withText(testString)))
    }

    @Test
    fun noName() {
        onView(withId(R.id.name)).perform(clearText())
        onView(withId(R.id.save)).perform(click())
        onView(withText(Utils.CONTEXT.resources.getString(R.string.NOT_FOUND, Utils.CONTEXT.resources.getString(R.string.name)))).check(matches(isDisplayed()))
    }

    @Test
    fun nameTooLong() {
        onView(withId(R.id.name)).perform(clearText())
        onView(withId(R.id.name)).perform(typeText("12345678901234567890123456"))
        onView(withId(R.id.save)).perform(click())
        onView(withText(Utils.CONTEXT.resources.getString(R.string.LENGTH, Utils.CONTEXT.resources.getString(R.string.name), DataName.LENGTH.toString()))).check(matches(isDisplayed()))
    }

    @Test
    fun nameSpecialCharacters() {
        onView(withId(R.id.name)).perform(clearText())
        onView(withId(R.id.name)).perform(typeText("test!"))
        onView(withId(R.id.save)).perform(click())
        onView(withText(Utils.CONTEXT.resources.getString(R.string.SPECIAL_SYMBOLS, Utils.CONTEXT.resources.getString(R.string.name)))).check(matches(isDisplayed()))
    }
}