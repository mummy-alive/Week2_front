package com.example.bottomnavigationviewtest.ui.main.matching

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MatchingViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Hello blank fragment"
    }
    val text: LiveData<String> = _text
}