package com.mydigitalmedicaljournal.template.fields.editor

import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.template.file.TemplateManager

abstract class GenericEditor(protected val view: View, protected val template: TemplateManager, protected val position: Int?) {
    private val saveButton: ConstraintLayout = view.findViewById(R.id.save)
    protected fun setSaveListener(listener: View.OnClickListener) {
        saveButton.setOnClickListener(listener)
    }
}