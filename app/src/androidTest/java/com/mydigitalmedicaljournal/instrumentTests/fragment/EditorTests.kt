package com.mydigitalmedicaljournal.instrumentTests.fragment

import android.view.View
import android.widget.EditText
import androidx.appcompat.widget.AppCompatRadioButton
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.RootMatchers.isDialog
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.activityScenarioRule
import com.mydigitalmedicaljournal.MainActivity
import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.instrumentTests.Utils
import com.mydigitalmedicaljournal.template.fields.data.DataTime
import com.mydigitalmedicaljournal.ui._generics.ViewHolder
import com.mydigitalmedicaljournal.ui.templates.editor.EditorAdapter
import junit.framework.TestCase.assertEquals
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.not
import org.hamcrest.Matcher
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class EditorTests {
    @get:Rule
    val activityScenarioRule = activityScenarioRule<MainActivity>()

    @Before
    fun setup() {
        activityScenarioRule.scenario.onActivity { activity ->
            val nav = activity.findNavController(R.id.nav_host_fragment)
            nav.navigate(R.id.nav_templates)
        }
        onView(withId(R.id.add)).perform(click())
    }

    @Test
    fun saveButtonError() {
        onView(withId(R.id.save)).perform(click())
        onView(withId(com.google.android.material.R.id.snackbar_text)).check(matches(withText(R.string.error_message)))
        onView(withText(R.string.error_name)).check(matches(isDisplayed()))
        onView(withText(R.string.error_time)).check(matches(isDisplayed()))
    }

    @Test
    fun saveDeleteButtonSuccess() {
        val title = "hello"
        onView(withId(R.id.template)).perform(RecyclerViewActions.actionOnItemAtPosition<ViewHolder>(0, TypeText(title)))
        onView(withId(R.id.template)).perform(RecyclerViewActions.actionOnItemAtPosition<ViewHolder>(1, TimeCheckBox()))
        onView(withId(R.id.add)).perform(click())
        onView(withId(R.id.custom)).perform(click())
        onView(withText(R.string.radio)).inRoot(RootMatchers.isPlatformPopup()).perform(click())
        onView(withText(R.string.Yes)).inRoot(isDialog()).perform(click())
        onView(withId(R.id.save)).perform(click())
        onView(withId(R.id.template_recycler)).check(matches(Utils.atPosition(0, withText(title))))
        onView(withId(R.id.template_recycler)).perform(RecyclerViewActions.actionOnItemAtPosition<ViewHolder>(0, click()))
        onView(withId(R.id.delete_template)).perform(click())
        onView(withText(R.string.Yes)).inRoot(isDialog()).perform(click())
    }

    @Test
    fun addButton() {
        onView(withId(R.id.add)).perform(click())
        onView(withText(R.string.add_field)).inRoot(isDialog()).check(matches(isDisplayed()))
    }

    @Test
    fun useAddButtonAndDelete() {
        onView(withId(R.id.add)).perform(click())
        onView(withId(R.id.custom)).perform(click())
        onView(withText(R.string.radio)).inRoot(RootMatchers.isPlatformPopup()).perform(click())
        onView(withText(R.string.Yes)).inRoot(isDialog()).perform(click())
        onView(withId(R.id.template)).check(matches(Utils.atPosition(2, withText(R.string.radio))))
        onView(allOf(withId(R.id.delete), isDescendantOfA(withId(R.id.template)))).perform(click())
        onView(withId(R.id.template)).check(matches(hasChildCount(2)))
    }

    @Test
    fun deleteButton() {
        onView(withId(R.id.delete_template)).perform(click())
        onView(withId(com.google.android.material.R.id.snackbar_text)).check(matches(withText(R.string.file_not_found)))
    }

    class TypeText(private val text: String): ViewAction {
        override fun getDescription(): String {
            return "name field was not changed"
        }

        override fun getConstraints(): Matcher<View> {
            return withId(R.id.nameEditText)
        }

        override fun perform(uiController: UiController?, view: View?) {
            view!!.findViewById<EditText>(R.id.nameEditText).setText(text)
        }

    }

    @Test
    fun name() {
        val testText = "hello"
        onView(withId(R.id.template)).perform(RecyclerViewActions.actionOnItemAtPosition<ViewHolder>(0, TypeText(testText)))
        onView(withId(R.id.template)).check(matches(Utils.atPosition(0, withText(testText))))
        activityScenarioRule.scenario.onActivity { activity ->
            val recycler = activity.findViewById<RecyclerView>(R.id.template)
            val adapter = recycler.adapter as EditorAdapter
            assertEquals(testText, adapter.localData.name)
        }
    }

    @Test
    fun dynamicText() {
        val testText = "hello"
        onView(withId(R.id.template)).perform(RecyclerViewActions.actionOnItemAtPosition<ViewHolder>(0, TypeText(testText)))
        onView(withText(R.string.error_name)).check(matches(not(isDisplayed())))
        onView(withId(R.id.template)).perform(RecyclerViewActions.actionOnItemAtPosition<ViewHolder>(0, TypeText("")))
        onView(withText(R.string.error_name)).check(matches(isDisplayed()))
    }

    class TimeCheckBox: ViewAction {
        override fun getDescription(): String {
            return "time format was not clicked"
        }

        override fun getConstraints(): Matcher<View> {
            return withId(R.id.date_time)
        }

        override fun perform(uiController: UiController?, view: View?) {
            view?.findViewById<AppCompatRadioButton>(R.id.date_time)?.performClick()
        }

    }

    @Test
    fun timeFormat() {
        onView(withId(R.id.template)).perform(RecyclerViewActions.actionOnItemAtPosition<ViewHolder>(1, TimeCheckBox()))
        onView(withId(R.id.date_time)).check(matches(isChecked()))
        activityScenarioRule.scenario.onActivity { activity ->
            val recycler = activity.findViewById<RecyclerView>(R.id.template)
            val adapter = recycler.adapter as EditorAdapter
            assertEquals(DataTime.TimeFormat.DATETIME, adapter.localData.time)
        }
    }
}