package com.mydigitalmedicaljournal

import android.content.pm.ActivityInfo
import android.view.Gravity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.DrawerActions
import androidx.test.espresso.contrib.DrawerMatchers.isClosed
import androidx.test.espresso.contrib.DrawerMatchers.isOpen
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import org.junit.Rule
import org.junit.Test

class GlobalTests {
    @get:Rule
    var activityScenarioRule = activityScenarioRule<MainActivity>()

    //TODO this opens the options menu
    //TODO wish this was more specific
    @Test
    fun openOptionsMenu() {
        openActionBarOverflowOrOptionsMenu(getInstrumentation().targetContext)
        onView(withText("Settings")).check(matches(isDisplayed()))
    }

}

//TODO below is an example of how robolectric works, i think it needs to be placed in local tests
/*import android.os.Build
import android.widget.TextView
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.launchActivity
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config


@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.P])
class RoboTest {
    @get:Rule
    var activityScenarioRule = activityScenarioRule<MainActivity>()


    @Test
    fun test() {
        //nav.navigate(R.id.nav_social)
        //Thread.sleep(10000)
        //val view = activity.findViewById<TextView>(R.id.text_journal)
        //assertNotNull("navigation didn't happen", view)
    }
}*/