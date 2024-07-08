package com.example.bottomnavigationviewtest.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.bottomnavigationviewtest.network.RetrofitInstance
import kotlinx.coroutines.Dispatchers

class MatchingViewModel : ViewModel() {

    val profiles = liveData(Dispatchers.IO) {
        val retrievedProfiles = RetrofitInstance.api.getProfiles()
        emit(retrievedProfiles)
    }
}