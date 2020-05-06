package com.mydigitalmedicaljournal.template.editor

import android.content.DialogInterface
import android.util.Log
import android.view.View
import android.widget.EditText
import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.template.file.TemplateDefinition
import com.mydigitalmedicaljournal.ui._generics.dialogs.ClearKeyboardEditText
import com.mydigitalmedicaljournal.ui.templates.templates.editor.EditorAdapter

class EditorName(itemView: View) : GenericEditor(itemView) {
    private lateinit var et: EditText
    override fun setup(view: View, adapter: EditorAdapter) {
        et = view.findViewById(R.id.editText)
        setEvent(adapter, view)
        setField(adapter.localData.name)
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