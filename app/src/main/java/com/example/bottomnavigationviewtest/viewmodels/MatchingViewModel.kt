package com.example.bottomnavigationviewtest.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bottomnavigationviewtest.network.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MatchingViewModel : ViewModel() {

    private val _autoProfiles = MutableLiveData<List<ProfileData>>()
    val autoProfiles: LiveData<List<ProfileData>> get() = _autoProfiles

    init {
        fetchAutoProfiles()
    }

    fun fetchAutoProfiles() {
        viewModelScope.launch(Dispatchers.IO) {
            RetrofitInstance.api.getAutoMatchings().enqueue(object : Callback<List<ProfileData>> {
                override fun onResponse(call: Call<List<ProfileData>>, response: Response<List<ProfileData>>) {
                    if (response.isSuccessful) {
                        _autoProfiles.postValue(response.body())
                    } else {
                    }
                }

                override fun onFailure(call: Call<List<ProfileData>>, t: Throwable) {
                    // Handle failure
                }
            })
        }
    }
}

