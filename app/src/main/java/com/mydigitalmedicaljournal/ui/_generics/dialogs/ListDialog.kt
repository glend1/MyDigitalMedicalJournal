package com.mydigitalmedicaljournal.ui._generics.dialogs

import android.content.Context
import android.view.ViewGroup
import com.mydigitalmedicaljournal.ui._generics.dialogs.cancel.AbstractDialogCancel
import com.mydigitalmedicaljournal.ui._generics.dialogs.cancel.DialogCancelNull
import com.mydigitalmedicaljournal.ui._generics.dialogs.input.AbstractDialogInput
import com.mydigitalmedicaljournal.ui._generics.dialogs.input.DialogInputList

class ListDialog(title: String, message: String, data: Array<Int>, context: Context, container: ViewGroup?) : AbstractDialog(title, message, context) {
    override var cancel: AbstractDialogCancel = DialogCancelNull(builder, context)
    override var input: AbstractDialogInput = DialogInputList(builder, context, data, container)

    fun setListener(function: (Int) -> Unit) {
        (input as DialogInputList).listener = function
    }
}