package com.mydigitalmedicaljournal.template.file

import com.mydigitalmedicaljournal.model.ValidData
import com.mydigitalmedicaljournal.template.fields.data.DataTime
import com.mydigitalmedicaljournal.template.fields.data.GenericData
import com.mydigitalmedicaljournal.template.fields.editor.EditorName
import com.mydigitalmedicaljournal.template.fields.editor.EditorTime
import java.util.*

class TemplateDefinition(private val id: UUID, var name: String? = null, var time: DataTime.TimeFormat? = null, var data: MutableList<GenericData> = mutableListOf()) {

    companion object {
        fun validName(string: String?) : Boolean {
            return !string.isNullOrBlank()
        }

        fun validDate(format: DataTime.TimeFormat?): Boolean {
            return format != null
        }
    }
    
    fun getId() : UUID {
        return id
    }

    private fun validName(): Boolean {
        return validName(name)
    }

    private fun validDate(): Boolean {
        return validDate(time)
    }

    //TODO validate data field

    fun validate(): ValidData {
        val validData = ValidData()
        validData.add(EditorName.ERROR, validName())
        validData.add(EditorTime.ERROR, validDate())
        return validData
    }

    fun delete(position: Int) {
        data.removeAt(position)
    }

    fun moveDown(position: Int) {
        val temp = data[position]
        delete(position)
        data.add(position+1, temp)
    }

    fun moveUp(position: Int) {
        val temp = data[position]
        delete(position)
        data.add(position-1, temp)
    }

    fun size(): Int {
        return data.size
    }

}