package com.mydigitalmedicaljournal.template.file.properties

import com.mydigitalmedicaljournal.template.editor.TemplateView

class Name: TemplateFormat() {
    override val type = TemplateView.NAME
    override fun createInstance(): Name {
        return Name()
    }

}