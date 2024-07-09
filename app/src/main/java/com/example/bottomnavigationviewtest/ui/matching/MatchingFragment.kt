package com.example.bottomnavigationviewtest.ui.matching

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.bottomnavigationviewtest.adapter.CardStackAdapter
import com.example.bottomnavigationviewtest.databinding.FragmentMatchingBinding
import com.example.bottomnavigationviewtest.viewmodels.MatchingViewModel
import com.yuyakaido.android.cardstackview.CardStackLayoutManager
import com.yuyakaido.android.cardstackview.CardStackListener
import com.yuyakaido.android.cardstackview.Direction

class MatchingFragment : Fragment() {

    private var _binding: FragmentMatchingBinding? = null
    private val binding get() = _binding!!

    private val matchingViewModel: MatchingViewModel by viewModels()

    lateinit var cardStackAdapter: CardStackAdapter
    lateinit var manager: CardStackLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment using view binding
        _binding = FragmentMatchingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        manager = CardStackLayoutManager(requireContext(), object : CardStackListener {
            override fun onCardDragging(direction: Direction?, ratio: Float) {}
            override fun onCardSwiped(direction: Direction?) {}
            override fun onCardRewound() {}
            override fun onCardCanceled() {}
            override fun onCardAppeared(view: View?, position: Int) {}
            override fun onCardDisappeared(view: View?, position: Int) {}
        })

        cardStackAdapter = CardStackAdapter(requireContext(), mutableListOf())
        binding.cardStackView.layoutManager = manager
        binding.cardStackView.adapter = cardStackAdapter

        // ViewModel에서 프로필 목록 관찰
        matchingViewModel.autoProfiles.observe(viewLifecycleOwner, Observer { profiles ->
            cardStackAdapter.setProfiles(profiles)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
