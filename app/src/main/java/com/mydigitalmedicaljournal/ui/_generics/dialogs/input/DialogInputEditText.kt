package com.mydigitalmedicaljournal.ui._generics.dialogs.input

import android.content.Context
import android.text.InputType
import android.widget.EditText
import androidx.appcompat.app.AlertDialog

class DialogInputEditText(builder: AlertDialog.Builder, context : Context, text: String) : AbstractDialogInput {
    private val input = EditText(context)
    init {
        //TODO this might need more options
        //TODO not sure this flag is being set
        input.inputType = InputType.TYPE_TEXT_FLAG_CAP_WORDS
        input.setText(text)
        input.selectAll()
        builder.setView(input)
    }
    fun getText(): String {
        return input.text.toString()
    }
}