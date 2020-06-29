package com.mydigitalmedicaljournal.ui._generics.dialogs

import android.content.Context
import com.mydigitalmedicaljournal.ui._generics.dialogs.cancel.AbstractDialogCancel
import com.mydigitalmedicaljournal.ui._generics.dialogs.cancel.DialogCancelNull
import com.mydigitalmedicaljournal.ui._generics.dialogs.input.AbstractDialogInput
import com.mydigitalmedicaljournal.ui._generics.dialogs.input.DialogInputList
import com.mydigitalmedicaljournal.ui.categories.ManageCategoriesAdapter

class ListDialog(title: String, message: String, context: Context, adapter: ManageCategoriesAdapter) : AbstractDialog(title, message, context) {
    override var cancel: AbstractDialogCancel = DialogCancelNull(builder, context)
    override var input: AbstractDialogInput = DialogInputList(builder, context, adapter)
}