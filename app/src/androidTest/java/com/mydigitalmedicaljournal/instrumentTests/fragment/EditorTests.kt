package com.mydigitalmedicaljournal.instrumentTests.fragment

import android.view.View
import android.widget.ImageView
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.RootMatchers.isDialog
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.activityScenarioRule
import com.mydigitalmedicaljournal.MainActivity
import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.instrumentTests.DummyTemplateFile
import com.mydigitalmedicaljournal.instrumentTests.Utils
import com.mydigitalmedicaljournal.template.file.TemplateManager
import com.mydigitalmedicaljournal.ui._generics.ViewHolder
import org.hamcrest.CoreMatchers.not
import org.hamcrest.Matcher
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class EditorTests {
    @get:Rule
    val activityScenarioRule = activityScenarioRule<MainActivity>()
    private lateinit var templateManager: TemplateManager
    private lateinit var dtf: DummyTemplateFile
    private val position = 3
    private val testText = "second"

    fun navigate() {
        activityScenarioRule.scenario.onActivity { activity ->
            val bundle = bundleOf("data" to templateManager.getId())
            activity.findNavController(R.id.nav_host_fragment).navigate(R.id.editorFragment, bundle)
        }
    }

    @Before
    fun setup() {
        dtf = DummyTemplateFile("793b7045-d572-4110-b4c7-9e1dcfa251f1", "test", arrayOf("templates"))
        templateManager = dtf.get()
        dtf.addSimple("first")
        dtf.addSimple(testText)
        dtf.addSimple("third")
        dtf.addSimple("fourth")
        navigate()
    }

    @After
    fun teardown() {
        dtf.delete()
    }

    @Test
    fun addField() {
        onView(withId(R.id.add)).perform(click())
        onView(withText(R.string.add_field)).inRoot(isDialog()).check(matches(isDisplayed()))
        onView(withId(R.id.recycler)).inRoot(isDialog()).perform(RecyclerViewActions.actionOnItemAtPosition<ViewHolder>(0, click()))
        onView(withId(R.id.text)).check(matches(isDisplayed()))
    }

    @Test
    fun delete() {
        onView(withId(R.id.delete_template)).perform(click())
        onView(withText(R.string.yes)).inRoot(isDialog()).perform(click())
        activityScenarioRule.scenario.onActivity { activity ->
            activity.findNavController(R.id.nav_host_fragment).navigate(R.id.nav_templates)
        }
        onView(withText(Utils.CONTEXT.getString(R.string.no_data, Utils.CONTEXT.getString(R.string.template)))).check(matches(isDisplayed()))
    }

    class ClickImage(private val id: Int): ViewAction {
        override fun getDescription(): String {
            return "button not clicked"
        }

        override fun getConstraints(): Matcher<View> {
            return withId(id)
        }

        override fun perform(uiController: UiController?, view: View?) {
            view!!.findViewById<ImageView>(id).performClick()
        }

    }

    @Test
    fun moveFieldUp() {
        onView(withId(R.id.template)).perform(RecyclerViewActions.actionOnItemAtPosition<ViewHolder>(position, ClickImage(R.id.up)))
        onView(withId(R.id.template)).check(matches(Utils.atPosition(position-1, withSubstring(testText))))
    }

    @Test
    fun moveFieldDown() {
        onView(withId(R.id.template)).perform(RecyclerViewActions.actionOnItemAtPosition<ViewHolder>(position, ClickImage(R.id.down)))
        onView(withId(R.id.template)).check(matches(Utils.atPosition(position+1, withSubstring(testText))))
    }

    @Test
    fun deleteField() {
        onView(withId(R.id.template)).perform(RecyclerViewActions.actionOnItemAtPosition<ViewHolder>(position, ClickImage(R.id.delete)))
        onView(withId(R.id.template)).check(matches(not(Utils.atPosition(position, withSubstring(testText)))))
    }

    @Test
    fun save() {
        onView(withId(R.id.template)).perform(RecyclerViewActions.actionOnItemAtPosition<ViewHolder>(position, ClickImage(R.id.delete)))
        onView(withId(R.id.save)).perform(click())
        navigate()
        onView(withId(R.id.template)).check(matches(not(Utils.atPosition(position, withSubstring(testText)))))
    }
}