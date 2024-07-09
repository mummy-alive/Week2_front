package com.example.bottomnavigationviewtest.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bottomnavigationviewtest.models.profile.Profile
import com.example.bottomnavigationviewtest.repository.ProfileRepository
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {
    private val _profile = MutableLiveData<Profile?>()
    val profile: MutableLiveData<Profile?> get() = _profile

    init {
        fetchProfile()
    }

    private fun fetchProfile() {
        viewModelScope.launch {
            val profileData = ProfileRepository.getProfile()
            _profile.postValue(profileData)
        }
    }
}
