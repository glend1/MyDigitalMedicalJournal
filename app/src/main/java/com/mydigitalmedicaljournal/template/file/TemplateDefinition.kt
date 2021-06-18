package com.mydigitalmedicaljournal.template.file

import com.mydigitalmedicaljournal.template.fields.data.DataName
import com.mydigitalmedicaljournal.template.fields.data.DataTime
import com.mydigitalmedicaljournal.template.fields.data.GenericData
import java.util.*

class TemplateDefinition(private val id: UUID, private var data: MutableList<GenericData> = mutableListOf()) {

    init {
        if (data.isEmpty()) {
            data.add(DataName())
            data.add(DataTime())
        }
    }
    
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

    fun validate(): Boolean {
        val valid = mutableMapOf<Int, Int?>()
        data.forEach{field -> valid.putAll(field.validate())}
        return hasNoErrors(errorCount(valid))
    }

    private fun errorCount(valid: MutableMap<Int, Int?>): Int {
        var errorsNum = 0
        valid.forEach{ (id, i) ->
            if (i != null) {
                errorsNum++
            }
        }
        return errorsNum
    }

    private fun hasNoErrors(errorCount: Int): Boolean {
        if (errorCount == 0) {
            return true
        }
        return false
    }

    fun add(field: GenericData) {
        data.add(field)
    }

}