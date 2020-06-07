package com.mydigitalmedicaljournal

import androidx.navigation.testing.TestNavHostController
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test

class NavControllerTest {
    @get:Rule
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun navigate() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext.applicationContext
        val navController = TestNavHostController(context)
        //val navController = TestNavHostController(mActivityTestRule.activity.baseContext)
        navController.setGraph(R.navigation.mobile_navigation)
        //navController.navigate(R.id.manageCategories)
        Thread.sleep(9000)
        //val navController = findNavController(mActivityTestRule, R.id.nav_host_fragment)
    }
}