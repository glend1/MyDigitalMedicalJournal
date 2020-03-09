package com.mydigitalmedicaljournal.ui._generics

import android.content.Context
import android.content.DialogInterface
import android.text.InputType
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.mydigitalmedicaljournal.R


class TextBoxDialog(title: String, message: String, text: String, val context: Context) {

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

        //TODO this might need more options
        //TODO not sure this flag is being set
        input.inputType = InputType.TYPE_TEXT_FLAG_CAP_WORDS
        input.setText(text)
        input.selectAll()
        builder.setView(input)

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

    fun getText(): String {
        return input.text.toString()
    }
}