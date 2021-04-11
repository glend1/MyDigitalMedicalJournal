package com.mydigitalmedicaljournal.ui._generics.dialogs.textbox.cancel

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AlertDialog
import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.ui._generics.dialogs.cancel.AbstractDialogCancel

class DialogCancelHideKeyboard(builder: AlertDialog.Builder, val view: View) : AbstractDialogCancel {
    //TODO delete this if its not used
    init {
        builder.setNeutralButton(view.context.getString(R.string.Cancel)) { _, _ -> clearKeyboard() }
        builder.setOnDismissListener { clearKeyboard() }
    }

    private fun clearKeyboard() {
        val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
        view.clearFocus()
    }

}