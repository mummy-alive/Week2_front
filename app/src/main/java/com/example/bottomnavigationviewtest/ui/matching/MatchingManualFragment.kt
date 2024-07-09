package com.example.bottomnavigationviewtest.ui.matching

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.bottomnavigationviewtest.databinding.FragmentMatchingManualBinding
import com.example.bottomnavigationviewtest.viewmodels.MatchingViewModel

class MatchingManualFragment : Fragment() {

    private var _binding: FragmentMatchingManualBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MatchingViewModel
    private lateinit var adapter: HomeMatchingAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMatchingManualBinding.inflate(inflater, container, false)
        return binding.root
    }

/*
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(MatchingViewModel::class.java)
        binding.profileRecyclerview.layoutManager = LinearLayoutManager(requireContext())

        viewModel.profiles.observe(viewLifecycleOwner, Observer { profiles ->
            adapter = HomeMatchingAdapter(profiles)
            binding.profileRecyclerview.adapter = adapter
        })
    }
*/

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
