package com.example.bottomnavigationviewtest.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bottomnavigationviewtest.R
import com.example.bottomnavigationviewtest.adapter.HomeMatchingAdapter
import com.example.bottomnavigationviewtest.adapter.HomeRecruitAdapter
import com.example.bottomnavigationviewtest.adapter.RecruitPostAdapter
import com.example.bottomnavigationviewtest.models.MatchingProfile
import com.example.bottomnavigationviewtest.models.RecruitPost
import org.w3c.dom.Text

class HomeFragment : Fragment() {

    private lateinit var postRecyclerView: RecyclerView
    private lateinit var postAdapter: HomeRecruitAdapter
    private lateinit var postContainer: LinearLayout
    private lateinit var matchingRecyclerView: RecyclerView
    private lateinit var matchingAdapter: HomeMatchingAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
/*
        // 구인글 리싸이클러뷰
        // 리싸이클러뷰가 아니라 최신 5개만 뽑아옴 + 더보기 버튼
        postRecyclerView = view.findViewById(R.id.recruit_post_recycler)
        postRecyclerView.layoutManager = LinearLayoutManager(context)*/

        // 샘플 데이터 생성, db에서 최근 네 개만 뽑기
        val jobPostings = listOf(
            RecruitPost("Software Engineer", "Develop amazing apps", "Tech Company", "Seoul"),
            RecruitPost("Product Manager", "Manage product lifecycle", "Startup", "Busan"),
            RecruitPost("Software Engineer", "Develop amazing apps", "Tech Company", "Seoul"),
            RecruitPost("Product Manager", "Manage product lifecycle", "Startup", "Busan"),
        )
/*
        // 적용
        postAdapter = RecruitPostAdapter(jobPostings)
        postRecyclerView.adapter = postAdapter*/

        // 구인글 컨테이너 설정
        postContainer = view.findViewById(R.id.postContianer)
        // 적용
        postAdapter = HomeRecruitAdapter(requireContext(), jobPostings)
        postAdapter.addPostsToContainer(postContainer)
        // 구인글 더보기 버튼 설정
        val btnMorePost: TextView = view.findViewById(R.id.btnMorePost)
        btnMorePost.setOnClickListener {
            val navController = findNavController()
            navController.navigate(R.id.navigation_recruitpost)
        }

        // 추천매칭 리싸이클러뷰
        matchingRecyclerView = view.findViewById(R.id.home_matching_recycler)
        matchingRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        // 샘플 데이터 생성
        val matchingSample = listOf(
            MatchingProfile(name = "Alice", mbti = "INFJ", img_url = -1, interest = "Reading, Traveling"),
            MatchingProfile(name = "Bob", mbti = "ENFP", img_url = -1, interest = "Cooking, Hiking"),
            MatchingProfile(name = "Alice", mbti = "INFJ", img_url = -1, interest = "Reading, Traveling"),
            MatchingProfile(name = "Bob", mbti = "ENFP", img_url = -1, interest = "Cooking, Hiking"),
            MatchingProfile(name = "Alice", mbti = "INFJ", img_url = -1, interest = "Reading, Traveling"),
            MatchingProfile(name = "Bob", mbti = "ENFP", img_url = -1, interest = "Cooking, Hiking"),
        )

        // 적용
        matchingAdapter = HomeMatchingAdapter(matchingSample)
        matchingRecyclerView.adapter = matchingAdapter

        matchingRecyclerView.setOnClickListener{
            // 매칭카드 스와이프
        }

        return view
    }
}