package com.mydigitalmedicaljournal.instrumentTests.fragment

import android.text.InputType.TYPE_NUMBER_FLAG_SIGNED
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.RootMatchers.isDialog
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.activityScenarioRule
import com.mydigitalmedicaljournal.MainActivity
import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.instrumentTests.DummyTemplateFile
import com.mydigitalmedicaljournal.instrumentTests.Utils
import com.mydigitalmedicaljournal.template.file.TemplateManager
import com.mydigitalmedicaljournal.ui._generics.ViewHolder
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class EditorSliderTests {
    @get:Rule
    val activityScenarioRule = activityScenarioRule<MainActivity>()
    private lateinit var templateManager: TemplateManager
    private lateinit var dtf: DummyTemplateFile

    fun navigate() {
        activityScenarioRule.scenario.onActivity { activity ->
            val bundle = bundleOf("data" to templateManager.getId())
            activity.findNavController(R.id.nav_host_fragment).navigate(R.id.editorFragment, bundle)
        }
        onView(withId(R.id.add)).perform(click())
        onView(withText(R.string.add_field)).inRoot(isDialog()).check(matches(isDisplayed()))
        onView(withId(R.id.recycler)).inRoot(isDialog()).perform(actionOnItemAtPosition<ViewHolder>(4, click()))
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
    fun sliderSave() {
        val minTest = "5"
        val maxTest = "10"
        onView(withId(R.id.editText)).perform(typeText("question"))
        onView(withId(R.id.minimum)).perform(typeText(minTest))
        onView(withId(R.id.maximum)).perform(typeText(maxTest))
        onView(withId(R.id.save)).perform(click())
        onView(withId(R.id.template)).perform(actionOnItemAtPosition<ViewHolder>(2, click()))
        onView(withId(R.id.minimum)).check(matches(withText(minTest)))
        onView(withId(R.id.maximum)).check(matches(withText(maxTest)))
    }

    @Test
    fun noVals() {
        onView(withId(R.id.save)).perform(click())
        onView(withText(Utils.CONTEXT.getString(R.string.not_found, Utils.CONTEXT.getString(R.string.minimum)))).check(matches(isDisplayed()))
        onView(withText(Utils.CONTEXT.getString(R.string.not_found, Utils.CONTEXT.getString(R.string.maximum)))).check(matches(isDisplayed()))
    }
    //can only test one from now on because they use the same code

    @Test
    fun valTooBig() {
        onView(withId(R.id.minimum)).perform(typeText("1001"))
        onView(withId(R.id.save)).perform(click())
        onView(withText(Utils.CONTEXT.getString(R.string.too_big, Utils.CONTEXT.getString(R.string.minimum)))).check(matches(isDisplayed()))
    }

    @Test
    fun valTooSmall() {
        activityScenarioRule.scenario.onActivity { activity ->
            activity.findViewById<EditText>(R.id.minimum).inputType = TYPE_NUMBER_FLAG_SIGNED
        }
        onView(withId(R.id.minimum)).perform(typeText("-1"))
        onView(withId(R.id.save)).perform(click())
        onView(withText(Utils.CONTEXT.getString(R.string.too_small, Utils.CONTEXT.getString(R.string.minimum)))).check(matches(isDisplayed()))
    }

    @Test
    fun invalidRange() {
        onView(withId(R.id.minimum)).perform(typeText("10"))
        onView(withId(R.id.maximum)).perform(typeText("5"))
        onView(withId(R.id.save)).perform(click())
        onView(withText(R.string.min_max_not_valid)).check(matches(isDisplayed()))
    }

    @Test
    fun labelSaveTest() {
        onView(withId(R.id.editText)).perform(typeText("question"))
        onView(withId(R.id.minimum)).perform(typeText("1"))
        onView(withId(R.id.maximum)).perform(typeText("10"))
        onView(withId(R.id.addLabel)).perform(click())
        onView(withId(R.id.addLabel)).perform(click())
        val appCompatEditText2 = onView(
            allOf(
                withId(R.id.value_input),
                childAtPosition(
                    allOf(
                        withId(R.id.label_item),
                        childAtPosition(
                            withClassName(Matchers.`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                            0
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatEditText2.perform(ViewActions.replaceText("4"), ViewActions.closeSoftKeyboard())
        onView(withId(R.id.addLabel)).perform(click())
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}