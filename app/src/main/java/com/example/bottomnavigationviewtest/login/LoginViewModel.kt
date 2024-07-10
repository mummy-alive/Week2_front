package com.example.bottomnavigationviewtest.login

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bottomnavigationviewtest.models.User
import com.example.bottomnavigationviewtest.repository.UserRepository
import com.kakao.sdk.user.UserApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : ViewModel() {

    private val _loginResult = MutableLiveData<Result<String>>()
    val loginResult: LiveData<Result<String>> = _loginResult

    private val _userProfileExists = MutableLiveData<Boolean>()
    val userProfileExists: LiveData<Boolean> get() = _userProfileExists

    private val _user = MutableLiveData<User?>()
    val user: LiveData<User?> get() = _user

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

    fun sendUserInfoToServer(email: String, name: String, token:String) {
        UserRepository.getUserByEmail(token, email).enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful && response.body() != null) {
                    _user.value = response.body()
                    _userProfileExists.value = true
                } else {
                    val password = "1234"
                    val user = User(email, name, password)
                    Log.d("send to user info", "$email $name $password")
                    Log.d("서버에 보내는 check current token", "$token")
                    UserRepository.createUser(token, user).enqueue(object : Callback<Boolean> {
                        override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                            _userProfileExists.value = response.isSuccessful && response.body() == true
                        }

                        override fun onFailure(call: Call<Boolean>, t: Throwable) {
                            _userProfileExists.value = false
                            Log.e("LoginViewModel", "sendUserInfoToServer onFailure: ${t.message}")
                        }
                    })
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                _userProfileExists.value = false
                Log.e("LoginViewModel", "getUserByEmail onFailure: ${t.message}")
            }
        })
    }
}
