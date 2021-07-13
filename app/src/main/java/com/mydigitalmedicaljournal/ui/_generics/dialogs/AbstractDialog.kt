package com.mydigitalmedicaljournal.ui._generics.dialogs

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog
import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.ui._generics.dialogs.cancel.AbstractDialogCancel
import com.mydigitalmedicaljournal.ui._generics.dialogs.input.AbstractDialogInput

abstract class AbstractDialog(title: String, message: String, protected val context: Context) {
    //TODO this has default styling
    protected val builder = AlertDialog.Builder(context)
    protected lateinit var dialog: AlertDialog
    protected abstract var cancel: AbstractDialogCancel
    protected abstract var input: AbstractDialogInput

    init {
        builder.setTitle(title)
        builder.setMessage(message)
    }

    fun setConfirm(listener: DialogInterface.OnClickListener) {
        setConfirm(R.string.yes, listener)
    }

    fun setConfirm(name: Int, listener: DialogInterface.OnClickListener) {
        builder.setPositiveButton(context.getString(name), listener)
    }

    fun show() {
        dialog = builder.create()
        dialog.show()
    }

    fun dismiss() {
        dialog.dismiss()
    }
}
