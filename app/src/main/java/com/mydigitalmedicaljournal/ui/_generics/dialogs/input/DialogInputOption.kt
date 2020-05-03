package com.mydigitalmedicaljournal.ui._generics.dialogs.input

import android.content.Context
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AlertDialog
import com.mydigitalmedicaljournal.R

class DialogInputOption(builder: AlertDialog.Builder, context: Context, data: Array<Int>) : AbstractDialogInput {
    private val spinner = Spinner(context)
    init {
        spinner.adapter = ArrayAdapter(context, R.layout.spinner_item, convertArray(context, data))
        builder.setView(spinner)
    }

    private fun convertArray(context: Context, data: Array<Int>): MutableList<String> {
        val vals = mutableListOf<String>()
        data.forEach { vals.add(context.getString(it)) }
        return vals
    }

    fun getSelected(): Int {
        return spinner.selectedItemPosition
    }
}
