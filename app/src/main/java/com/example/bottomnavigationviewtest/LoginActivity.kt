package com.example.bottomnavigationviewtest

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.bottomnavigationviewtest.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    // private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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

    }


}