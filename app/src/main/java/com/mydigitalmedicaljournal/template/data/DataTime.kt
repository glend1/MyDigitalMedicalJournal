package com.mydigitalmedicaljournal.template.data

import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.template.TemplateEnum

class DataTime: GenericData() {

    override val type = TemplateEnum.TIME
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
