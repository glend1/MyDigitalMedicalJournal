package com.mydigitalmedicaljournal.template.fields.editor

import android.util.Log
import android.view.View
import com.mydigitalmedicaljournal.template.file.TemplateManager

class EditorDescription(view: View, template: TemplateManager, position: Int?): GenericEditor(view, template, position) {
    init {
        setSaveListener {
            Log.i("HELLO", "SAVE CLICKED")
        }
    }
}