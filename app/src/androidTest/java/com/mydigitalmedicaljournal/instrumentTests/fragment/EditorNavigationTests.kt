package com.mydigitalmedicaljournal.instrumentTests.fragment

import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.RootMatchers.isDialog
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.activityScenarioRule
import com.mydigitalmedicaljournal.MainActivity
import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.instrumentTests.DummyTemplateFile
import com.mydigitalmedicaljournal.template.file.TemplateManager
import com.mydigitalmedicaljournal.ui._generics.ViewHolder
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class EditorNavigationTests {
    @get:Rule
    val activityScenarioRule = activityScenarioRule<MainActivity>()
    private lateinit var templateManager: TemplateManager
    private lateinit var dtf: DummyTemplateFile

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
        navigate()
    }

    @After
    fun teardown() {
        dtf.delete()
    }

    @Test
    fun name() {
        onView(withId(R.id.template)).perform(RecyclerViewActions.actionOnItemAtPosition<ViewHolder>(0, click()))
        onView(withId(R.id.name_text)).check(matches(isDisplayed()))
    }

    @Test
    fun time() {
        onView(withId(R.id.template)).perform(RecyclerViewActions.actionOnItemAtPosition<ViewHolder>(1, click()))
        onView(withId(R.id.time)).check(matches(isDisplayed()))
    }

    @Test
    fun newYesNo() {
        onView(withId(R.id.add)).perform(click())
        onView(withText(R.string.add_field)).inRoot(isDialog()).check(matches(isDisplayed()))
        onView(withId(R.id.recycler)).inRoot(isDialog()).perform(RecyclerViewActions.actionOnItemAtPosition<ViewHolder>(0, click()))
        onView(withId(R.id.yes_no)).check(matches(isDisplayed()))
    }

    @Test
    fun newMultiple() {
        onView(withId(R.id.add)).perform(click())
        onView(withText(R.string.add_field)).inRoot(isDialog()).check(matches(isDisplayed()))
        onView(withId(R.id.recycler)).inRoot(isDialog()).perform(RecyclerViewActions.actionOnItemAtPosition<ViewHolder>(1, click()))
        onView(withId(R.id.multiple)).check(matches(isDisplayed()))
    }

    @Test
    fun newSingle() {
        onView(withId(R.id.add)).perform(click())
        onView(withText(R.string.add_field)).inRoot(isDialog()).check(matches(isDisplayed()))
        onView(withId(R.id.recycler)).inRoot(isDialog()).perform(RecyclerViewActions.actionOnItemAtPosition<ViewHolder>(2, click()))
        onView(withId(R.id.single)).check(matches(isDisplayed()))
    }

    @Test
    fun newDescription() {
        onView(withId(R.id.add)).perform(click())
        onView(withText(R.string.add_field)).inRoot(isDialog()).check(matches(isDisplayed()))
        onView(withId(R.id.recycler)).inRoot(isDialog()).perform(RecyclerViewActions.actionOnItemAtPosition<ViewHolder>(3, click()))
        onView(withId(R.id.description)).check(matches(isDisplayed()))
    }

    @Test
    fun newSilder() {
        onView(withId(R.id.add)).perform(click())
        onView(withText(R.string.add_field)).inRoot(isDialog()).check(matches(isDisplayed()))
        onView(withId(R.id.recycler)).inRoot(isDialog()).perform(RecyclerViewActions.actionOnItemAtPosition<ViewHolder>(4, click()))
        onView(withId(R.id.slider)).check(matches(isDisplayed()))
    }

    @Test
    fun newValue() {
        onView(withId(R.id.add)).perform(click())
        onView(withText(R.string.add_field)).inRoot(isDialog()).check(matches(isDisplayed()))
        onView(withId(R.id.recycler)).inRoot(isDialog()).perform(RecyclerViewActions.actionOnItemAtPosition<ViewHolder>(5, click()))
        onView(withId(R.id.value)).check(matches(isDisplayed()))
    }

    //TODO test reordering
}