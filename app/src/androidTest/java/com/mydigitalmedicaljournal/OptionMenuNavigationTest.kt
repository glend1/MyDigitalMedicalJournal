package com.mydigitalmedicaljournal

/*import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.rule.ActivityTestRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class OptionMenuNavigationTest {
    @get:Rule
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    //TODO all of these tests use a string name, that's frail
    @Before
    fun openOptionsMenu() {
        openActionBarOverflowOrOptionsMenu(getInstrumentation().targetContext)
    }

    @Test
    fun openOptions() {
        Espresso.onView(ViewMatchers.withText("Settings")).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun openSettings() {
        Espresso.onView(ViewMatchers.withText("Settings")).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withText("Measures"))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun openSupport() {
        Espresso.onView(ViewMatchers.withText("Support")).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.text_support))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun openSocial() {
        Espresso.onView(ViewMatchers.withText("Social")).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.text_social))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun openInstructions() {
        Espresso.onView(ViewMatchers.withText("Instructions")).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.text_instructions))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun openCopyright() {
        Espresso.onView(ViewMatchers.withText("Copyright")).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.text_copyright))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}*/