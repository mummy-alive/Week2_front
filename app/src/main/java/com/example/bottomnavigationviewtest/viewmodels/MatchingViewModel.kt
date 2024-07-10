package com.example.bottomnavigationviewtest.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bottomnavigationviewtest.models.profile.Profile
import com.example.bottomnavigationviewtest.models.profile.TransformedProfile
import com.example.bottomnavigationviewtest.repository.MatchingRepository
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MatchingViewModel : ViewModel() {
    private val _profiles = MutableLiveData<List<TransformedProfile>>()
    val profiles: LiveData<List<TransformedProfile>> get() = _profiles

    fun fetchProfiles(email: String) {
        viewModelScope.launch {
            val techTagsMap = fetchTechTagsMap() // Fetch or define your tech tags map here
            MatchingRepository.getAutoMatchings(email).enqueue(object : Callback<List<Profile>> {
                override fun onResponse(
                    call: Call<List<Profile>>,
                    response: Response<List<Profile>>
                ) {
                    if (response.isSuccessful) {
                        Log.d("matching fetch", "${response.body()}")
                    } else {
                        Log.e("matching fetch error", "Response not successful: ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<List<Profile>>, t: Throwable) {
                    Log.e("matching fail", "$t")
                }
            })
        }

    }

    private fun fetchTechTagsMap(): Map<Int, String> {
        // This should be replaced with actual fetching logic, for now, using static data
        return mapOf(
            1 to "프론트엔드",
            2 to "백엔드",
            3 to "앱개발",
            4 to "웹개발",
            5 to "데이터분석",
            6 to "인공지능",
            7 to "하드웨어",
            8 to "OS",
            9 to "자바",
            10 to "파이썬",
            11 to "C / C++",
            12 to "C#",
            13 to "Kotlin",
            14 to "HTML/CSS",
            15 to "UI 디자인"
        )
    }
}
