package com.mydigitalmedicaljournal.template.data

import com.mydigitalmedicaljournal.R

class Time: TemplateFormat() {

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

}
