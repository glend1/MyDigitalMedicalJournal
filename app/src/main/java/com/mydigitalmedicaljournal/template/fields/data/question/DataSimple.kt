package com.mydigitalmedicaljournal.template.fields.data.question

import android.content.Context
import com.mydigitalmedicaljournal.template.TemplateEnum
import com.mydigitalmedicaljournal.template.fields.data.GenericQuestionData

class DataSimple(context: Context): GenericQuestionData(context) {
    override val type = TemplateEnum.SIMPLE
    override fun validateAfterQuestion(errors: MutableMap<Int, String?>) {}
}