package com.mydigitalmedicaljournal.instrumentTests.dialog

import android.content.Context
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers.isDialog
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.activityScenarioRule
import com.mydigitalmedicaljournal.MainActivity
import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.template.file.TemplateList
import com.mydigitalmedicaljournal.ui._generics.dialogs.ListDialog
import com.mydigitalmedicaljournal.ui._generics.dialogs.input.DialogInputList
import com.mydigitalmedicaljournal.ui.categories.ManageCategoriesAdapter
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import java.util.*

class ListDialogTest {
    @get:Rule
    val activityScenarioRule = activityScenarioRule<MainActivity>()

    @Test
    fun listDialogTest() {
        var dialog: ListDialog?
        val testVal = "test2"
        val testUuid = UUID.fromString("93c9ca2a-627d-495d-a67f-e6e6c38afe3b")
        val list = listOf(
            TemplateList.FileList("test", UUID.fromString("f8145027-8a4d-4f9b-b4b5-2ebf049983e3")),
            TemplateList.FileList(testVal, testUuid),
            TemplateList.FileList("test3", UUID.fromString("99cb8d9a-26f1-4bd8-8959-84fba7367151"))
        )
        val adapter = ManageCategoriesAdapter(list, mutableListOf())
        activityScenarioRule.scenario.onActivity { activity: MainActivity? ->
            dialog = ListDialog("title", "message", activity as Context, adapter)
            dialog!!.setConfirm { _, _ -> }
            dialog!!.show()
        }
        onView(withId(DialogInputList.id)).inRoot(isDialog()).check(matches(hasChildCount(3)))
        onView(withText(testVal)).inRoot(isDialog()).perform(click())
        onView(withText(R.string.Yes)).inRoot(isDialog()).perform(click())
        assertEquals(adapter.localData[0], testUuid)
    }
}