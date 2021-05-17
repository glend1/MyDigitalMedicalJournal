package com.mydigitalmedicaljournal.template.fields.data

import android.content.Context
import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.template.TemplateEnum

class DataTime: GenericData() {

    companion object {
        const val TIME_FIELD = R.string.date_field
        const val TIME_NOT_SET = R.string.TIME_NOT_SET
    }

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
    override fun listDisplay(context: Context): String = "${context.getString(type.listName)} : ${context.getString(time!!.displayName)}"
    var time: TimeFormat? = null

    override fun validate(): MutableMap<Int, Int> {
        val errors = mutableMapOf<Int, Int>()
        if (time == null) {
            errors[TIME_FIELD] = TIME_NOT_SET
        }
        return errors
    }



}
