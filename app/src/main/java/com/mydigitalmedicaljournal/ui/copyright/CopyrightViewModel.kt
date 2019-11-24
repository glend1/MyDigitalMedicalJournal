package com.mydigitalmedicaljournal.ui.copyright

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CopyrightViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is copyright Fragment"
    }
    val text: LiveData<String> = _text
}