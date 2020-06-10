package com.mydigitalmedicaljournal

import android.os.Build
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config


@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.P])
class RoboTest {

    @Test
    fun test() {
        val activity: MainActivity = Robolectric.buildActivity(MainActivity::class.java).get()
        Thread.sleep(9000)
        //val activity = Robolectric.buildActivity(MainActivity::class.java)
        //activity.findViewById(R.id.login).performClick()

        //val expectedIntent = Intent(activity, LoginActivity::class.java)
        //val actual: Intent = shadowOf(RuntimeEnvironment.application).getNextStartedActivity()
        //assertEquals(expectedIntent.component, actual.component)
    }
}