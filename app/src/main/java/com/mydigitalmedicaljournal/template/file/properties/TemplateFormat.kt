package com.mydigitalmedicaljournal.template.file.properties

import com.mydigitalmedicaljournal.template.editor.TemplateView

abstract class TemplateFormat {
    //TODO implement this class
    /*
        this should not store its own name as
        i never need multiple template definitions so this being singular is fine
            however i need an array of objects because there isn't just a singular field
        should this be a data class?
     */

    abstract val type: TemplateView

    abstract fun createInstance(): TemplateFormat
}