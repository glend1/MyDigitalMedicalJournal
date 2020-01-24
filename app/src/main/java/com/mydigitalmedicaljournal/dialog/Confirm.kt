package com.mydigitalmedicaljournal.dialog

import android.content.Context
import android.content.DialogInterface
import android.view.View
import androidx.appcompat.app.AlertDialog


class Confirm(title: String, message: String, private val listener: DialogInterface.OnClickListener, context: Context) {

    private val builder = AlertDialog.Builder(context)
    private lateinit var dialog: AlertDialog

    init {
        create(title, message)
    }

    private fun create(title: String, message: String) {
        // Set the alert dialog title
        builder.setTitle(title)

        // Display a message on alert dialog
        builder.setMessage(message)

        // Set a positive button and its click listener on alert dialog
        val click: View.OnClickListener
        builder.setPositiveButton("Yes", listener)

        // Display a negative button on alert dialog
        builder.setNegativeButton("No", null)

        // Display a neutral button on alert dialog
        builder.setNeutralButton("Cancel", null)

        // Finally, make the alert dialog using builder
        dialog = builder.create()
    }

    fun show() {
        // Display the alert dialog on app interface
        dialog.show()
    }
}