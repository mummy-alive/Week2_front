package com.example.Molzakgyo.ui.post

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.Molzakgyo.R
import com.example.Molzakgyo.adapter.RecruitPostAdapter
import com.example.Molzakgyo.databinding.FragmentPostBinding
import com.example.Molzakgyo.viewmodels.PostViewModel

class PostFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RecruitPostAdapter

    private var _binding: FragmentPostBinding? = null
    private lateinit var postViewModel: PostViewModel

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPostBinding.inflate(inflater, container, false)
        val view = binding.root
        recyclerView = binding.postRecycler
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = RecruitPostAdapter(emptyList())
        recyclerView.adapter = adapter

        postViewModel = ViewModelProvider(this).get(PostViewModel::class.java)
        postViewModel.allPosts.observe(viewLifecycleOwner, Observer { posts ->
            posts?.let {
                adapter.updateData(it)
            }
        })

        // 데이터 가져오기
        postViewModel.fetchPosts()

        // 업로드페이지 이동
        val uploadBtn: ImageButton = binding.uploadButton

        uploadBtn.setOnClickListener {
            findNavController().navigate(R.id.action_postFragment_to_postUploadFragment)
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}