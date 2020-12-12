package com.mydigitalmedicaljournal.template.fields.editor

import android.util.Log
import android.view.View
import com.mydigitalmedicaljournal.model.ValidData
import com.mydigitalmedicaljournal.ui.templates.editor.EditorAdapter

//TODO delete this
class EditorTest2(itemView: View) : DeleteableEditor(itemView) {
    override fun setup(view: View, adapter: EditorAdapter) {
        view.setOnClickListener {
            Log.i("EVENT2", "clicked")
        }
    }
    override fun errorHandling(view: View, validData: ValidData) {
        Log.i("EVENT2", "error")
    }
}