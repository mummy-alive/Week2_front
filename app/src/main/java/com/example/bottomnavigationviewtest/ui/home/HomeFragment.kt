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
import com.example.bottomnavigationviewtest.ui.adapter.CardAdapter
import com.example.bottomnavigationviewtest.viewmodels.HomeViewModel

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

        // Observe for home data
        homeViewModel.homeData.observe(viewLifecycleOwner, Observer { homeData ->
            if (homeData != null) {
                autoMatchingAdapter.updateProfiles(homeData.profiles)
                bindRecentPosts(homeData.recentPosts.take(4))
            } else {
                Toast.makeText(requireContext(), "데이터를 가져올 수 없습니다.", Toast.LENGTH_SHORT).show()
            }
        })

        // Observe for profile data
        homeViewModel.profile.observe(viewLifecycleOwner, Observer { profile ->
            bindProfileData(profile)
        })

        // Fetch data
        // Fetch data
        homeViewModel.fetchHomeData()
        val email = MyPreferences.getEmail(requireContext()) // SharedPreferences에서 이메일 가져오기
        if (email != null) {
            homeViewModel.fetchProfile(email)
        } else {
            Toast.makeText(requireContext(), "이메일을 가져올 수 없습니다.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun bindProfileData(profile: Profile?) {
        if (profile != null) {
            binding.textName.text = profile.email
            binding.textEmail.text = profile.email
            binding.textClass.text = "${profile.class_tag}분반"
            binding.textMbti.text = profile.mbti
            binding.textInterest.text = profile.interest
            binding.textIsRecruit.text = if (profile.is_recruit) "구직 중" else "구직 안함"
            binding.textTech.text = profile.tech_tags.joinToString(", ") { techTagId ->
                techTagsMap[techTagId] ?: "알 수 없음"
            }
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

    companion object {
        private val techTagsMap = mapOf(
            1 to "프론트엔드",
            2 to "백엔드",
            3 to "앱개발",
            4 to "웹개발",
            5 to "데이터분석",
            6 to "인공지능",
            7 to "하드웨어",
            8 to "OS",
            9 to "자바",
            10 to "파이썬",
            11 to "C / C++",
            12 to "C#",
            13 to "Kotlin",
            14 to "HTML/CSS",
            15 to "UI 디자인"
        )
    }
}
