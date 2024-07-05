package com.example.bottomnavigationviewtest.ui.matching

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ProfileViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Hello blank fragment"
    }
    val text: LiveData<String> = _text
}