package com.mydigitalmedicaljournal.ui._generics

import android.content.Context
import android.content.DialogInterface
import android.text.InputType
import android.widget.EditText
import androidx.appcompat.app.AlertDialog


class TextBoxDialog(title: String, message: String, text: String, context: Context) {

    //TODO this uses default styling
    private val builder = AlertDialog.Builder(context)
    private lateinit var dialog: AlertDialog
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
        builder.setView(input)

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

    fun getText(): String {
        return input.text.toString()
    }
}