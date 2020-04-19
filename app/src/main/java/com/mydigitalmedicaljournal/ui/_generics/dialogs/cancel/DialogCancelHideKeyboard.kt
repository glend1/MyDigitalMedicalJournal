package com.mydigitalmedicaljournal.ui._generics.dialogs.cancel

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AlertDialog
import com.mydigitalmedicaljournal.R

class DialogCancelHideKeyboard(builder: AlertDialog.Builder, context : Context, val view: View) : AbstractDialogCancel {
    init {
        builder.setNeutralButton(context.getString(R.string.Cancel)) { _, _ -> clearKeyboard() }
        builder.setOnDismissListener { clearKeyboard() }
    }

    private fun clearKeyboard() {
        val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
        view.clearFocus()
    }

}