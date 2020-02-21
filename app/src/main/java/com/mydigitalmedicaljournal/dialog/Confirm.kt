package com.mydigitalmedicaljournal.dialog

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog


class Confirm(title: String, message: String, context: Context) {

    private val builder = AlertDialog.Builder(context)
    private lateinit var dialog: AlertDialog

    init {
        // Set the alert dialog title
        builder.setTitle(title)

        // Display a message on alert dialog
        builder.setMessage(message)

        // Display a negative button on alert dialog
        //builder.setNegativeButton("No", null)

        // Display a neutral button on alert dialog
        builder.setNeutralButton("Cancel", null)


    }

    fun setListener(listener: DialogInterface.OnClickListener) {
        // Set a positive button and its click listener on alert dialog
        builder.setPositiveButton("Yes", listener)

        // Finally, make the alert dialog using builder
        dialog = builder.create()
    }

    fun show() {
        // Display the alert dialog on app interface
        dialog.show()
    }
}