package com.mydigitalmedicaljournal.ui._generics.dialogs.input

import android.content.Context
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.ui.categories.ManageCategoriesAdapter

class DialogInputList(builder: AlertDialog.Builder, context: Context, adapter: ManageCategoriesAdapter) : AbstractDialogInput {
    //TODO this needs testing
    private var input = RecyclerView(context)
    init {
        if (adapter.itemCount > 0) {
            input.layoutManager = LinearLayoutManager(context)
            input.adapter = adapter
            builder.setView(input)
        } else {
            //TODO no styling information here
            val errorMessage = TextView(context)
            errorMessage.text = context.getString(R.string.no_templates)
            builder.setView(errorMessage)
        }
    }
}
