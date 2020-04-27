package com.mydigitalmedicaljournal.model

class ValidData {
    private val data = HashMap<String, Boolean>()
    fun add(error: String, result: Boolean) {
        data[error] = result
    }
    fun test() : Boolean {
        data.forEach {
            if (!it.value) {
                return false
            }
        }
        return true
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