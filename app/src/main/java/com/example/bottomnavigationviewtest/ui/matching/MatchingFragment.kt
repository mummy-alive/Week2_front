package com.example.bottomnavigationviewtest.ui.matching

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.bottomnavigationviewtest.databinding.FragmentMatchingBinding
import com.example.bottomnavigationviewtest.viewmodels.MatchingViewModel
import com.yuyakaido.android.cardstackview.CardStackLayoutManager
import com.yuyakaido.android.cardstackview.CardStackView
import com.yuyakaido.android.cardstackview.Direction

class MatchingFragment : Fragment() {
    private var _binding: FragmentMatchingBinding? = null
    private val binding get() = _binding!!
    private val matchingViewModel: MatchingViewModel by viewModels()
    private lateinit var adapter: CardAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMatchingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val cardStackView = binding.cardStackView
        val manager = CardStackLayoutManager(requireContext())
        adapter = CardAdapter(emptyList())

        cardStackView.layoutManager = manager
        cardStackView.adapter = adapter

        val email = MyPreferences.getEmail(requireContext())
        if (email != null) {
            matchingViewModel.fetchProfiles(email)
        } else {
            Log.d("no email check", "")
        }
            matchingViewModel.profiles.observe(viewLifecycleOwner, Observer { profiles ->
                if (profiles != null) {
                    adapter.updateProfiles(profiles)
                } else {
                    Toast.makeText(requireContext(), "프로필을 가져올 수 없습니다.", Toast.LENGTH_SHORT).show()
                }
            })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

