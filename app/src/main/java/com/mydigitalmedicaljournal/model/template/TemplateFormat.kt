package com.mydigitalmedicaljournal.model.template

import com.mydigitalmedicaljournal.ui.templates.templates.editor.TemplateView

open class TemplateFormat(val type: TemplateView) {
    //TODO implement this class
    /*
        this should not store its own name as
        i never need multiple template definitions so this being singular is fine
            however i need an array of objects because there isn't just a singular field
        should this be a data class?
     */
}