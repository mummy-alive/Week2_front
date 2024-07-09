package com.example.bottomnavigationviewtest.login

import android.content.Context
import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kakao.sdk.user.UserApiClient

class LoginViewModel : ViewModel() {

    private val _loginResult = MutableLiveData<Result<String>>()
    val loginResult: LiveData<Result<String>> = _loginResult

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
}