package com.mydigitalmedicaljournal.ui._generics.dialogs

import android.content.Context
import android.content.DialogInterface
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.mydigitalmedicaljournal.R

class OptionDialog(title: String, message: String, data: MutableList<String>, context: Context) : Dialog(title, message, context) {

    private val spinner = Spinner(context)

    init {
        setInput(data)
        setCancel()
    }

    private fun setCancel() {
        builder.setNeutralButton(context.getString(R.string.Cancel), null)
    }

    private fun setInput(data: MutableList<String>) {
        spinner.adapter = ArrayAdapter(context, R.layout.spinner_item, data)
        builder.setView(spinner)
    }

    fun getSelected(): Int {
        return spinner.selectedItemPosition
    }
}