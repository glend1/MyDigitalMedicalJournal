package com.mydigitalmedicaljournal.template.fields.data.question

import com.mydigitalmedicaljournal.template.TemplateEnum
import com.mydigitalmedicaljournal.template.fields.data.GenericQuestionData

class DataRating: GenericQuestionData() {
    override val type = TemplateEnum.RATING
    override fun validateAfterQuestion(errors: MutableMap<Int, Int?>) {
        TODO("Not yet implemented")
    }
}