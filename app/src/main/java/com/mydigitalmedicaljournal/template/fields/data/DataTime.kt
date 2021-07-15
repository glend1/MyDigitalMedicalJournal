package com.mydigitalmedicaljournal.template.fields.data

import android.content.Context
import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.template.TemplateEnum

class DataTime(context: Context): GenericData(context) {

    enum class TimeFormat(val view: Int, val displayName: Int) {
        DATE(R.id.date, R.string.date),DATETIME(R.id.date_time, R.string.date_time),DURATION(R.id.duration, R.string.duration);
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
    override fun listDisplay(): String = "${getStrRes(type.listName)} : ${getStrRes(time!!.displayName)}"
    @Transient private var timeError: String? = getStrRes(R.string.NOT_FOUND, getStrRes(R.string.time_format))
    var time: TimeFormat? = null
        set(value) {
            if (value == null) {
                timeError = getStrRes(R.string.NOT_FOUND, getStrRes(R.string.time_format))
            } else {
                timeError = null
                field = value
            }
        }
    override fun validate(): MutableMap<Int, String?> {
        return mutableMapOf(Pair(R.id.time_field, timeError))
    }



}
