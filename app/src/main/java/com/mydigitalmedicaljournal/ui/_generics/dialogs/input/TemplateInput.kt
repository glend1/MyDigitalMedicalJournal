package com.mydigitalmedicaljournal.ui._generics.dialogs.input

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.mydigitalmedicaljournal.R

class TemplateInput(builder: AlertDialog.Builder, val context: Context, container: ViewGroup?) : AbstractDialogInput {
    val view: View = LayoutInflater.from(context).inflate(R.layout.new_template, container)
    val dialogName : EditText = view.findViewById(R.id.dialog_name)
    private val errorName: TextView = view.findViewById(R.id.error_name)
    val dialogDate: RadioGroup = view.findViewById(R.id.dialog_date)
    private val errorDate: TextView = view.findViewById(R.id.error_date)

    init {
        builder.setView(view)
    }

    fun errorName(visibility: Int, error: Int?) {
        errorName.text = error?.let { context.resources.getString(it)}
        errorName.visibility = visibility
    }

    fun errorDate(visibility: Int, error: Int?) {
        errorDate.text = error?.let { context.resources.getString(it)}
        errorDate.visibility = visibility
    }
}
