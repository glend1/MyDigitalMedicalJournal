package com.mydigitalmedicaljournal.ui._generics.dialogs.cancel

import android.content.Context
import androidx.appcompat.app.AlertDialog
import com.mydigitalmedicaljournal.R

class DialogCancelNull(builder: AlertDialog.Builder, context : Context) : AbstractDialogCancel {
    init {
        builder.setNeutralButton(context.getString(R.string.cancel), null)
    }
}