package com.mydigitalmedicaljournal.template.file.properties

import com.mydigitalmedicaljournal.template.editor.TemplateView

class Test2: TemplateFormat() {
    override val type = TemplateView.TEST2
    override fun createInstance(): Test2 {
        return Test2()
    }

}