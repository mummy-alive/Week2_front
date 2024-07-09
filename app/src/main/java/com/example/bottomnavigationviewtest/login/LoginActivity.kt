package com.example.bottomnavigationviewtest.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.bottomnavigationviewtest.MainActivity
import com.example.bottomnavigationviewtest.databinding.ActivityLoginBinding
import com.kakao.sdk.common.util.Utility
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {
    // private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityLoginBinding
    private val  kakaoAuthViewModel : KakaoAuthViewModel by viewModels ()
    // accessToken 구현

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val keyHash = Utility.getKeyHash(this)
        Log.d("Hash", keyHash)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

/*        // login function
        binding.buttonLogin.setOnClickListener {
            val userId = binding.editTextId!!.text.toString()
            val password = binding.editTextPassword!!.text.toString()
            if (userId == "" || password == "") Toast.makeText(
                this@LoginActivity,
                "회원정보를 전부 입력해주세요",
                Toast.LENGTH_SHORT
            ).show() else{
                *//* val checkUserpass
                if(checkUserpass == true) : 로그인 성공
                else : 로그인 실패
                 *//*
                val intent = Intent(applicationContext, MainActivity::class.java)
                startActivity(intent)
            }
        }

        binding.buttonRegister.setOnClickListener{
            val intent = Intent(applicationContext, RegisterActivity::class.java)
            startActivity(intent)
        }*/

        binding.buttonKakao.setOnClickListener {
            kakaoAuthViewModel.kakaoLogin()
        }

        lifecycleScope.launch {
            kakaoAuthViewModel.isLoggedIn.collect { isLoggedIn ->
                if (isLoggedIn) {
                    kakaoAuthViewModel.kakaoAccessToken.value?.let { token ->
                        kakaoAuthViewModel.sendTokenToBackend(token) { success, message ->
                            if (success) {
                                MyPreferences.saveToken(this@LoginActivity, token)
                                Toast.makeText(this@LoginActivity, "로그인 성공", Toast.LENGTH_SHORT).show()
                                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                                intent.putExtra("userId", message)
                                startActivity(intent)
                                finish()
                            }/* else {
                                Toast.makeText(this@LoginActivity, "로그인 실패: $message", Toast.LENGTH_SHORT).show()
                            }*/
                        }
                    }
                } else {
                    Toast.makeText(this@LoginActivity, "카카오 로그인 실패", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}

/*    private fun sendTokenToServer(token: String) {
        val tokenRequest = TokenRequest(token)
        RetrofitInstance.api.sendKakaoToken(tokenRequest).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    val loginResponse = response.body()
                    if (loginResponse != null && loginResponse.success) {
                        val intent = Intent(applicationContext, MainActivity::class.java)
                        startActivity(intent)
                        finish() // 로그인 후 현재 액티비티를 종료
                    } else {
                        Toast.makeText(this@LoginActivity, "서버 로그인 실패: ${loginResponse?.message}", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this@LoginActivity, "서버 로그인 실패: ${response.message()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Toast.makeText(this@LoginActivity, "서버 로그인 실패: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }*/