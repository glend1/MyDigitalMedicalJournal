package com.mydigitalmedicaljournal.ui._generics.dialogs

import android.content.Context
import android.content.DialogInterface
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AlertDialog
import com.mydigitalmedicaljournal.R

class OptionDialog(title: String, message: String, data: MutableList<String>, val context: Context) {

    //TODO this uses default styling
    private val builder = AlertDialog.Builder(context)
    private lateinit var dialog: AlertDialog
    private var spinner = Spinner(context)

    init {
        // Set the alert dialog title
        builder.setTitle(title)

        // Display a message on alert dialog
        builder.setMessage(message)
        //builder.setMessage(text)

        spinner.adapter = ArrayAdapter(context, R.layout.spinner_item, data)
        builder.setView(spinner)

        // Display a negative button on alert dialog
        //builder.setNegativeButton("No", null)

        // Display a neutral button on alert dialog
        builder.setNeutralButton(context.getString(R.string.Cancel), null)
    }

    fun setListener(listener: DialogInterface.OnClickListener) {
        // Set a positive button and its click listener on alert dialog
        builder.setPositiveButton(context.getString(R.string.Yes), listener)
        // Finally, make the alert dialog using builder
        dialog = builder.create()
    }

    fun show() {
        // Display the alert dialog on app interface
        dialog.show()
    }

    fun getSelected(): Int {
        return spinner.selectedItemPosition
    }
}