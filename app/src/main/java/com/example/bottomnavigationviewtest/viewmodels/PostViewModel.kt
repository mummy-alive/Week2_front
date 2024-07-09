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

    init {
        fetchPosts()
    }

    private fun fetchPosts() {
        viewModelScope.launch {
            val postsData = PostRepository.getPosts()
            _posts.postValue(postsData!!)
        }
    }
}
