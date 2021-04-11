package com.mydigitalmedicaljournal.ui._generics.dialogs.textbox

import android.content.Context
import com.mydigitalmedicaljournal.ui._generics.dialogs.cancel.AbstractDialogCancel
import com.mydigitalmedicaljournal.ui._generics.dialogs.cancel.DialogCancelNull
import com.mydigitalmedicaljournal.ui._generics.dialogs.textbox.AbstractTextBoxDialog


class TextBoxDialog(title: String, message: String, text: String, context: Context): AbstractTextBoxDialog(title, message, text, context) {
    override var cancel: AbstractDialogCancel = DialogCancelNull(builder, context)
}