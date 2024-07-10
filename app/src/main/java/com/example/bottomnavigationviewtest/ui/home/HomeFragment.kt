package com.example.bottomnavigationviewtest.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bottomnavigationviewtest.R
import com.example.bottomnavigationviewtest.databinding.FragmentHomeBinding
import com.example.bottomnavigationviewtest.models.profile.Profile
import com.example.bottomnavigationviewtest.models.recruitpost.RecruitPost
import com.example.bottomnavigationviewtest.viewmodels.HomeViewModel
import com.example.bottomnavigationviewtest.ui.matching.CardAdapter

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var autoMatchingAdapter: CardAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        autoMatchingAdapter = CardAdapter(emptyList())

        binding.homeMatchingRecycler.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.homeMatchingRecycler.adapter = autoMatchingAdapter

        homeViewModel.homeData.observe(viewLifecycleOwner, Observer { homeData ->
            if (homeData != null) {
                bindProfileData(homeData.profiles.firstOrNull())
                autoMatchingAdapter.updateProfiles(homeData.profiles)
                bindRecentPosts(homeData.recentPosts.take(4))
            } else {
                Toast.makeText(requireContext(), "데이터를 가져올 수 없습니다.", Toast.LENGTH_SHORT).show()
            }
        })

        homeViewModel.fetchHomeData()
    }

    private fun bindProfileData(profile: Profile?) {
        if (profile != null) {
            binding.textName.text = profile.email
            binding.textEmail.text = profile.email
            binding.textClass.text = profile.class_tag.toString()
            binding.textMbti.text = profile.mbti
            binding.textInterest.text = profile.interest
            binding.textIsRecruit.text = if (profile.is_recruit) "구직 중" else "구직 안함"
            binding.textTech.text = profile.tech_tags.toString()
        }
    }

    private fun bindRecentPosts(posts: List<RecruitPost>) {
        binding.menuContainer.removeAllViews()
        posts.forEach { post ->
            val postView = LayoutInflater.from(requireContext()).inflate(R.layout.item_recruit_post, binding.menuContainer, false)
            val postTitleTextView = postView.findViewById<TextView>(R.id.postTitle)
            val postContentTextView = postView.findViewById<TextView>(R.id.postContent)
            val postWriterTextView = postView.findViewById<TextView>(R.id.postWriter)
            val postDateTextView = postView.findViewById<TextView>(R.id.postDate)
            val postTechTagTextView = postView.findViewById<TextView>(R.id.postTechTag)

            postTitleTextView.text = post.title
            postContentTextView.text = post.content
            postWriterTextView.text = post.writer.username
            postDateTextView.text = post.created_at
            postTechTagTextView.text = post.tech_tags.joinToString(", ") { it.tech_tag_name }

            binding.menuContainer.addView(postView)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

