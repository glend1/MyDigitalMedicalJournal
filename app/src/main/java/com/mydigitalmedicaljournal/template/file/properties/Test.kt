package com.mydigitalmedicaljournal.template.file.properties

import com.mydigitalmedicaljournal.template.editor.TemplateView

class Test: TemplateFormat() {
    override val type = TemplateView.TEST
    override fun createInstance(): Test {
        return Test()
    }

}