package com.mydigitalmedicaljournal.ui._generics.dialogs.input

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.ui.categories.ManageCategoriesAdapter

class DialogInputList(builder: AlertDialog.Builder, context: Context, adapter: ManageCategoriesAdapter) : AbstractDialogInput {
    init {
        if (adapter.itemCount > 0) {
            val input: View = LayoutInflater.from(context).inflate(R.layout.recycler_layout, null)
            input.findViewById<RecyclerView>(R.id.recycler).adapter = adapter
            builder.setView(input)
        } else {
            //TODO no styling information here
            val input: View = LayoutInflater.from(context).inflate(R.layout.empty_recycler, null)
            input.findViewById<TextView>(R.id.message).text = context.getString(R.string.no_templates)
            builder.setView(input)
        }
    }
}
