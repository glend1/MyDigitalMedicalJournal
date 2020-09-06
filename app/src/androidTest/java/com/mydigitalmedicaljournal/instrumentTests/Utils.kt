package com.mydigitalmedicaljournal.instrumentTests

import android.view.View
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import com.mydigitalmedicaljournal.MainActivity
import com.mydigitalmedicaljournal.R
import junit.framework.TestCase.assertTrue
import org.hamcrest.Description
import org.hamcrest.Matcher

class Utils {

    companion object {
        fun atPosition(position: Int, itemMatcher: Matcher<View?>): Matcher<View?>? {
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

        fun isvisible(pos: Int, view: Int): (activity: MainActivity) -> Unit {
            return { activity ->
                val rv = activity.findViewById<RecyclerView>(R.id.template)
                val v = rv.findViewHolderForAdapterPosition(pos)!!.itemView
                val error = v.findViewById<TextView>(view)
                assertTrue(error.isVisible)
            }
        }
    }

}