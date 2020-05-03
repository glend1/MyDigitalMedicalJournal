package com.mydigitalmedicaljournal.template.editor

import android.util.Log
import android.view.View
import com.mydigitalmedicaljournal.ui.templates.templates.editor.EditorAdapter

class EditorTest2(itemView: View) : GenericEditor(itemView) {
    override fun setup(view: View, adapter: EditorAdapter) {
        view.setOnClickListener {
            //TODO set the event
            Log.i("EVENT2", "clicked")
        }
    }

}