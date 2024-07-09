/*
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bottomnavigationviewtest.models.UserLikeResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LikeViewModel : ViewModel() {

    private val _profiles = MutableLiveData<List<UserLikeResponse>>()
    val profiles: LiveData<List<UserLikeResponse>> get() = _profiles

    private var currentUserId: Int = 1 // 예시를 위한 기본값

    fun fetchLikesByFromId(fromId: Int) {
        currentUserId = fromId
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val call = RetrofitInstance.apiService.getLikedProfiles(fromId)
                call.enqueue(object : Callback<List<UserLikeResponse>> {
                    override fun onResponse(
                        call: Call<List<UserLikeResponse>>,
                        response: Response<List<UserLikeResponse>>
                    ) {
                        if (response.isSuccessful) {
                            _profiles.postValue(response.body() ?: emptyList())
                        }
                    }

                    override fun onFailure(call: Call<List<UserLikeResponse>>, t: Throwable) {
                        // 에러 처리
                    }
                })
            }
        }
    }

    fun deleteLike(likeId: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val call = RetrofitInstance.apiService.deleteLike(likeId)
                call.enqueue(object : Callback<Void> {
                    override fun onResponse(call: Call<Void>, response: Response<Void>) {
                        if (response.isSuccessful) {
                            fetchLikesByFromId(currentUserId)
                        }
                    }

                    override fun onFailure(call: Call<Void>, t: Throwable) {
                        // 에러 처리
                    }
                })
            }
        }
    }
}

*/
