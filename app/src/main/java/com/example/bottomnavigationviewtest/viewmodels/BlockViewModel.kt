/*
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bottomnavigationviewtest.models.UserBlockResponse
import com.example.bottomnavigationviewtest.network.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BlockViewModel : ViewModel() {

    private val _profiles = MutableLiveData<List<UserBlockResponse>>()
    val profiles: LiveData<List<UserBlockResponse>> get() = _profiles

    private var currentUserId: Int = 1 // 예시를 위한 기본값

    fun fetchBlocksByFromId(fromId: Int) {
        currentUserId = fromId
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val call = RetrofitInstance.api.getBlockedProfiles(fromId)
                call.enqueue(object : Callback<List<UserBlockResponse>> {
                    override fun onResponse(
                        call: Call<List<UserBlockResponse>>,
                        response: Response<List<UserBlockResponse>>
                    ) {
                        if (response.isSuccessful) {
                            _profiles.postValue(response.body() ?: emptyList())
                        }
                    }

                    override fun onFailure(call: Call<List<UserBlockResponse>>, t: Throwable) {
                        // 에러 처리
                    }
                })
            }
        }
    }

    fun deleteBlock(likeId: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val call = RetrofitInstance.api.deleteBlock(likeId)
                call.enqueue(object : Callback<Void> {
                    override fun onResponse(call: Call<Void>, response: Response<Void>) {
                        if (response.isSuccessful) {
                            fetchBlocksByFromId(currentUserId)
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
