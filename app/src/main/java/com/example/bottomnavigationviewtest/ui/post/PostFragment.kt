package com.example.bottomnavigationviewtest.ui.post

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bottomnavigationviewtest.R
import com.example.bottomnavigationviewtest.adapter.RecruitPostAdapter
import com.example.bottomnavigationviewtest.databinding.FragmentPostBinding

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
        val view = inflater.inflate(R.layout.fragment_post, container, false)
        recyclerView = view.findViewById(R.id.postRecycler)
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


        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}