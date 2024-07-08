package com.example.bottomnavigationviewtest.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bottomnavigationviewtest.models.RecruitPost
import com.example.bottomnavigationviewtest.network.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import okhttp3.*

class PostViewModel : ViewModel() {

    private val _allPosts = MutableLiveData<List<RecruitPost>>()
    val allPosts: LiveData<List<RecruitPost>> get() = _allPosts

    private val _authorName = MutableLiveData<String>()
    val authorName: LiveData<String> get() = _authorName

    init {
        fetchPosts()
        fetchAuthorName()  // 작성자 이름을 가져오는 함수 호출
    }

    fun fetchPosts() {
        viewModelScope.launch(Dispatchers.IO) {
            RetrofitInstance.api.getPosts().enqueue(object : Callback<List<RecruitPost>> {
                override fun onResponse(call: Call<List<RecruitPost>>, response: Response<List<RecruitPost>>) {
                    if (response.isSuccessful) {
                        _allPosts.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<List<RecruitPost>>, t: Throwable) {
                    // Handle failure
                }
            })
        }
    }

    fun uploadPost(post: RecruitPost) {
        viewModelScope.launch(Dispatchers.IO) {
            RetrofitInstance.api.uploadPost(post).enqueue(object : Callback<RecruitPost> {
                override fun onResponse(call: Call<RecruitPost>, response: Response<RecruitPost>) {
                    if (response.isSuccessful) {
                        fetchPosts()  // 업로드 후 게시글 목록을 다시 가져옴
                    }
                }

                override fun onFailure(call: Call<RecruitPost>, t: Throwable) {
                    // Handle failure
                }
            })
        }
    }

    fun fetchAuthorName() {
        // 여기서 작성자의 이름을 가져오는 로직을 추가합니다.
        // 예시: 서버에서 사용자 정보를 가져오는 API를 호출하는 코드
        // 임시로 "John Doe"로 설정
        _authorName.postValue("John Doe")
    }

    fun getCurrentDateTime(): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return sdf.format(Date())
    }
}
