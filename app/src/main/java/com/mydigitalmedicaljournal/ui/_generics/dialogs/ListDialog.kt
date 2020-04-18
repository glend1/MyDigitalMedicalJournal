package com.mydigitalmedicaljournal.ui._generics.dialogs

import android.content.Context
import android.content.DialogInterface
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.ui.templates.categories.ManageCategoriesAdapter

class ListDialog(title: String, message: String, context: Context, adapter: ManageCategoriesAdapter) : Dialog(title, message, context) {

    private var input = RecyclerView(context)

    init {
        setInput(adapter)
        setCancel()
    }

    private fun setCancel() {
        builder.setNeutralButton(context.getString(R.string.Cancel), null)
    }

    private fun setInput(adapter: ManageCategoriesAdapter) {
        input.layoutManager = LinearLayoutManager(context)
        input.adapter = adapter
        builder.setView(input)
    }

}