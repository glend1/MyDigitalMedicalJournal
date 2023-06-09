package com.mydigitalmedicaljournal.ui.templates.dialog

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.ui._generics.dialogs.input.AbstractDialogInput

class TemplateInput(builder: AlertDialog.Builder, val context: Context, container: ViewGroup?) : AbstractDialogInput {
    val view: View = LayoutInflater.from(context).inflate(R.layout.new_template, container, false)
    val dialogName : EditText = view.findViewById(R.id.dialog_name)
    private val errorName: TextView = view.findViewById(R.id.error_name)
    val dialogDate: RadioGroup = view.findViewById(R.id.dialog_date)
    private val errorDate: TextView = view.findViewById(R.id.error_date)

    init {
        builder.setView(view)
    }

    fun errorName(visibility: Int, error: String?) {
        errorName.text = error!!
        errorName.visibility = visibility
    }

    fun errorDate(visibility: Int, error: String?) {
        errorDate.text = error!!
        errorDate.visibility = visibility
    }
}
