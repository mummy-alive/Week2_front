package com.example.bottomnavigationviewtest.ui.profile

import BlockViewModel
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
import com.example.bottomnavigationviewtest.adapter.BlockAdapter
import com.example.bottomnavigationviewtest.databinding.FragmentBlockBinding

class BlockFragment : Fragment() {
    private var _binding: FragmentBlockBinding? = null
    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView
    private lateinit var blockAdapter: BlockAdapter
    private val viewModel: BlockViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBlockBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = binding.blockRecycler
        recyclerView.layoutManager = LinearLayoutManager(context)

        blockAdapter = BlockAdapter(emptyList(), viewModel::deleteBlock)
        recyclerView.adapter = blockAdapter

        viewModel.profiles.observe(viewLifecycleOwner, Observer { profiles ->
            blockAdapter = BlockAdapter(profiles, viewModel::deleteBlock)
            recyclerView.adapter = blockAdapter
        })

        viewModel.fetchBlocksByFromId(1) // 예시로 userId 1의 블락목록을 가져옴
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
