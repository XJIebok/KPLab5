package com.example.kp.ui.medicines

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MedicinesViewModel: ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is medicine Fragment"
    }
    val text: LiveData<String> = _text
}