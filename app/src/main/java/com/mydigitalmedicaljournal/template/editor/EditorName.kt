package com.mydigitalmedicaljournal.template.editor

import android.content.Context
import android.util.Log
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

class EditorName(itemView: View) : GenericEditor(itemView) {
    //TODO this need testing
    //TODO handle pysical keyboard
    companion object { const val ERROR = "NAME_ERROR" }
    private lateinit var et: EditText
    override fun setup(view: View, adapter: EditorAdapter) {
        et = view.findViewById(R.id.editText)
        setEvent(adapter)
        setField(adapter.localData.name)
    }
    override fun errorHandling(view: View, validData: ValidData) {
        val test = validData.getErrors()
        val error = view.findViewById<TextView>(R.id.error)
        if (test.contains(ERROR)) {
            error.visibility = View.VISIBLE
        } else {
            error.visibility = View.GONE
        }
    }
    private fun setEvent(adapter: EditorAdapter) {
        et.addTextChangedListener {
            changeData(adapter.localData, it.toString())
        }
        et.setOnEditorActionListener { v, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                Log.i("HEELO", "test")
                et.clearFocus()
                val imm =
                    v.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(v.windowToken, 0)
            }
            false
        }
    }
    private fun setField(text: String?) {
        et.setText(text)
    }
    private fun changeData(localData: TemplateDefinition, text: String) {
        localData.name = text
    }
}