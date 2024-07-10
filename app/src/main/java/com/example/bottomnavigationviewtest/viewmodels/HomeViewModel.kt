package com.example.bottomnavigationviewtest.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.bottomnavigationviewtest.models.HomeDataResponse
import com.example.bottomnavigationviewtest.models.profile.Profile
import com.example.bottomnavigationviewtest.repository.HomeRepository
import com.example.bottomnavigationviewtest.repository.ProfileRepository
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private val _homeData = MutableLiveData<HomeDataResponse>()
    val homeData: LiveData<HomeDataResponse> get() = _homeData

    private val _profile = MutableLiveData<Profile?>()
    val profile: LiveData<Profile?> get() = _profile


    fun fetchHomeData() {
        HomeRepository.getHomeData().enqueue(object : Callback<HomeDataResponse> {
            override fun onResponse(call: Call<HomeDataResponse>, response: Response<HomeDataResponse>) {
                if (response.isSuccessful) {
                    _homeData.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<HomeDataResponse>, t: Throwable) {
                Log.e("HomeViewModel", "Request failed", t)
                // Handle failure
            }
        })
    }

    fun fetchProfile(email: String) {
        viewModelScope.launch {
            ProfileRepository.getProfileByEmail(email).enqueue(object : Callback<Profile> {
                override fun onResponse(call: Call<Profile>, response: Response<Profile>) {
                    if (response.isSuccessful) {
                        _profile.postValue(response.body())
                    } else {
                        _profile.postValue(null)
                    }
                }

                override fun onFailure(call: Call<Profile>, t: Throwable) {
                    _profile.postValue(null)
                }
            })
        }
    }
}
