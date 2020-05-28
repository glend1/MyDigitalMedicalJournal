package com.mydigitalmedicaljournal

import android.content.pm.ActivityInfo
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.rule.ActivityTestRule
import org.hamcrest.Matchers
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainNavigationTest {
    @get:Rule
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    //TODO all of these tests use a string name, that's frail
    @Before
    fun forceOrientation() {
        mActivityTestRule.activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        //TODO would like a way where i don't have to sleep here
        try {
            Thread.sleep(1000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }

    @Test
    fun openJournal() {
        Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.design_menu_item_text),
                ViewMatchers.withText("Journal")
            )
        )
            .perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.text_journal))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun openReports() {
        Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.design_menu_item_text),
                ViewMatchers.withText("Reports")
            )
        )
            .perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.text_reports))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun openCalendar() {
        Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.design_menu_item_text),
                ViewMatchers.withText("Calendar")
            )
        )
            .perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.text_calendar))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun openTemplates() {
        Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.design_menu_item_text),
                ViewMatchers.withText("Templates")
            )
        )
            .perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.template_list))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

}