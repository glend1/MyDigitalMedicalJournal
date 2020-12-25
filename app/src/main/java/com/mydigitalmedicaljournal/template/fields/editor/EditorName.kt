package com.mydigitalmedicaljournal.template.fields.editor

import android.content.Context
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.model.ValidData
import com.mydigitalmedicaljournal.template.file.TemplateDefinition
import com.mydigitalmedicaljournal.ui.templates.editor.EditorAdapter

class EditorName(val itemView: View) : GenericEditor(itemView) {
    //TODO handle pysical keyboard
    companion object { const val ERROR = "NAME_ERROR" }
    private lateinit var et: EditText
    private lateinit var adapter: EditorAdapter
    val error: TextView = itemView.findViewById(R.id.error)

    override fun setup(view: View, adapter: EditorAdapter) {
        this.adapter = adapter
        et = view.findViewById(R.id.editText)
        setEvent(adapter)
        val name = adapter.localData.name
        if (TemplateDefinition.validName(name)) {
            setField(name!!)
        }
    }

    override fun errorHandlingOnSave(view: View, validData: ValidData) {
        showError(!validData.getErrors().contains(ERROR))
    }

    private fun showError(bool: Boolean) {
        if (bool) {
            error.visibility = View.GONE
        } else {
            error.visibility = View.VISIBLE
        }
    }

    private fun setEvent(adapter: EditorAdapter) {
        et.addTextChangedListener {
            changeData(adapter.localData, it.toString())
        }
        et.setOnEditorActionListener { v, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                et.clearFocus()
                val imm =
                    v.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(v.windowToken, 0)
            }
            false
        }
    }

    private fun setField(text: String) {
        et.setText(text)
    }

    private fun changeData(localData: TemplateDefinition, text: String) {
        showError(TemplateDefinition.validName(text))
        localData.name = text
    }
}
