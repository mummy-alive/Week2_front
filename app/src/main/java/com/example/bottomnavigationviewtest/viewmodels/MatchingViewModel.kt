package com.example.bottomnavigationviewtest.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.bottomnavigationviewtest.models.Profile
import com.example.bottomnavigationviewtest.network.RetrofitInstance
import kotlinx.coroutines.Dispatchers

class MatchingViewModel : ViewModel() {

    /*
    liveData : 데이터의 변경사항을 관찰하는 객체
    Dispatchers.IO : 네트워크 요청 등의 IO작업을 백그라운드에서 수행할 수 있도록 지정
    RetroInstance ~ : api요청 수행
    emit : liveData에 값을 보냄
     */

    private val _profiles = liveData(Dispatchers.IO) {
        val retrievedProfiles = RetrofitInstance.api.getProfiles()
        emit(retrievedProfiles)
    }
    val profiles: LiveData<List<Profile>> = _profiles


    // 필터
    private val _filteredProfiles = MutableLiveData<List<Profile>>()
    val filteredProfiles: LiveData<List<Profile>> = _filteredProfiles

    private val selectedTags = mutableListOf<String>()

    // 테크태그 구현하고 난 뒤에
/*    fun updateSelectedTags(tags: List<String>) {
        selectedTags.clear()
        selectedTags.addAll(tags)
        filterProfiles()
    }

    private fun filterProfiles() {
        val allProfiles = profiles.value ?: emptyList()
        val filtered = allProfiles.filter { profile ->
            selectedTags.all { tag -> tag in profile.techTags }
        }
        _filteredProfiles.value = filtered
    }*/

    // 좋아요
}