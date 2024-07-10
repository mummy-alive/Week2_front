package com.example.bottomnavigationviewtest.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.bottomnavigationviewtest.models.HomeDataResponse
import com.example.bottomnavigationviewtest.repository.HomeRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private val _homeData = MutableLiveData<HomeDataResponse>()
    val homeData: LiveData<HomeDataResponse> get() = _homeData

    fun fetchHomeData() {
        val context = getApplication<Application>().applicationContext
        HomeRepository.getHomeData(context, object : Callback<HomeDataResponse> {
            override fun onResponse(call: Call<HomeDataResponse>, response: Response<HomeDataResponse>) {
                if (response.isSuccessful) {
                    _homeData.postValue(response.body())
                } else {
                    Log.e("HomeViewModel", "Error: ${response.code()} - ${response.message()}")
                    // Handle other responses or errors if necessary
                }
            }

            override fun onFailure(call: Call<HomeDataResponse>, t: Throwable) {
                Log.e("HomeViewModel", "Request failed", t)
                // Handle failure
            }
        })
    }
}
