package com.mydigitalmedicaljournal.ui._generics

import android.content.Context
import android.view.View
import com.mydigitalmedicaljournal.R

class ClearKeyboardEditText(title: String, message: String, text: String, context: Context, val view: View): TextBoxDialog(title, message, text, context) {
    override fun setCancel() {
        builder.setNeutralButton(context.getString(R.string.Cancel)) { _, _ -> clear() }
        builder.setOnDismissListener { clear() }
    }
    private fun clear() {
        view.clearFocus()
    }
}