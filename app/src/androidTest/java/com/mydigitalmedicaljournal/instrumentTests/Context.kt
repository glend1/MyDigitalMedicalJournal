package com.mydigitalmedicaljournal.instrumentTests

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry

class Context {
    companion object {
        @JvmStatic
        val CONTEXT: Context = InstrumentationRegistry.getInstrumentation().targetContext
    }
}