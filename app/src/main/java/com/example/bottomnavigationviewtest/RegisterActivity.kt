package com.example.bottomnavigationviewtest

import android.os.Bundle
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
import com.example.bottomnavigationviewtest.network.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity: AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private var techTagList: MutableList<TechTag> = mutableListOf()
    val name  = MyPreferences.getNickname(this)
    val email = MyPreferences.getEmail(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
                val selectedTechTag = TechTag(position, techTagOptions[position])
                if (!techTagList.contains(selectedTechTag)) {
                    techTagList.add(selectedTechTag)
                }
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
        val profile = Profile(
            profile_id = 1, // 임의의 값
            email = Email(email!!, null, name!!),
            class_tag = binding.spinnerClass.selectedItemPosition + 1,
            mbti = binding.spinnerMbti.selectedItem.toString(),
            interest = binding.textInterest.text.toString(),
            is_recruit = binding.isRecruitCheckbox.isChecked,
            tech_tags = techTagList
        )

        RetrofitInstance.api.createProfile(profile).enqueue(object : Callback<ProfileResponse> {
            override fun onResponse(call: Call<ProfileResponse>, response: Response<ProfileResponse>) {
                if (response.isSuccessful) {
                    Toast.makeText(this@RegisterActivity, "프로필이 등록되었습니다.", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this@RegisterActivity, "등록 실패", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ProfileResponse>, t: Throwable) {
                Toast.makeText(this@RegisterActivity, "네트워크 오류: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
