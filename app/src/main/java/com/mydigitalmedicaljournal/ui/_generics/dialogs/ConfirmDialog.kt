package com.mydigitalmedicaljournal.ui._generics.dialogs

import android.content.Context
import android.content.DialogInterface
import com.mydigitalmedicaljournal.R

class ConfirmDialog(title: String, message: String, context: Context) : Dialog(title, message, context) {

    init{
        builder.setNeutralButton(context.getString(R.string.Cancel), null)
    }

    fun setConfirm(listener: DialogInterface.OnClickListener) {
        builder.setPositiveButton(context.getString(R.string.Yes), listener)
    }

}