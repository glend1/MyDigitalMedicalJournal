package com.mydigitalmedicaljournal

import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.RootMatchers.isDialog
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.activityScenarioRule
import com.mydigitalmedicaljournal.ui._generics.ViewHolder
import org.hamcrest.Matcher
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class CategoryTest {
    @get:Rule
    val activityScenarioRule = activityScenarioRule<MainActivity>()

    @Before
    fun setup() {
        activityScenarioRule.scenario.onActivity { activity ->
            val nav = activity.findNavController(R.id.nav_host_fragment)
            nav.navigate(R.id.nav_categories)
        }
    }

    @Test
    fun add() {
        onView(withId(R.id.add)).perform(click())
        onView(withText(R.string.New)).inRoot(isDialog()).check(matches(isDisplayed()))
    }

    inner class ClickRename: ViewAction {
        override fun getDescription(): String {
            return "rename was not clicked"
        }

        override fun getConstraints(): Matcher<View> {
            return withId(R.id.rename)
        }

        override fun perform(uiController: UiController?, view: View?) {
            view?.findViewById<ConstraintLayout>(R.id.rename)?.performClick()
        }

    }

    @Test
    fun rename() {
        onView(withId(R.id.category_recycler)).perform(actionOnItemAtPosition<ViewHolder>(0, ClickRename()))
        onView(withText(R.string.rename)).inRoot(isDialog()).check(matches(isDisplayed()))
    }

    inner class ClickManage: ViewAction {
        override fun getDescription(): String {
            return "manage was not clicked"
        }

        override fun getConstraints(): Matcher<View> {
            return withId(R.id.manage)
        }

        override fun perform(uiController: UiController?, view: View?) {
            view?.findViewById<ConstraintLayout>(R.id.manage)?.performClick()
        }

    }

    @Test
    fun manage() {
        onView(withId(R.id.category_recycler)).perform(actionOnItemAtPosition<ViewHolder>(0, ClickManage()))
        onView(withText(R.string.manage)).inRoot(isDialog()).check(matches(isDisplayed()))
    }

    inner class ClickDelete: ViewAction {
        override fun getDescription(): String {
            return "delete was not clicked"
        }

        override fun getConstraints(): Matcher<View> {
            return withId(R.id.delete)
        }

        override fun perform(uiController: UiController?, view: View?) {
            view?.findViewById<ConstraintLayout>(R.id.delete)?.performClick()
        }

    }

    @Test
    fun delete() {
        onView(withId(R.id.category_recycler)).perform(actionOnItemAtPosition<ViewHolder>(0, ClickDelete()))
        onView(withText(R.string.Confirm_Category_Warning)).inRoot(isDialog()).check(matches(isDisplayed()))
    }
}

