package com.mydigitalmedicaljournal.ui._generics.dialogs.textbox

import android.content.Context
import com.mydigitalmedicaljournal.ui._generics.dialogs.AbstractDialog
import com.mydigitalmedicaljournal.ui._generics.dialogs.input.AbstractDialogInput
import com.mydigitalmedicaljournal.ui._generics.dialogs.textbox.input.DialogInputEditText

abstract class AbstractTextBoxDialog(title: String, message: String, text: String, context: Context): AbstractDialog(title, message, context) {
    override var input: AbstractDialogInput = DialogInputEditText(builder, context, text)

    fun getText(): String {
        return (input as DialogInputEditText).getText()
    }
}