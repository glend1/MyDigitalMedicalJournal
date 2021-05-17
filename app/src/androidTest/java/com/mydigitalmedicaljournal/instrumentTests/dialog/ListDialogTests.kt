package com.mydigitalmedicaljournal.instrumentTests.dialog

import android.content.Context
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers.isDialog
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.activityScenarioRule
import com.mydigitalmedicaljournal.MainActivity
import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.instrumentTests.Utils
import com.mydigitalmedicaljournal.template.TemplateEnum
import com.mydigitalmedicaljournal.ui._generics.dialogs.ListDialog
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ListDialogTests {
    @get:Rule
    val activityScenarioRule = activityScenarioRule<MainActivity>()

    @Before
    fun setup() {
        activityScenarioRule.scenario.onActivity { activity: MainActivity? ->
            val context = activity as Context
            val dialog = ListDialog(
                context.getString(R.string.add_field),
                context.getString(R.string.add_field_text),
                TemplateEnum.namedString,
                context,
                activity.window.decorView.rootView as ViewGroup
            )
            dialog.setListener(fun (pos: Int) {
                /*val bundle = bundleOf("type" to TemplateEnum.nameList[pos], "filename" to templateManager!!.getId())
                root.findNavController().navigate(R.id.fieldEditorFragment, bundle)*/
                dialog.dismiss()
            })
            dialog.show()
        }
    }

    @Test
    fun listVisible() {
        onView(withText(R.string.simple)).inRoot(isDialog()).check(matches(isDisplayed()))
    }

    @Test
    fun dismissedWhenClicked() {
        onView(withText(R.string.simple)).inRoot(isDialog()).perform(click())
        onView(withId(R.id.fragment_layout)).inRoot(Utils.ActivityMatcher()).check(matches(isDisplayed()))
    }
}