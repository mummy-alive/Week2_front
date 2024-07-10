package com.example.bottomnavigationviewtest.login

import MyPreferences
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bottomnavigationviewtest.MainActivity
import com.example.bottomnavigationviewtest.RegisterActivity
import com.example.bottomnavigationviewtest.databinding.ActivityLoginBinding
import com.example.bottomnavigationviewtest.models.profile.Profile
import com.example.bottomnavigationviewtest.network.LoginRequest
import com.example.bottomnavigationviewtest.network.LoginResponse
import com.example.bottomnavigationviewtest.network.RetrofitInstance
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.user.UserApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonKakao.setOnClickListener {
            loginWithKakao()
        }
    }

    private fun loginWithKakao() {
        UserApiClient.instance.loginWithKakaoAccount(this) { token, error ->
            if (error != null) {
                Toast.makeText(this, "카카오 로그인 실패: ${error.message}", Toast.LENGTH_LONG).show()
            } else if (token != null) {
                Toast.makeText(this, "카카오 로그인 성공", Toast.LENGTH_SHORT).show()
                Log.d("success kakao login", "$token")
                // 카카오 로그인 성공 시 서버로 토큰 전송
                loginUserWithKakaoToken(token.accessToken)
            }
        }
    }

    private fun loginUserWithKakaoToken(kakaoToken: String) {
        val request = LoginRequest(name = "송종찬32", email = "jongchan32@naver.com", password = "11111111")
        RetrofitInstance.api.loginWithKakao(kakaoToken, request).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    val loginResponse = response.body()
                    if (loginResponse != null) {
                        Log.d("success kakao login", "${loginResponse.token}")
                        MyPreferences.saveEmail(this@LoginActivity, loginResponse.user.email)
                        /*

                        MyPreferences.saveToken(this@LoginActivity, loginResponse.token)*/
                        Toast.makeText(this@LoginActivity, "Welcome, ${loginResponse.user.name}", Toast.LENGTH_LONG).show()
                        checkUserProfile(loginResponse.token, loginResponse.user.email)
                    }
                } else {
                    Toast.makeText(this@LoginActivity, "Login failed: ${response.message()}", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Toast.makeText(this@LoginActivity, "Error: ${t.message}", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun checkUserProfile(token: String, email: String) {
        RetrofitInstance.api.getUserProfile(email).enqueue(object : Callback<Profile> {
            override fun onResponse(call: Call<Profile>, response: Response<Profile>) {
                if (response.isSuccessful && response.body() != null) {
                    // 프로필이 존재하면 MainActivity로 이동
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    intent.putExtra("USER_TOKEN", token)
                    startActivity(intent)
                    finish()
                } else {
                    // 프로필이 없으면 RegisterActivity로 이동
                    val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
                    intent.putExtra("USER_TOKEN", token)
                    intent.putExtra("USER_EMAIL", email)
                    startActivity(intent)
                    finish()
                }
            }

            override fun onFailure(call: Call<Profile>, t: Throwable) {
                Toast.makeText(this@LoginActivity, "Failed to fetch profile: ${t.message}", Toast.LENGTH_LONG).show()
            }
        })
    }


}



/*

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.bottomnavigationviewtest.MainActivity
import com.example.bottomnavigationviewtest.RegisterActivity
import com.example.bottomnavigationviewtest.databinding.ActivityLoginBinding
import com.kakao.sdk.common.util.Utility
import com.kakao.sdk.user.UserApiClient

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val keyHash = Utility.getKeyHash(this)
        Log.d("Hash", keyHash)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonKakao.setOnClickListener {
            loginViewModel.loginWithKakao(this)
        }

        loginViewModel.loginResult.observe(this, Observer { result ->
            result.onSuccess { token ->
                Log.d("LoginActivity", "Token: $token")
                MyPreferences.saveToken(this, token)
                getUserInfo(token) // 사용자 정보 가져오기
            }.onFailure { error ->
                Log.e("LoginActivity", "로그인 실패: ${error.message}")
                Toast.makeText(this, "로그인 실패: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        })

        loginViewModel.userProfileExists.observe(this, Observer { exists ->
            if (exists) {
                // MainActivity로 이동
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else { // 없으면
                // Profile 생성 프래그먼트로 이동
                val intent = Intent(this, RegisterActivity::class.java)
                startActivity(intent)
                finish()
            }
        })
    }

    private fun getUserInfo(token: String) {
        UserApiClient.instance.me { user, error ->
            if (error != null) {
                Toast.makeText(this, "사용자 정보 요청 실패: ${error.message}", Toast.LENGTH_SHORT).show()
            } else if (user != null) {
                val email = "fkrfkrfkr2@naver.com"
                val nickname = user.kakaoAccount?.profile?.nickname ?: "닉네임 없음"
                MyPreferences.saveEmail(this, email)
                MyPreferences.saveNickname(this, nickname)
                Log.d("login check email and name", "email: $email , name: $nickname")
                sendUserInfoToServer(email, nickname, token) // 사용자 정보를 서버로 전송
            }
        }
    }

    private fun sendUserInfoToServer(email: String, name: String, token: String) {
        loginViewModel.sendUserInfoToServer(email, name, token)
    }
}
*/
