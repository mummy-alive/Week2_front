package com.example.bottomnavigationviewtest.ui.post

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bottomnavigationviewtest.databinding.FragmentPostBinding
import com.example.bottomnavigationviewtest.viewmodels.PostViewModel

class PostFragment : Fragment() {
    private var _binding: FragmentPostBinding? = null
    private val binding get() = _binding!!
    private val postViewModel: PostViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPostBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = PostAdapter(emptyList())
        binding.postRecycler.layoutManager = LinearLayoutManager(requireContext())
        binding.postRecycler.adapter = adapter

        postViewModel.posts.observe(viewLifecycleOwner, Observer { posts ->
            if (posts != null) {
                adapter.updatePosts(posts)
            } else {
                Toast.makeText(requireContext(), "포스트를 가져올 수 없습니다.", Toast.LENGTH_SHORT).show()
            }
        })

        binding.uploadButton.setOnClickListener {
            // 업로드 버튼 클릭 이벤트 처리
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
