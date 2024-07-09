/*
package com.example.bottomnavigationviewtest.ui.post

import MyPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.bottomnavigationviewtest.databinding.FragmentPostUploadBinding
import com.example.bottomnavigationviewtest.models.recruitpost.RecruitPost
import com.example.bottomnavigationviewtest.models.User
import com.example.bottomnavigationviewtest.viewmodels.PostViewModel


class PostUploadFragment : Fragment() {

    private var _binding: FragmentPostUploadBinding? = null
    private val binding get() = _binding!!

    private lateinit var postViewModel: PostViewModel
    private lateinit var currentUser: User

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPostUploadBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // ViewModel 설정
        postViewModel = ViewModelProvider(this).get(PostViewModel::class.java)

        val titleText: EditText = binding.titleEditText
        val contentText: EditText = binding.contentEditText
        val dateText: TextView = binding.dateTextView
        val uploadBtn: TextView = binding.uploadButton
        // 현재 사용자 정보 가져오기
        val email = MyPreferences.getEmail(requireContext())
        val name = MyPreferences.getNickname(requireContext())

        currentUser = email?.let { name?.let { it1 -> User(it, it1, "1234") } }!!

        // 날짜 설정
        dateText.text = postViewModel.getCurrentDateTime()



        // 글자 수 제한 및 업데이트 설정
        val contentCharCount: TextView = binding.contentCharCount

        setCharacterLimit(titleText, 50)
        setCharacterLimit(contentText, 500, contentCharCount)

        uploadBtn.setOnClickListener {
            val title = titleText.text.toString()
            val content = contentText.text.toString()
            val date = dateText.text.toString()

            val newPost = RecruitPost(0, title, currentUser, content, date)  // ID는 서버에서 자동으로 설정됨
            postViewModel.uploadPost(newPost)
        }


        return root
    }

    private fun setCharacterLimit(editText: EditText, maxLength: Int, charCountText: TextView? = null ) {
        editText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                s?.let {
                    if (it.length > maxLength) {
                        editText.setText(it.subSequence(0, maxLength))
                        editText.setSelection(maxLength)  // 커서를 텍스트의 끝으로 이동
                    }
                    charCountText?.text = "${it.length}/$maxLength"
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }
}*/
