package com.mydigitalmedicaljournal.ui._generics.dialogs.input

import android.content.Context
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AlertDialog
import com.mydigitalmedicaljournal.R

class DialogInputOption(builder: AlertDialog.Builder, context : Context, data: MutableList<String>) : AbstractDialogInput {
    private val spinner = Spinner(context)
    init {
        spinner.adapter = ArrayAdapter(context, R.layout.spinner_item, data)
        builder.setView(spinner)
    }

    fun getSelected(): Int {
        return spinner.selectedItemPosition
    }
}
