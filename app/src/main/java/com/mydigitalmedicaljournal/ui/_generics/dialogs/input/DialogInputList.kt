package com.mydigitalmedicaljournal.ui._generics.dialogs.input

import android.content.Context
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mydigitalmedicaljournal.ui.templates.categories.ManageCategoriesAdapter

class DialogInputList(builder: AlertDialog.Builder, context: Context, adapter: ManageCategoriesAdapter) : AbstractDialogInput {
    private var input = RecyclerView(context)
    init {
        input.layoutManager = LinearLayoutManager(context)
        input.adapter = adapter
        builder.setView(input)
    }
}
