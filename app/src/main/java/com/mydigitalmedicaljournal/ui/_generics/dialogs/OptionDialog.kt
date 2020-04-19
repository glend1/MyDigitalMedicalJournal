package com.mydigitalmedicaljournal.ui._generics.dialogs

import android.content.Context
import android.content.DialogInterface
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.ui._generics.dialogs.cancel.AbstractDialogCancel
import com.mydigitalmedicaljournal.ui._generics.dialogs.cancel.DialogCancelNull
import com.mydigitalmedicaljournal.ui._generics.dialogs.input.AbstractDialogInput
import com.mydigitalmedicaljournal.ui._generics.dialogs.input.DialogInputNull
import com.mydigitalmedicaljournal.ui._generics.dialogs.input.DialogInputOption

class OptionDialog(title: String, message: String, data: MutableList<String>, context: Context) : AbstractDialog(title, message, context) {

    override var cancel: AbstractDialogCancel = DialogCancelNull(builder, context)
    override var input: AbstractDialogInput = DialogInputOption(builder, context, data)

    fun getSelected(): Int {
        return (input as DialogInputOption).getSelected()
    }
}