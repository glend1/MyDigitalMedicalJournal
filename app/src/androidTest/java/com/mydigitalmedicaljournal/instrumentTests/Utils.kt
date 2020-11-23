package com.mydigitalmedicaljournal.instrumentTests

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.platform.app.InstrumentationRegistry
import org.hamcrest.Description
import org.hamcrest.Matcher

class Utils {

    companion object {
        @JvmStatic
        val CONTEXT: Context = InstrumentationRegistry.getInstrumentation().targetContext

        fun atPosition(position: Int, itemMatcher: Matcher<View?>): Matcher<View?> {
            return object : BoundedMatcher<View?, RecyclerView>(RecyclerView::class.java) {
                override fun describeTo(description: Description) {
                    description.appendText("has item at position $position: ")
                    itemMatcher.describeTo(description)
                }

                override fun matchesSafely(view: RecyclerView): Boolean {
                    val viewHolder = view.findViewHolderForAdapterPosition(position)
                        ?: // has no item on such position
                        return false
                    return hasDescendant(itemMatcher).matches(viewHolder.itemView)
                }
            }
        }


    }

}