package com.mydigitalmedicaljournal.ui._generics.dialogs

import android.content.Context
import android.content.DialogInterface
import android.view.View
import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.ui._generics.dialogs.components.cancel.DialogComponentCancelHideKeyboard
import com.mydigitalmedicaljournal.ui._generics.dialogs.components.input.DialogComponentInputEditText

/*class ClearKeyboardEditText(title: String, message: String, text: String, context: Context, val view: View): Dialog(title, message, context) {
    override val input = DialogComponentInputEditText(builder, text, context)
    override val cancel = DialogComponentCancelHideKeyboard(builder, context, view)

    init {
        initObjects()
    }

    fun setConfirm(listener: DialogInterface.OnClickListener) {
        builder.setPositiveButton(context.getString(R.string.Yes), listener)
    }

    fun getText(): String {
        return input.getFieldData()
    }
}*/