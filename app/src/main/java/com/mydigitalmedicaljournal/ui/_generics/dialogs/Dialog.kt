package com.mydigitalmedicaljournal.ui._generics.dialogs

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog
import com.mydigitalmedicaljournal.R

abstract class Dialog(title: String, message: String, protected val context: Context) {

    //TODO this has default styling
    protected val builder = AlertDialog.Builder(context)
    private lateinit var dialog: AlertDialog

    init {
        builder.setTitle(title)
        builder.setMessage(message)
    }

    fun setConfirm(listener: DialogInterface.OnClickListener) {
        builder.setPositiveButton(context.getString(R.string.Yes), listener)
    }

    fun show() {
        dialog = builder.create()
        dialog.show()
    }
}
