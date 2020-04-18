package com.mydigitalmedicaljournal.ui._generics.dialogs

import android.content.Context
import android.content.DialogInterface
import android.text.InputType
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import com.mydigitalmedicaljournal.R

class ClearKeyboardEditText(title: String, message: String, text: String, context: Context, val view: View): Dialog(title, message, context) {

    private val input = EditText(context)

    init {
        setInput(text)
        setCancel()
    }

    private fun setInput(text: String) {
        //TODO this might need more options
        //TODO not sure this flag is being set
        input.inputType = InputType.TYPE_TEXT_FLAG_CAP_WORDS
        input.setText(text)
        input.selectAll()
        builder.setView(input)
    }

    private fun setCancel() {
        builder.setNeutralButton(context.getString(R.string.Cancel)) { _, _ -> clearKeyboard() }
        builder.setOnDismissListener { clearKeyboard() }
    }

    fun getText(): String {
        return input.text.toString()
    }

    private fun clearKeyboard() {
        val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
        view.clearFocus()
    }
}

