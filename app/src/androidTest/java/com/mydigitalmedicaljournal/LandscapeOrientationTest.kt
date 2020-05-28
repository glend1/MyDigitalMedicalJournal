package com.mydigitalmedicaljournal

import android.content.pm.ActivityInfo
import android.view.View
import androidx.fragment.app.FragmentContainerView
import androidx.test.rule.ActivityTestRule
import com.google.android.material.navigation.NavigationView
import junit.framework.TestCase.assertNotNull
import org.junit.Before

import org.junit.Rule
import org.junit.Test

class LandscapeOrientationTest {
    @get:Rule
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

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
    fun checkLandscapeOrientation() {
        val view = mActivityTestRule.activity.findViewById<View>(R.id.fragment_layout)
        assertNotNull(view)
    }

    @Test
    fun navigationPresent() {
        val view = mActivityTestRule.activity.findViewById<NavigationView>(R.id.nav_view)
        assertNotNull(view)
    }

    @Test
    fun contentPresent() {
        val view = mActivityTestRule.activity.findViewById<FragmentContainerView>(R.id.nav_host_fragment)
        assertNotNull(view)
    }

}