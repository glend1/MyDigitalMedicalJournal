package com.mydigitalmedicaljournal.instrumentTests.dialog

import android.content.Context
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.RootMatchers.isDialog
import androidx.test.espresso.matcher.RootMatchers.isPlatformPopup
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.activityScenarioRule
import com.mydigitalmedicaljournal.MainActivity
import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.ui._generics.dialogs.SpinnerDialog
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class OptionDialogTest {
    //TODO delete this if its not used
    @get:Rule
    val activityScenarioRule = activityScenarioRule<MainActivity>()

    @Test
    fun optionDialogTestUNUSED() {
        val selected = R.string.manage_categories
        var dialog: SpinnerDialog? = null
        activityScenarioRule.scenario.onActivity { activity: MainActivity? ->
            dialog = SpinnerDialog("test", "test", arrayOf(R.string.drawer_categories,selected), activity as Context)
            dialog!!.setConfirm { _, _ -> }
            dialog!!.show()
        }
        onView(withId(R.id.custom)).inRoot(isDialog()).perform(click())
        onView(withText(R.string.manage_categories)).inRoot(isPlatformPopup()).perform(click())
        onView(withText(R.string.Yes)).inRoot(isDialog()).perform(click())
        assertEquals(dialog!!.getSelected(), selected)
        assertEquals(true, false)
    }
    
}