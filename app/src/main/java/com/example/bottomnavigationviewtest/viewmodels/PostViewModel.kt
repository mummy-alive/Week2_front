package com.example.bottomnavigationviewtest.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bottomnavigationviewtest.models.recruitpost.RecruitPost
import com.example.bottomnavigationviewtest.repository.PostRepository
import kotlinx.coroutines.launch

class PostViewModel : ViewModel() {
    private val _posts = MutableLiveData<List<RecruitPost>>()
    val posts: LiveData<List<RecruitPost>> get() = _posts


    fun fetchPosts() {
        viewModelScope.launch {
            try {
                val response = PostRepository.getPosts()
                if (response.isSuccessful) {
                    _posts.postValue(response.body())
                } else {
                    _posts.postValue(emptyList())
                }
            } catch (e: Exception) {
                _posts.postValue(emptyList())
                e.printStackTrace() // 로그에 예외 메시지 출력
            }
        }
    }
}
