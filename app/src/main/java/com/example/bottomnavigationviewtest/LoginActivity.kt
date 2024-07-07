package com.example.bottomnavigationviewtest

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.bottomnavigationviewtest.databinding.ActivityLoginBinding
import com.example.bottomnavigationviewtest.network.LoginResponse
import com.example.bottomnavigationviewtest.network.RetrofitInstance
import com.example.bottomnavigationviewtest.network.TokenRequest
import com.kakao.sdk.common.util.Utility
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    // private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityLoginBinding
    private val  kakaoAuthViewModel : KakaoAuthViewModel by viewModels ()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val keyHash = Utility.getKeyHash(this)
        Log.d("Hash", keyHash)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // login function
        binding.buttonLogin.setOnClickListener {
            val userId = binding.editTextId!!.text.toString()
            val password = binding.editTextPassword!!.text.toString()
            if (userId == "" || password == "") Toast.makeText(
                this@LoginActivity,
                "회원정보를 전부 입력해주세요",
                Toast.LENGTH_SHORT
            ).show() else{
                /* val checkUserpass
                if(checkUserpass == true) : 로그인 성공
                else : 로그인 실패
                 */
                val intent = Intent(applicationContext, MainActivity::class.java)
                startActivity(intent)
            }
        }

        binding.buttonRegister.setOnClickListener{
            val intent = Intent(applicationContext, RegisterActivity::class.java)
            startActivity(intent)
        }

        binding.buttonKakao.setOnClickListener {
            kakaoAuthViewModel.kakaoLogin()
        }

        // 로그인 상태 관찰
        lifecycleScope.launch {
            kakaoAuthViewModel.isLoggedIn.collect { isLoggedIn ->
                if (isLoggedIn) {
                    Toast.makeText(this@LoginActivity, "카카오 로그인 성공", Toast.LENGTH_SHORT).show()
                    val intent = Intent(applicationContext, MainActivity::class.java)
                    startActivity(intent)
                    finish() // 로그인 후 현재 액티비티를 종료
/*
                    // 액세스 토큰 가져오기
                    val accessToken = kakaoAuthViewModel.accessToken

                    // Django 서버로 액세스 토큰 전송
                    sendTokenToServer(accessToken)*/
                }
            }
        }

    }

    private fun sendTokenToServer(token: String) {
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
    }


}