package com.mydigitalmedicaljournal.instrumentTests.dialog

import android.content.Context
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers.isDialog
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.activityScenarioRule
import com.mydigitalmedicaljournal.MainActivity
import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.instrumentTests.DummyTemplates
import com.mydigitalmedicaljournal.ui.templates.dialog.NewTemplateDialog
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NewTemplateDialog {
    @get:Rule
    val activityScenarioRule = activityScenarioRule<MainActivity>()

    @Before
    fun setup() {
        activityScenarioRule.scenario.onActivity { activity: MainActivity? ->
            val context = activity as Context
            val newTemplate =
                NewTemplateDialog(
                    context.getString(R.string.new_template),
                    context.getString(R.string.new_template_instruction),
                    context,
                    activity.window.decorView.rootView as ViewGroup
                )
            newTemplate.setConfirm(R.string.save) { _, _ -> }
            newTemplate.show()
            newTemplate.disableAutoDismiss {
                if (newTemplate.save()) {
                    newTemplate.dismiss()
                    val bundle = bundleOf("data" to newTemplate.getTemplateId())
                    activity.findNavController(R.id.nav_host_fragment).navigate(R.id.editorFragment, bundle)
                }
            }
        }
    }

    @Test
    fun errors() {
        onView(withText(R.string.save)).inRoot(isDialog()).perform(click())
        onView(withId(R.id.error_name)).inRoot(isDialog()).check(matches(isDisplayed()))
        onView(withId(R.id.error_date)).inRoot(isDialog()).check(matches(isDisplayed()))
    }

    @Test
    fun success() {
        onView(withId(R.id.dialog_name)).inRoot(isDialog()).perform(typeText("hello"))
        onView(withId(R.id.date_time)).inRoot(isDialog()).perform(click())
        onView(withText(R.string.save)).inRoot(isDialog()).perform(click())
        onView(withText("Name : hello")).check(matches(isDisplayed()))
        onView(withText("Date : Date/Time")).check(matches(isDisplayed()))
        DummyTemplates(arrayOf("templates")).delete()
    }
}