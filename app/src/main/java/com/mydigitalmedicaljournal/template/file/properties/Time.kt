package com.mydigitalmedicaljournal.template.file.properties

import com.mydigitalmedicaljournal.template.editor.TemplateView

class Time: TemplateFormat() {

    override val type = TemplateView.TIMEFORMAT
    var timeFormat: TimeFormat? = null

    enum class TimeFormat(val i: Int) {
        DATE(0),DATETIME(1),DURATION(2);
        companion object {
            fun getType(i: Int): TimeFormat? {
                for (type in values()) {
                    if (type.i == i) {
                        return type
                    }
                }
                return null
            }
        }
    }



    override fun createInstance(): Time {
        return Time()
    }
}
