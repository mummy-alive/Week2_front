package com.example.bottomnavigationviewtest.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bottomnavigationviewtest.databinding.FragmentScrapBinding
import com.example.bottomnavigationviewtest.ui.adapter.ScrapAdapter
import com.example.bottomnavigationviewtest.viewmodels.ScrapViewModel

class ScrapFragment : Fragment() {
    private var _binding: FragmentScrapBinding? = null
    private val binding get() = _binding!!
    private val scrapViewModel: ScrapViewModel by viewModels()
    private lateinit var scrapAdapter: ScrapAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentScrapBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        scrapAdapter = ScrapAdapter(emptyList())

        binding.scrapRecycler.layoutManager = LinearLayoutManager(requireContext())
        binding.scrapRecycler.adapter = scrapAdapter

        // Observe for scrap data
        scrapViewModel.scrapPosts.observe(viewLifecycleOwner, Observer { posts ->
            if (posts != null) {
                scrapAdapter.updatePosts(posts)
            } else {
                Toast.makeText(requireContext(), "데이터를 가져올 수 없습니다.", Toast.LENGTH_SHORT).show()
            }
        })

        // Fetch data
        scrapViewModel.fetchScrapPosts()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
