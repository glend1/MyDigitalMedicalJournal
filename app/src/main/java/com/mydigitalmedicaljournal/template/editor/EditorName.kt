package com.mydigitalmedicaljournal.template.editor

import android.content.DialogInterface
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.model.ValidData
import com.mydigitalmedicaljournal.template.file.TemplateDefinition
import com.mydigitalmedicaljournal.ui._generics.dialogs.ClearKeyboardEditText
import com.mydigitalmedicaljournal.ui.templates.templates.editor.EditorAdapter

class EditorName(itemView: View) : GenericEditor(itemView) {
    companion object { const val ERROR = "NAME_ERROR" }
    private lateinit var et: EditText
    override fun setup(view: View, adapter: EditorAdapter) {
        et = view.findViewById(R.id.editText)
        setEvent(adapter, view)
        setField(adapter.localData.name)
    }
    override fun errorHandling(
        view: View,
        validData: ValidData
    ) {
        val test = validData.getErrors()
        val error = view.findViewById<TextView>(R.id.error)
        if (test.contains(ERROR)) {
            error.visibility = View.VISIBLE
        } else {
            error.visibility = View.GONE
        }
    }
    private fun setEvent(adapter: EditorAdapter, view: View) {
        et.setOnFocusChangeListener{ v: View, hasFocus: Boolean ->
            Log.i("FOCUS", hasFocus.toString())
            if (hasFocus) {
                val add =
                    ClearKeyboardEditText(
                        v.context.getString(R.string.Rename),
                        v.context.getString(R.string.Rename_Template_Text),
                        et.text.toString(),
                        v.context!!,
                        view
                    )
                add.setConfirm(DialogInterface.OnClickListener { _, _ ->
                    changeData(adapter.localData, add.getText())
                    v.clearFocus()
                })
                add.show()
            }
        }
    }
    private fun setField(text: String?) {
        et.setText(text)
    }
    private fun changeData(localData: TemplateDefinition, text: String) {
        setField(text)
        localData.name = text
    }
}