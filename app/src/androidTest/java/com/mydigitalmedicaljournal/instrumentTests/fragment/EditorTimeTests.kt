package com.mydigitalmedicaljournal.instrumentTests.fragment

import android.widget.RadioGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.activityScenarioRule
import com.mydigitalmedicaljournal.MainActivity
import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.instrumentTests.DummyTemplateFile
import com.mydigitalmedicaljournal.instrumentTests.Utils
import com.mydigitalmedicaljournal.template.file.TemplateManager
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class EditorTimeTests {
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
            val bundle = bundleOf("position" to 1, "filename" to templateManager.getId())
            activity.findNavController(R.id.nav_host_fragment).navigate(R.id.fieldEditorFragment, bundle)
        }
    }

    @After
    fun teardown() {
        dtf.delete()
    }

    @Test
    fun saveTime() {
        onView(withId(R.id.date_time)).perform(click())
        onView(withId(R.id.save)).perform(click())
        navigate()
        onView(withId(R.id.date_time)).check(matches(isChecked()))
    }

    @Test
    fun noTime() {
        activityScenarioRule.scenario.onActivity { activity ->
            activity.findViewById<RadioGroup>(R.id.time_format).clearCheck()
        }
        onView(withId(R.id.save)).perform(click())
        onView(withText(Utils.CONTEXT.resources.getString(R.string.not_found, Utils.CONTEXT.resources.getString(R.string.time_format)))).check(matches(isDisplayed()))
    }
}