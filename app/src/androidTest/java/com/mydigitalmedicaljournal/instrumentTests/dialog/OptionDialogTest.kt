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
import com.mydigitalmedicaljournal.ui._generics.dialogs.OptionDialog
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class OptionDialogTest {
    @get:Rule
    val activityScenarioRule = activityScenarioRule<MainActivity>()

    @Test
    fun optionDialogTest() {
        //TODO this fails sometimes, i don't know why
        val selected = R.string.manage_categories
        var dialog: OptionDialog? = null
        activityScenarioRule.scenario.onActivity { activity: MainActivity? ->
            dialog = OptionDialog("test", "test", arrayOf(R.string.drawer_categories,selected), activity as Context)
            dialog!!.setConfirm { _, _ -> }
            dialog!!.show()
        }
        onView(withId(R.id.custom)).perform(click())
        onView(withText(R.string.manage_categories)).inRoot(isPlatformPopup()).perform(click())
        onView(withText(R.string.Yes)).inRoot(isDialog()).perform(click())
        assertEquals(dialog!!.getSelected(), selected)
    }
    
}