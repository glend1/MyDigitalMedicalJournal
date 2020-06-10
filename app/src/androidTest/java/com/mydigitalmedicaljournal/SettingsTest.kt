package com.mydigitalmedicaljournal

/*import org.junit.Test
import java.lang.Boolean.TRUE

class SettingsTest {

    //TODO there's nothing really being tested here, because it assumes android isn't working
    //keeping the code i had in case i need it later on

    /*@get:Rule
    //this should really open the settings directly
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)*/

    //this before uses a string name, that's frail
    /*@Before
    fun openOptionsMenu() {
        Espresso.openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getInstrumentation().targetContext)
        Espresso.onView(ViewMatchers.withText("Settings")).perform(ViewActions.click())
    }*/

    /*@Before
    fun openFragment() {
        //this isn't the whole code, im just leaving this here as a reminder
        mActivityTestRule.activity.findNavController().navigate(R.id.nav_settings)
        Thread.sleep(6000)
    }*/

    /*@Before
    fun openFragment() {
        launchFragmentInContainer<SettingsFragment>()
    }

    @Test
    fun changeWeight() {
        //Espresso.onView(ViewMatchers.withText("Weight")).check(matches(isDisplayed()))
        Espresso.onView(ViewMatchers.withText("Weight")).perform(ViewActions.click())
        //Espresso.onView(ViewMatchers.withText("Pounds (lb)")).inRoot(isDialog()).check(matches(isDisplayed()))
    }*/

    @Test
    fun noTest() {
        assert(TRUE)
    }
}*/