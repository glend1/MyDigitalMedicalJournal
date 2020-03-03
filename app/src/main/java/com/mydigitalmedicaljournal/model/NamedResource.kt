package com.mydigitalmedicaljournal.model

import android.content.Context

data class NamedResource(private val nameResource: Int, val resource: Int?) {
    fun getName (context: Context) : String {
        return context.resources.getString(nameResource)
    }
}
