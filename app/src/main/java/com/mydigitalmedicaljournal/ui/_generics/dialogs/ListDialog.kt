package com.mydigitalmedicaljournal.ui._generics.dialogs

import android.content.Context
import android.content.DialogInterface
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.ui._generics.dialogs.cancel.AbstractDialogCancel
import com.mydigitalmedicaljournal.ui._generics.dialogs.cancel.DialogCancelNull
import com.mydigitalmedicaljournal.ui._generics.dialogs.input.AbstractDialogInput
import com.mydigitalmedicaljournal.ui._generics.dialogs.input.DialogInputNull
import com.mydigitalmedicaljournal.ui.templates.categories.ManageCategoriesAdapter

class ListDialog(title: String, message: String, context: Context, adapter: ManageCategoriesAdapter) : AbstractDialog(title, message, context) {

    //private var input = RecyclerView(context)
    override var cancel: AbstractDialogCancel = DialogCancelNull(builder, context)
    override var input: AbstractDialogInput = DialogInputNull()


    /*init {
        setInput(adapter)
    }

    private fun setInput(adapter: ManageCategoriesAdapter) {
        input.layoutManager = LinearLayoutManager(context)
        input.adapter = adapter
        builder.setView(input)
    }*/

}