package com.example.bottomnavigationviewtest.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bottomnavigationviewtest.R
import com.example.bottomnavigationviewtest.databinding.FragmentLikeBinding

class LikeFragment : Fragment() {
 /*   private var _binding: FragmentLikeBinding? = null
    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView
    private lateinit var likeAdapter: LikeAdapter
    private val viewModel: LikeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLikeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = binding.likeRecycler
        recyclerView.layoutManager = LinearLayoutManager(context)

        likeAdapter = LikeAdapter(emptyList(), viewModel::deleteLike)
        recyclerView.adapter = likeAdapter

        viewModel.profiles.observe(viewLifecycleOwner, Observer { profiles ->
            likeAdapter = LikeAdapter(profiles, viewModel::deleteLike)
            recyclerView.adapter = likeAdapter
        })

        viewModel.fetchLikesByFromId(1) // 예시로 userId 1의 좋아요 목록을 가져옴
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }*/
}
