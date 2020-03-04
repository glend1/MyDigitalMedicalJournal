package com.mydigitalmedicaljournal.ui.templates.templates.editor

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class EditorViewModel: ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is editor Fragment"
    }
    val text: LiveData<String> = _text
}