package com.example.bottomnavigationviewtest.login

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bottomnavigationviewtest.network.RetrofitInstance
import com.example.bottomnavigationviewtest.models.User
import com.kakao.sdk.user.UserApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : ViewModel() {

    private val _loginResult = MutableLiveData<Result<String>>()
    val loginResult: LiveData<Result<String>> = _loginResult

    private val _userProfileExists = MutableLiveData<Boolean>()
    val userProfileExists: LiveData<Boolean> get() = _userProfileExists

    fun loginWithKakao(context: Context) {
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
            UserApiClient.instance.loginWithKakaoTalk(context) { token, error ->
                if (error != null) {
                    _loginResult.value = Result.failure(error)
                } else if (token != null) {
                    _loginResult.value = Result.success(token.accessToken)
                }
            }
        } else {
            UserApiClient.instance.loginWithKakaoAccount(context) { token, error ->
                if (error != null) {
                    _loginResult.value = Result.failure(error)
                } else if (token != null) {
                    _loginResult.value = Result.success(token.accessToken)
                }
            }
        }
    }

    fun sendUserInfoToServer(email: String, name: String) {
        val password = "1234"
        val user = User(email, name, password)
        Log.d("send to user info", "$email $name $password")
        RetrofitInstance.api.createUser(user).enqueue(object : Callback<Boolean> {
            override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                if (response.isSuccessful) {
                    _userProfileExists.value = response.body() ?: false
                }
            }

            override fun onFailure(call: Call<Boolean>, t: Throwable) {
                // Handle failure
            }
        })
    }
}
