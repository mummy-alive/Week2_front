package com.example.bottomnavigationviewtest.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bottomnavigationviewtest.models.Profile
import com.example.bottomnavigationviewtest.network.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileViewModel : ViewModel() {

    private val _profile = MutableLiveData<Profile>()
    val profile: LiveData<Profile> get() = _profile

    fun fetchProfileByEmail(email: String) {
        // hardcoding
        viewModelScope.launch(Dispatchers.IO) {
            RetrofitInstance.api.getProfileByEmail(email).enqueue(object : Callback<Profile> {
                override fun onResponse(call: Call<Profile>, response: Response<Profile>) {
                    if (response.isSuccessful) {
                        _profile.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<Profile>, t: Throwable) {
                    // Handle failure
                }
            })
        }
    }
}
