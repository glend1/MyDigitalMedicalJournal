package com.mydigitalmedicaljournal.instrumentTests.fragment

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
import com.mydigitalmedicaljournal.MainActivity
import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.instrumentTests.DummyCategories
import com.mydigitalmedicaljournal.instrumentTests.DummyTemplateFile
import com.mydigitalmedicaljournal.ui._generics.ViewHolder
import org.hamcrest.Matcher
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class CategoryTests {
    @get:Rule
    val activityScenarioRule = activityScenarioRule<MainActivity>()
    private val dc = DummyCategories("categories.json")

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
        onView(withId(R.id.category_recycler)).perform(actionOnItemAtPosition<ViewHolder>(0, ClickRename()))
        onView(withText(R.string.rename)).inRoot(isDialog()).check(matches(isDisplayed()))
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
        val pathArray = arrayOf("templates")
        val testTemplate = "lmno template"
        val file1 = DummyTemplateFile("68aa63ff-1e34-49fd-afbd-bffecf95685c", "abc template", pathArray).get()
        val file2 = DummyTemplateFile("b132f1ab-d50b-4f84-a87e-bfbcadf91281", testTemplate, pathArray).get()
        val file3 = DummyTemplateFile("1c57893f-7f6c-4b8a-bcd2-97ca5d4cdc09", "xyz template", pathArray).get()
        onView(withId(R.id.category_recycler)).perform(actionOnItemAtPosition<ViewHolder>(1, ClickManage()))
        onView(withText(testTemplate)).inRoot(isDialog()).perform(click())
        onView(withText(R.string.Yes)).inRoot(isDialog()).perform(click())
        onView(withId(R.id.category_recycler)).perform(actionOnItemAtPosition<ViewHolder>(1, ClickManage()))
        onView(withText(testTemplate)).inRoot(isDialog()).check(matches(isNotChecked()))
        file1.delete()
        file2.delete()
        file3.delete()
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
        onView(withId(R.id.category_recycler)).perform(actionOnItemAtPosition<ViewHolder>(0, ClickDelete()))
        onView(withText(R.string.Confirm_Category_Warning)).inRoot(isDialog()).check(matches(isDisplayed()))
    }
}

