package com.mydigitalmedicaljournal.template.fields.data

import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.template.TemplateEnum

class DataTime: GenericData() {

    companion object {
        const val TIME_FIELD = R.string.date_field
        const val TIME_NOT_SET = R.string.TIME_NOT_SET
    }

    enum class TimeFormat(val view: Int) {
        DATE(R.id.date),DATETIME(R.id.date_time),DURATION(R.id.duration);
        companion object {
            fun getType(i: Int?): TimeFormat? {
                for (type in values()) {
                    if (type.view == i) {
                        return type
                    }
                }
                return null
            }
        }
    }

    override val type = TemplateEnum.TIME
    var time: TimeFormat? = null

    override fun validate(): MutableMap<Int, Int> {
        val errors = mutableMapOf<Int, Int>()
        if (time == null) {
            errors[TIME_FIELD] = TIME_NOT_SET
        }
        return errors
    }



}
