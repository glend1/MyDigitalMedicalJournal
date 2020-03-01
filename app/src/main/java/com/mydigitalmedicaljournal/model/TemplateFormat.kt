package com.mydigitalmedicaljournal.model

data class TemplateFormat(var name: String) {
    //TODO implement this class
    //TODO this should not store its own name
    fun formatString(): String {
        return "this is a test to see what happens ${name.length}"
    }
}