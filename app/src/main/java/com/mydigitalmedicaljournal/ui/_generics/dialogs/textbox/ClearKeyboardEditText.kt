package com.mydigitalmedicaljournal.ui._generics.dialogs.textbox

import android.view.View
import com.mydigitalmedicaljournal.ui._generics.dialogs.cancel.AbstractDialogCancel
import com.mydigitalmedicaljournal.ui._generics.dialogs.textbox.cancel.DialogCancelHideKeyboard

class ClearKeyboardEditText(title: String, message: String, text: String, view: View): AbstractTextBoxDialog(title, message, text, view.context) {
    //TODO delete this if its not used
    override var cancel: AbstractDialogCancel = DialogCancelHideKeyboard(builder, view)
}

