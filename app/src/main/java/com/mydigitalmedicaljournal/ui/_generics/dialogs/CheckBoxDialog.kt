package com.mydigitalmedicaljournal.ui._generics.dialogs

import android.content.Context
import android.view.ViewGroup
import com.mydigitalmedicaljournal.ui._generics.dialogs.cancel.AbstractDialogCancel
import com.mydigitalmedicaljournal.ui._generics.dialogs.cancel.DialogCancelNull
import com.mydigitalmedicaljournal.ui._generics.dialogs.input.AbstractDialogInput
import com.mydigitalmedicaljournal.ui._generics.dialogs.input.DialogInputCheckBox
import com.mydigitalmedicaljournal.ui.categories.ManageCategoriesAdapter

class CheckBoxDialog(
    title: String,
    message: String,
    context: Context,
    adapter: ManageCategoriesAdapter,
    container: ViewGroup?
) : AbstractDialog(title, message, context) {
    override var cancel: AbstractDialogCancel = DialogCancelNull(builder, context)
    override var input: AbstractDialogInput = DialogInputCheckBox(builder, context, adapter, container)
}