package com.mydigitalmedicaljournal.ui._generics.dialogs

import android.content.Context
import android.content.DialogInterface
import com.mydigitalmedicaljournal.R

class ConfirmDialog(title: String, message: String, context: Context) : Dialog(title, message, context) {

    init {
        setCancel()
    }

    private fun setCancel() {
        builder.setNeutralButton(context.getString(R.string.Cancel), null)
    }

}