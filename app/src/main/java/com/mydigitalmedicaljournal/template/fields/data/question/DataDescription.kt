package com.mydigitalmedicaljournal.template.fields.data.question

import android.content.Context
import com.mydigitalmedicaljournal.template.TemplateEnum
import com.mydigitalmedicaljournal.template.fields.data.GenericQuestionData

class DataDescription(context: Context): GenericQuestionData(context) {
    override val type = TemplateEnum.DESCRIPTION
    override fun validateAfterQuestion(errors: MutableMap<Int, String?>) {}
}