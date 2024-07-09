package com.example.bottomnavigationviewtest.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.bottomnavigationviewtest.MainActivity
import com.example.bottomnavigationviewtest.databinding.ActivityLoginBinding
import com.kakao.sdk.common.util.Utility

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
                Toast.makeText(this, "로그인 성공", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("kakao_token", token)
                startActivity(intent)
                finish()
            }.onFailure { error ->
                Log.e("LoginActivity", "로그인 실패: ${error.message}")
                Toast.makeText(this, "로그인 실패: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
