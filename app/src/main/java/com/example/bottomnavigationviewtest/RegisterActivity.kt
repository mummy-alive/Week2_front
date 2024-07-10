package com.example.bottomnavigationviewtest

import MyPreferences
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bottomnavigationviewtest.databinding.ActivityRegisterBinding
import com.example.bottomnavigationviewtest.models.Email
import com.example.bottomnavigationviewtest.models.profile.Profile
import com.example.bottomnavigationviewtest.models.profile.ProfileResponse
import com.example.bottomnavigationviewtest.models.TechTag
import com.example.bottomnavigationviewtest.models.TokenResponse
import com.example.bottomnavigationviewtest.network.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity: AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private var techTagList: MutableList<Int> = mutableListOf()
    private var name: String? = null
    private var email: String? = null
    private lateinit var token: String
    private lateinit var refreshToken: String
    private var userToken: String? = null
    private var userEmail: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        name = MyPreferences.getNickname(this)
        email = MyPreferences.getEmail(this)
        token = MyPreferences.getToken(this) ?: ""
        refreshToken = MyPreferences.getRefreshToken(this) ?: ""

        userToken = intent.getStringExtra("USER_TOKEN")
        userEmail = intent.getStringExtra("USER_EMAIL")


        val spinnerClass = binding.spinnerClass
        val spinnerMbti = binding.spinnerMbti
        val spinnerTechTag = binding.spinnerTechTag

        if (name == null || email == null) {
            Toast.makeText(this, "사용자 정보를 가져올 수 없습니다.", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        var selectedClassIndex: Int = -1
        var selectedTechTagIndex: Int = -1

        val classOptions = listOf("1분반", "2분반", "3분반", "4분반")
        val mbtiOptions = listOf(
            "ENTJ", "ENFJ", "ESFJ", "ESTJ",
            "ENTP", "ENFP", "ESFP", "ESTP",
            "INTJ", "INFJ", "ISFJ", "ISTJ",
            "INTP", "INFP", "ISFP", "ISTP"
        )

        // Tech tag options
        val techTagOptions = listOf(
            "프론트엔드", "백엔드", "앱개발", "웹개발",
            "데이터분석", "인공지능", "하드웨어", "OS",
            "자바", "파이썬", "C / C++", "C#",
            "Kotlin", "HTML/CSS", "UI 디자인"
        )

        // Setting adapters for the spinners
        val classAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, classOptions)
        classAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerClass.adapter = classAdapter

        val mbtiAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, mbtiOptions)
        mbtiAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerMbti.adapter = mbtiAdapter

        val techTagAdapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, techTagOptions)
        techTagAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerTechTag.adapter = techTagAdapter

        spinnerMbti.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                selectedClassIndex = position
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                selectedClassIndex = -1
            }
        }

        spinnerTechTag.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedTechTag = TechTag(position+1, techTagOptions[position])
                    techTagList.add(position+1)
                    Log.d("techTag Test", "$selectedTechTag")
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Do nothing
            }
        }

        binding.btnRegister.setOnClickListener {
            createProfile()
        }
    }

    private fun createProfile() {
        val email = MyPreferences.getEmail(this).toString()
        val profile = Profile(
            profile_id = 1, // 임의의 값
            email = email,
            class_tag = binding.spinnerClass.selectedItemPosition + 1,
            mbti = binding.spinnerMbti.selectedItem.toString(),
            interest = binding.textInterest.text.toString(),
            is_recruit = binding.isRecruitCheckbox.isChecked,
            tech_tags = techTagList
        )

        if (userToken != null || userToken == null) {
            RetrofitInstance.api.createProfile(/*"Bearer $userToken", */profile).enqueue(object : Callback<ProfileResponse> {
                override fun onResponse(call: Call<ProfileResponse>, response: Response<ProfileResponse>) {
                    if (response.isSuccessful) {
                        Toast.makeText(this@RegisterActivity, "Profile created successfully", Toast.LENGTH_LONG).show()
                        val intent = Intent(this@RegisterActivity, MainActivity::class.java)
/*                        intent.putExtra("USER_TOKEN", userToken)*/
                        startActivity(intent)
                        finish()
                    } /*else if (response.code() == 401) {
                        refreshAccessToken { newToken ->
                            userToken = newToken
                            MyPreferences.saveToken(this@RegisterActivity, newToken)
                            createProfile() // 갱신된 토큰으로 프로필 생성 다시 시도
                        }
                    }*/
                        else {
                        Log.d("failed profile check","$profile")
                        Toast.makeText(this@RegisterActivity, "Failed to create profile: ${response.message()}", Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<ProfileResponse>, t: Throwable) {
                    Toast.makeText(this@RegisterActivity, "Error: ${t.message}", Toast.LENGTH_LONG).show()
                }
            })
        } else {
            Toast.makeText(this, "User token is missing", Toast.LENGTH_LONG).show()
        }
    }

    private fun refreshAccessToken(onTokenRefreshed: (String) -> Unit) {
        val refreshTokenMap = mapOf("refresh" to refreshToken)
        RetrofitInstance.api.refreshAccessToken(refreshTokenMap).enqueue(object : Callback<TokenResponse> {
            override fun onResponse(call: Call<TokenResponse>, response: Response<TokenResponse>) {
                if (response.isSuccessful && response.body() != null) {
                    val newToken = response.body()!!.accessToken
                    onTokenRefreshed(newToken)
                } else {
                    Toast.makeText(this@RegisterActivity, "토큰 갱신 실패", Toast.LENGTH_SHORT).show()
                    Log.d("토큰 갱신 실패", "$token")
                }
            }

            override fun onFailure(call: Call<TokenResponse>, t: Throwable) {
                Toast.makeText(this@RegisterActivity, "토큰 갱신 네트워크 오류: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
