package com.example.Molzakgyo.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.Molzakgyo.network.RetrofitInstance
import kotlinx.coroutines.Dispatchers

class MatchingViewModel : ViewModel() {

    val profiles = liveData(Dispatchers.IO) {
        val retrievedProfiles = RetrofitInstance.api.getProfiles()
        emit(retrievedProfiles)
    }
}