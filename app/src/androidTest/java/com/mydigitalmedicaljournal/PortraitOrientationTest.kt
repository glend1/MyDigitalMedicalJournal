package com.mydigitalmedicaljournal

import android.content.pm.ActivityInfo
import android.view.Gravity
import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.DrawerActions
import androidx.test.espresso.contrib.DrawerMatchers.isClosed
import androidx.test.espresso.contrib.DrawerMatchers.isOpen
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import junit.framework.TestCase.assertNotNull
import org.hamcrest.Matchers
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PortraitOrientationTest {
    @get:Rule
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun forceOrientation() {
        mActivityTestRule.activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        //TODO would like a way where i don't have to sleep here
        try {
            Thread.sleep(1000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }

    @Test
    fun checkPortraitOrientation() {
        val view = mActivityTestRule.activity.findViewById<View>(R.id.drawer_layout)
        assertNotNull(view)
    }

    @Test
    fun drawerClosed() {
        onView(withId(R.id.drawer_layout)).check(matches(isClosed(Gravity.LEFT)))
    }

    @Test
    fun drawerOpen() {
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open())
        onView(withId(R.id.drawer_layout)).check(matches(isOpen(Gravity.LEFT)))
    }

    //TODO this test uses a string name, that's frail
    @Test
    fun navigate() {
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open())
        onView(
            Matchers.allOf(
                withId(R.id.design_menu_item_text),
                ViewMatchers.withText("Reports")
            )).perform(ViewActions.click())
        onView(withId(R.id.text_reports))
            .check(matches(ViewMatchers.isDisplayed()))
    }
}