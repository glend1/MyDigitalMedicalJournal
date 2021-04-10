package com.mydigitalmedicaljournal.template.file

import com.mydigitalmedicaljournal.template.fields.data.DataName
import com.mydigitalmedicaljournal.template.fields.data.DataTime
import com.mydigitalmedicaljournal.template.fields.data.GenericData
import java.util.*

//class TemplateDefinition(private val id: UUID, var name: String? = null, var time: DataTime.TimeFormat? = null, var data: MutableList<GenericData> = mutableListOf()) {
class TemplateDefinition(private val id: UUID, private var data: MutableList<GenericData> = mutableListOf()) {
    /*companion object {
        fun validName(string: String?) : Boolean {
            return !string.isNullOrBlank()
        }

        fun validDate(format: DataTime.TimeFormat?): Boolean {
            return format != null
        }
    }*/
    
    fun getId() : UUID {
        return id
    }

    fun getName() : DataName {
        return data[0] as DataName
    }

    fun getTime(): DataTime {
        return data[1] as DataTime
    }

    fun getData(pos: Int): GenericData {
        return data[pos]
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

    fun validate(): MutableMap<Int, Int> {
        val valid = mutableMapOf<Int, Int>()
        data.forEach{field -> valid.putAll(field.validate())}
        return valid
    }

    fun add(field: GenericData) {
        data.add(field)
    }



    /*fun validate(): ValidData {
        val validData = ValidData()
        validData.add(EditorName.ERROR, validName())
        validData.add(EditorTime.ERROR, validDate())
        return validData
    }

    fun getName() {
        return name
    }

    private fun validName(): Boolean {
        return true //validName(name)
    }

    private fun validDate(): Boolean {
        return true //validDate(time)
    }*/

    //TODO validate data field





}