package com.example.bottomnavigationviewtest.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bottomnavigationviewtest.models.profile.Profile
import com.example.bottomnavigationviewtest.repository.MatchingRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MatchingViewModel : ViewModel() {
    private val _profiles = MutableLiveData<List<Profile>>()
    val profiles: LiveData<List<Profile>> get() = _profiles

    fun fetchProfiles() {
        MatchingRepository.getAutoMatchings().enqueue(object : Callback<List<Profile>> {
            override fun onResponse(call: Call<List<Profile>>, response: Response<List<Profile>>) {
                if (response.isSuccessful) {
                    _profiles.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<List<Profile>>, t: Throwable) {
                // Handle failure
            }
        })
    }
}
