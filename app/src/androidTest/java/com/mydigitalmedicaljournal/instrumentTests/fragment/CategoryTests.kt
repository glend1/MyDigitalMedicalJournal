package com.mydigitalmedicaljournal.instrumentTests.fragment

import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.RootMatchers.isDialog
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.activityScenarioRule
import com.mydigitalmedicaljournal.MainActivity
import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.instrumentTests.DummyCategories
import com.mydigitalmedicaljournal.instrumentTests.DummyTemplates
import com.mydigitalmedicaljournal.ui._generics.ViewHolder
import org.hamcrest.Matcher
import org.hamcrest.Matchers.not
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CategoryTests {
    @get:Rule
    val activityScenarioRule = activityScenarioRule<MainActivity>()
    private val dc = DummyCategories("categories.json")
    private val testCategory = "cat"

    @Before
    fun setup() {
        dc.get()
        activityScenarioRule.scenario.onActivity { activity ->
            val nav = activity.findNavController(R.id.nav_host_fragment)
            nav.navigate(R.id.nav_categories)
        }
    }

    @After
    fun teardown() {
        dc.delete()
    }

    @Test
    fun addDialog() {
        onView(withId(R.id.add)).perform(click())
        onView(withText(R.string.New)).inRoot(isDialog()).check(matches(isDisplayed()))
    }

    class ClickRename: ViewAction {
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
    fun renameDialog() {
        val newName = "rabbit"
        onView(withId(R.id.category_recycler)).perform(actionOnItemAtPosition<ViewHolder>(1, ClickRename()))
        onView(withText(testCategory)).inRoot(isDialog()).perform(replaceText(newName))
        onView(withText(R.string.Yes)).inRoot(isDialog()).perform(click())
        onView(withId(R.id.category_recycler)).check(matches(hasDescendant(withText(newName))))
    }

    class ClickManage: ViewAction {
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
    fun noFilesManage() {
        onView(withId(R.id.category_recycler)).perform(actionOnItemAtPosition<ViewHolder>(0, ClickManage()))
        onView(withText(R.string.no_templates)).inRoot(isDialog()).check(matches(isDisplayed()))
    }

    @Test
    fun changeManage() {
        val dt = DummyTemplates(arrayOf("templates"))
        dt.get()
        onView(withId(R.id.category_recycler)).perform(actionOnItemAtPosition<ViewHolder>(1, ClickManage()))
        onView(withText(DummyTemplates.data[1][1])).inRoot(isDialog()).perform(click())
        onView(withText(R.string.Yes)).inRoot(isDialog()).perform(click())
        onView(withId(R.id.category_recycler)).perform(actionOnItemAtPosition<ViewHolder>(1, ClickManage()))
        onView(withText(DummyTemplates.data[1][1])).inRoot(isDialog()).check(matches(isChecked()))
        dt.delete()
    }

    class ClickDelete: ViewAction {
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
    fun deleteDialog() {
        onView(withId(R.id.category_recycler)).perform(actionOnItemAtPosition<ViewHolder>(1, ClickDelete()))
        onView(withText(R.string.Yes)).inRoot(isDialog()).perform(click())
        onView(withId(R.id.category_recycler)).check(matches(not(hasDescendant(withText(testCategory)))))
    }

    @Test
    fun displayNumber() {
        onView(withId(R.id.category_recycler)).check(matches(hasChildCount(DummyCategories.data.size)))
    }
}

