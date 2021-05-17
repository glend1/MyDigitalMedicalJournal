package com.mydigitalmedicaljournal.instrumentTests.fragment

import androidx.navigation.findNavController
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.activityScenarioRule
import com.mydigitalmedicaljournal.MainActivity
import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.instrumentTests.DummyCategories
import com.mydigitalmedicaljournal.instrumentTests.DummyCategoriesAndTemplates
import com.mydigitalmedicaljournal.instrumentTests.DummyTemplates
import com.mydigitalmedicaljournal.ui._generics.ViewHolder
import org.hamcrest.Matchers.not
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SelectorTests {
    @get:Rule
    val activityScenarioRule = activityScenarioRule<MainActivity>()
    private var dct = DummyCategoriesAndTemplates("categories.json", arrayOf("templates"))

    @Before
    fun setup() {
        dct.setup()
        activityScenarioRule.scenario.onActivity { activity ->
            val nav = activity.findNavController(R.id.nav_host_fragment)
            nav.navigate(R.id.templateSelector)
        }
    }

    @After
    fun teardown() {
        dct.delete()
    }

    @Test
    fun getCount() {
        onView(withId(R.id.selector_recycler)).check(matches(hasChildCount(9)))
    }

    @Test
    fun getType() {
        onView(withId(R.id.selector_recycler)).check(matches(hasDescendant(withText(DummyCategories.data[0].name))))
        onView(withId(R.id.selector_recycler)).check(matches(hasDescendant(withText(DummyTemplates.data[0][1]))))
    }

    @Test
    fun categoryNavigate() {
        onView(withId(R.id.selector_recycler)).perform(actionOnItemAtPosition<ViewHolder>(0, click()))
        onView(withId(R.id.selector_recycler)).check(matches(isDisplayed()))
    }

    @Test
    fun templateNavigateWILLFAIL() {
        //TODO finish this test when data entry is supported
        onView(withId(R.id.selector_recycler)).perform(actionOnItemAtPosition<ViewHolder>(1, click()))
        onView(withId(R.id.selector_recycler)).check(matches(not(isDisplayed())))
    }
    
    @Test
    fun filterCategory() {
        onView(withId(R.id.search_view)).perform(typeText("dog"), closeSoftKeyboard())
        onView(withId(R.id.selector_recycler)).check(matches(hasChildCount(2)))
    }

    @Test
    fun filterTemplate() {
        onView(withId(R.id.search_view)).perform(typeText("another"), closeSoftKeyboard())
        onView(withId(R.id.selector_recycler)).check(matches(hasChildCount(2)))
    }

    @Test
    fun filterClear() {
        onView(withId(R.id.search_view)).perform(typeText("test"), closeSoftKeyboard())
        onView(withId(R.id.clear_button)).perform(click())
        onView(withId(R.id.selector_recycler)).check(matches(hasChildCount(9)))
    }
}