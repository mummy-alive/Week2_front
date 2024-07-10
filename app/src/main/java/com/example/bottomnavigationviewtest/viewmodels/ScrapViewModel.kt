package com.example.bottomnavigationviewtest.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bottomnavigationviewtest.models.recruitpost.RecruitPost
import com.example.bottomnavigationviewtest.repository.ScrapRepository
import kotlinx.coroutines.launch

class ScrapViewModel : ViewModel() {
    private val _scrapPosts = MutableLiveData<List<RecruitPost>>()
    val scrapPosts: LiveData<List<RecruitPost>> get() = _scrapPosts

    fun fetchScrapPosts() {
        viewModelScope.launch {
            val scrapPosts = ScrapRepository.getScrapPosts()
            _scrapPosts.postValue(scrapPosts)
        }
    }
}
