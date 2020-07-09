package com.mydigitalmedicaljournal.instrumentTests.fragment

import android.view.View
import android.widget.EditText
import androidx.appcompat.widget.AppCompatRadioButton
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
import com.mydigitalmedicaljournal.ui._generics.ViewHolder
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
    fun saveButton() {
        onView(withId(R.id.save)).perform(click())
        onView(withId(com.google.android.material.R.id.snackbar_text)).check(matches(withText(
            R.string.error_message
        )))
    }

    @Test
    fun addButton() {
        onView(withId(R.id.add)).perform(click())
        onView(withText(R.string.add_field)).inRoot(isDialog()).check(matches(isDisplayed()))
    }

    @Test
    fun deleteButton() {
        onView(withId(R.id.delete)).perform(click())
        onView(withId(com.google.android.material.R.id.snackbar_text)).check(matches(withText(
            R.string.file_not_found
        )))
    }

    inner class ClickName: ViewAction {
        override fun getDescription(): String {
            return "name field was not clicked"
        }

        override fun getConstraints(): Matcher<View> {
            return withId(R.id.editText)
        }

        override fun perform(uiController: UiController?, view: View?) {
            view?.findViewById<EditText>(R.id.editText)?.requestFocus()
        }

    }

    @Test
    fun name() {
        onView(withId(R.id.template)).perform(RecyclerViewActions.actionOnItemAtPosition<ViewHolder>(0, ClickName()))
        onView(withText(R.string.rename)).inRoot(isDialog()).check(matches(isDisplayed()))
    }

    inner class TimeCheckBox: ViewAction {
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
    }
}