package com.mydigitalmedicaljournal.template.data

import com.mydigitalmedicaljournal.template.TemplateEnum

abstract class GenericData {
    //TODO these need testing
    //TODO implement this class
    /*
        this should not store its own name as
        i never need multiple template definitions so this being singular is fine
            however i need an array of objects because there isn't just a singular field
        should this be a data class?
     */
    abstract val type: TemplateEnum
}