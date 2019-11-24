package com.mydigitalmedicaljournal.ui.instructions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class InstructionsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is instructions Fragment"
    }
    val text: LiveData<String> = _text
}