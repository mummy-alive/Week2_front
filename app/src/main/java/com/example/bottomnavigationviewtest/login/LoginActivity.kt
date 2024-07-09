package com.example.bottomnavigationviewtest.login

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
                getUserInfo() // 사용자 정보 가져오기
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
            } else {
                // Profile 생성 프래그먼트로 이동
                val intent = Intent(this, RegisterActivity::class.java)
                startActivity(intent)
                finish()
            }
        })
    }

    private fun getUserInfo() {
        UserApiClient.instance.me { user, error ->
            if (error != null) {
                Toast.makeText(this, "사용자 정보 요청 실패: ${error.message}", Toast.LENGTH_SHORT).show()
            } else if (user != null) {
                val email = "fkrfkrfkr2@naver.com"
                val nickname = user.kakaoAccount?.profile?.nickname ?: "닉네임 없음"
                MyPreferences.saveEmail(this, email)
                MyPreferences.saveNickname(this, nickname)
                Log.d("login check email and name", "email: $email , name: $nickname")
                sendUserInfoToServer(email, nickname) // 사용자 정보를 서버로 전송
            }
        }
    }

    private fun sendUserInfoToServer(email: String, name: String) {
        loginViewModel.sendUserInfoToServer(email, name)
    }
}
