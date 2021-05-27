package com.mydigitalmedicaljournal.template.fields.data.question

import com.mydigitalmedicaljournal.template.TemplateEnum
import com.mydigitalmedicaljournal.template.fields.data.GenericQuestionData

class DataSimple: GenericQuestionData() {
    override val type = TemplateEnum.SIMPLE
    override fun validateAfterQuestion(errors: MutableMap<Int, Int>) {}
}