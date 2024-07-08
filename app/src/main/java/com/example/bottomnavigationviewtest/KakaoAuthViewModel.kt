package com.example.bottomnavigationviewtest.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.bottomnavigationviewtest.network.LoginResponse
import com.example.bottomnavigationviewtest.network.RetrofitInstance
import com.example.bottomnavigationviewtest.network.TokenRequest
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class KakaoAuthViewModel(application: Application) : AndroidViewModel(application) {

    companion object {
        const val TAG = "KakaoAuthViewModel"
    }

    private val context = application.applicationContext

    val isLoggedIn = MutableStateFlow(false)
    val kakaoAccessToken = MutableStateFlow<String?>(null)

    fun kakaoLogin() {
        viewModelScope.launch {
            val loginResult = handleKakaoLogin()
            isLoggedIn.emit(loginResult.first)
            kakaoAccessToken.emit(loginResult.second)
        }
    }

    private suspend fun handleKakaoLogin(): Pair<Boolean, String?> =
        suspendCoroutine { continuation ->
            val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
                if (error != null) {
                    Log.e(TAG, "카카오 계정으로 로그인 실패", error)
                    continuation.resume(Pair(false, null))
                } else if (token != null) {
                    Log.i(TAG, "카카오 계정으로 로그인 성공 ${token.accessToken}")
                    continuation.resume(Pair(true, token.accessToken))
                }
            }

            if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
                UserApiClient.instance.loginWithKakaoTalk(context) { token, error ->
                    if (error != null) {
                        Log.e(TAG, "카카오톡으로 로그인 실패", error)

                        if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                            continuation.resume(Pair(false, null))
                            return@loginWithKakaoTalk
                        }

                        UserApiClient.instance.loginWithKakaoAccount(context, callback = callback)
                    } else if (token != null) {
                        Log.i(TAG, "카카오톡으로 로그인 성공 ${token.accessToken}")
                        continuation.resume(Pair(true, token.accessToken))
                    }
                }
            } else {
                UserApiClient.instance.loginWithKakaoAccount(context, callback = callback)
            }
        }

    fun sendTokenToBackend(token: String, onResult: (Boolean, String?) -> Unit) {
        val tokenRequest = TokenRequest(token)
        RetrofitInstance.api.sendKakaoToken(tokenRequest).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let { loginResponse ->
                        if (loginResponse.success) {
                            onResult(true, loginResponse.userId)
                        } else {
                            onResult(false, loginResponse.message)
                        }
                    }
                } else {
                    onResult(false, "서버 오류: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                onResult(false, "네트워크 오류: ${t.message}")
            }
        })
    }
}
