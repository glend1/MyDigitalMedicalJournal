package com.mydigitalmedicaljournal.ui._generics.dialogs

import android.content.Context
import com.mydigitalmedicaljournal.ui._generics.dialogs.cancel.AbstractDialogCancel
import com.mydigitalmedicaljournal.ui._generics.dialogs.cancel.DialogCancelNull
import com.mydigitalmedicaljournal.ui._generics.dialogs.input.AbstractDialogInput
import com.mydigitalmedicaljournal.ui._generics.dialogs.input.DialogInputSpinner

class SpinnerDialog(title: String, message: String, data: Array<Int>, context: Context) : AbstractDialog(title, message, context) {
    //TODO this isn't actually used anywhere, delete it?
    override var cancel: AbstractDialogCancel = DialogCancelNull(builder, context)
    override var input: AbstractDialogInput = DialogInputSpinner(builder, context, data)

    fun getSelected(): Int {
        return (input as DialogInputSpinner).getSelected()
    }
}