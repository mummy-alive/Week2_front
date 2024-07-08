package com.example.bottomnavigationviewtest.ui.post

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.bottomnavigationviewtest.R
import com.example.bottomnavigationviewtest.databinding.FragmentPostUploadBinding
import com.example.bottomnavigationviewtest.databinding.FragmentProfileBinding
import com.example.bottomnavigationviewtest.models.RecruitPost
import com.example.bottomnavigationviewtest.ui.matching.ProfileViewModel


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
        postViewModel = ViewModelProvider(this).get(postViewModel::class.java)

        val titleText: TextView = binding.titleEditText
        val writerText: TextView = binding.writerTextView
        val contentText: TextView = binding.contentEditText
        val dateText: TextView = binding.dateTextView
        val uploadBtn: TextView = binding.uploadButton

        // 날짜 설정
        dateText.text = postViewModel.getCurrentDateTime()

        // 작성자
        postViewModel.authorName.observe(viewLifecycleOwner, Observer { authorName ->
            writerText.text = authorName
        })

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
}