package com.mydigitalmedicaljournal.dialog

import android.content.Context
import android.content.DialogInterface
import android.text.InputType
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AlertDialog


class TextBox(title: String, message: String, text: String, private val listener: DialogInterface.OnClickListener, context: Context) {

    private val builder = AlertDialog.Builder(context)
    private lateinit var dialog: AlertDialog
    private var input = EditText(context)

    init {
        create(title, message, text)
    }

    private fun create(title: String, message: String, text: String) {
        // Set the alert dialog title
        builder.setTitle(title)

        // Display a message on alert dialog
        builder.setMessage(message)

        input.inputType = InputType.TYPE_CLASS_TEXT
        input.setText(text)
        input.selectAll()
        builder.setView(input)

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

    fun getText(): String {
        return input.text.toString()
    }
}