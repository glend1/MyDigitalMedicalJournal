package com.mydigitalmedicaljournal.ui._generics

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mydigitalmedicaljournal.ui.templates.categories.ManageCategoriesAdapter

class ListDialog(title: String, message: String, context: Context, adapter: ManageCategoriesAdapter) {

    //TODO this uses default styling
    private val builder = AlertDialog.Builder(context)
    private lateinit var dialog: AlertDialog
    private var input = RecyclerView(context)

    init {
        // Set the alert dialog title
        builder.setTitle(title)

        // Display a message on alert dialog
        builder.setMessage(message)
        //builder.setMessage(text)

        input.layoutManager = LinearLayoutManager(context)
        input.adapter = adapter
        builder.setView(input)

        // Display a negative button on alert dialog
        //builder.setNegativeButton("No", null)

        // Display a neutral button on alert dialog
        builder.setNeutralButton("Cancel", null)
    }

    fun setListener(listener: DialogInterface.OnClickListener) {
        // Set a positive button and its click listener on alert dialog
        builder.setPositiveButton("Yes", listener)

        // Finally, make the alert dialog using builder
        dialog = builder.create()
    }

    fun show() {
        // Display the alert dialog on app interface
        dialog.show()
    }
}