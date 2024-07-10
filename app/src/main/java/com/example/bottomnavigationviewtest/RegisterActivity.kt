package com.example.bottomnavigationviewtest

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bottomnavigationviewtest.databinding.ActivityRegisterBinding

class RegisterActivity: AppCompatActivity() {
    // private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // login function
        binding.btnRegister.setOnClickListener {
            val userId = binding.editTextId!!.text.toString()
            val password = binding.editTextPassword!!.text.toString()
            val passwordConfirm = binding.editTextPasswordConfirm!!.text.toString()
            val userName = binding.editTextName!!.text.toString()
            val userPhoneNum = binding.editTextPhoneNum!!.text.toString()
            if (userId == "" || password == "" || userName == "" || userPhoneNum == "") Toast.makeText(
                this@RegisterActivity,
                "회원정보를 전부 입력해주세요",
                Toast.LENGTH_SHORT
            ).show()
            else if (password != passwordConfirm) {
                Toast.makeText(
                    this@RegisterActivity,
                    "비밀번호가 일치하지 않습니다",
                    Toast.LENGTH_SHORT
                ).show()
            } else { // 회원가입 성공
            }
        }

    }

}