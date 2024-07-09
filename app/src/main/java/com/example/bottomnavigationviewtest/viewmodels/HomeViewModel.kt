package com.example.bottomnavigationviewtest.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bottomnavigationviewtest.models.HomeDataResponse
import com.example.bottomnavigationviewtest.repository.HomeRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {
    private val _homeData = MutableLiveData<HomeDataResponse>()
    val homeData: LiveData<HomeDataResponse> get() = _homeData

    fun fetchHomeData() {
        HomeRepository.getHomeData().enqueue(object : Callback<HomeDataResponse> {
            override fun onResponse(call: Call<HomeDataResponse>, response: Response<HomeDataResponse>) {
                if (response.isSuccessful) {
                    _homeData.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<HomeDataResponse>, t: Throwable) {
                // Handle failure
            }
        })
    }
}
