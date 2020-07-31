package com.mydigitalmedicaljournal.model

class ValidData {
    //TODO this needs support for the same type of errors
    private val data = HashMap<String, Boolean>()
    fun add(error: String, result: Boolean) {
        data[error] = result
    }

    fun getErrors(): Set<String> {
        val errors = mutableSetOf<String>()
        data.forEach {
            if (!it.value) {
                errors.add(it.key)
            }
        }
        return errors
    }
}