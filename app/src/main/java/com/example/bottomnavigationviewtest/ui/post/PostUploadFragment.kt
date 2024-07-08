package com.example.bottomnavigationviewtest.ui.post

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
import com.example.bottomnavigationviewtest.models.RecruitPost
import com.example.bottomnavigationviewtest.viewmodels.PostViewModel


class PostUploadFragment : Fragment() {

    private var _binding: FragmentPostUploadBinding? = null
    private val binding get() = _binding!!

    private lateinit var postViewModel: PostViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPostUploadBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // ViewModel 설정
        postViewModel = ViewModelProvider(this).get(PostViewModel::class.java)

        val titleText: EditText = binding.titleEditText
        val writerText: TextView = binding.writerTextView
        val contentText: EditText = binding.contentEditText
        val dateText: TextView = binding.dateTextView
        val uploadBtn: TextView = binding.uploadButton

        // 날짜 설정
        dateText.text = postViewModel.getCurrentDateTime()

        // 작성자
        postViewModel.authorName.observe(viewLifecycleOwner, Observer { authorName ->
            writerText.text = authorName
        })

        // 글자 수 제한 및 업데이트 설정
        val contentCharCount: TextView = binding.contentCharCount

        setCharacterLimit(titleText, 50)
        setCharacterLimit(contentText, 500, contentCharCount)

        uploadBtn.setOnClickListener {
            val title = titleText.text.toString()
            val writer = writerText.text.toString()
            val content = contentText.text.toString()
            val date = dateText.text.toString()

            val newPost = RecruitPost(0, title, content, writer, date)  // ID는 서버에서 자동으로 설정됨
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
}