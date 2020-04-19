package com.mydigitalmedicaljournal.ui._generics.dialogs

import android.content.Context
import android.content.DialogInterface
import android.text.InputType
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.ui._generics.dialogs.cancel.AbstractDialogCancel
import com.mydigitalmedicaljournal.ui._generics.dialogs.cancel.DialogCancelHideKeyboard
import com.mydigitalmedicaljournal.ui._generics.dialogs.cancel.DialogCancelNull
import com.mydigitalmedicaljournal.ui._generics.dialogs.input.AbstractDialogInput
import com.mydigitalmedicaljournal.ui._generics.dialogs.input.DialogInputEditText
import com.mydigitalmedicaljournal.ui._generics.dialogs.input.DialogInputNull

class ClearKeyboardEditText(title: String, message: String, text: String, context: Context, view: View): AbstractTextBoxDialog(title, message, text, context) {
    override var cancel: AbstractDialogCancel = DialogCancelHideKeyboard(builder, context, view)
}

