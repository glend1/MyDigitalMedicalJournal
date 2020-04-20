package com.mydigitalmedicaljournal.ui._generics.dialogs

import android.content.Context
import android.view.View
import com.mydigitalmedicaljournal.ui._generics.dialogs.cancel.AbstractDialogCancel
import com.mydigitalmedicaljournal.ui._generics.dialogs.cancel.DialogCancelHideKeyboard

class ClearKeyboardEditText(title: String, message: String, text: String, context: Context, view: View): AbstractTextBoxDialog(title, message, text, context) {
    override var cancel: AbstractDialogCancel = DialogCancelHideKeyboard(builder, context, view)
}

