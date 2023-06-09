package com.mydigitalmedicaljournal.instrumentTests

import android.content.Context
import android.content.res.Resources
import android.os.IBinder
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Root
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import com.mydigitalmedicaljournal.MainActivity
import com.mydigitalmedicaljournal.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher


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

        fun withDrawable(resourceId: Int): Matcher<View?> {
            return object : TypeSafeMatcher<View?>() {
                override fun describeTo(description: Description) {
                    description.appendText("with drawable from resource id: $resourceId")
                }

                override fun matchesSafely(view: View?): Boolean {
                    try {
                        val resourcesDrawable = view?.resources?.getDrawable(resourceId, null)
                        if (view is ImageView) {
                            val imageView: ImageView = view
                            return if (imageView.drawable == null) {
                                resourcesDrawable == null
                            } else imageView.drawable.constantState!! == resourcesDrawable!!.constantState
                        }
                    } catch (ignored: Resources.NotFoundException) {
                    }
                    return false
                }
            }
        }

    }

    class ClickImage(private val id: Int): ViewAction {
        override fun getDescription(): String {
            return "button not clicked"
        }

        override fun getConstraints(): Matcher<View> {
            return ViewMatchers.withId(id)
        }

        override fun perform(uiController: UiController?, view: View?) {
            view!!.findViewById<ImageView>(id).performClick()
        }
    }

    class Navigation(private val activityScenarioRule: ActivityScenarioRule<MainActivity>) {
        fun navigate(fragment: Int) {
            activityScenarioRule.scenario.onActivity { activity ->
                val nav = activity.findNavController(R.id.nav_host_fragment)
                nav.navigate(fragment)
            }
        }
    }

    class ActivityMatcher : TypeSafeMatcher<Root>() {
        override fun describeTo(description: Description) {
            description.appendText("is activity")
        }

        public override fun matchesSafely(root: Root): Boolean {
            val type: Int = root.windowLayoutParams.get().type
            if (type == WindowManager.LayoutParams.TYPE_BASE_APPLICATION) {
                val windowToken: IBinder = root.decorView.windowToken
                val appToken: IBinder = root.decorView.applicationWindowToken
                if (windowToken === appToken) {
                    //means this window isn't contained by any other windows.
                    return true
                }
            }
            return false
        }
    }

}