package com.mydigitalmedicaljournal.template.file.properties

import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.template.editor.TemplateView

class Time: TemplateFormat() {

    override val type = TemplateView.TIMEFORMAT
    var timeFormat: TimeFormat? = null

    enum class TimeFormat(val view: Int) {
        DATE(R.id.date),DATETIME(R.id.date_time),DURATION(R.id.duration);
        companion object {
            fun getType(i: Int): TimeFormat? {
                for (type in values()) {
                    if (type.view == i) {
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
