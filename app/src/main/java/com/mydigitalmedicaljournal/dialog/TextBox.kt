package com.mydigitalmedicaljournal.dialog

import android.content.Context
import android.content.DialogInterface
import android.text.InputType
import android.widget.EditText
import androidx.appcompat.app.AlertDialog


class TextBox(title: String, message: String, text: String, listener: DialogInterface.OnClickListener, context: Context) {

    private val builder = AlertDialog.Builder(context)
    private var dialog: AlertDialog
    private var input = EditText(context)

    init {
        // Set the alert dialog title
        builder.setTitle(title)

        // Display a message on alert dialog
        builder.setMessage(message)
        //builder.setMessage(text)

        input.inputType = InputType.TYPE_CLASS_TEXT
        input.setText(text)
        input.selectAll()
        //TODO this line deletes the parent view
        //this happens occurs on all views showing position 1
        builder.setView(input)

        // Set a positive button and its click listener on alert dialog
        builder.setPositiveButton("Yes", listener)

        // Display a negative button on alert dialog
        //builder.setNegativeButton("No", null)

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