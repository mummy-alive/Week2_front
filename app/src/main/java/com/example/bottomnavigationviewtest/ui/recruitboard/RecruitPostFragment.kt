package com.example.bottomnavigationviewtest.ui.recruitboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bottomnavigationviewtest.R
import com.example.bottomnavigationviewtest.adapter.RecruitPostAdapter
import com.example.bottomnavigationviewtest.databinding.FragmentRecruitpostBinding
import com.example.bottomnavigationviewtest.models.RecruitPost

class RecruitPostFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RecruitPostAdapter

    private var _binding: FragmentRecruitpostBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_recruitpost, container, false)
        recyclerView = view.findViewById(R.id.postRecycler)
        recyclerView.layoutManager = LinearLayoutManager(context)

        // sampledata
        val jobPostings = listOf(
            RecruitPost("Data Scientist", "Analyze data", "writer", "date"),
            RecruitPost("UX Designer", "Design user experiences", "writer", "date"),
            RecruitPost("Data Scientist", "Analyze data", "writer", "date"),
            RecruitPost("UX Designer", "Design user experiences", "writer", "date"),
            RecruitPost("Data Scientist", "Analyze data", "writer", "date"),
            RecruitPost("UX Designer", "Design user experiences", "writer", "date"),
            RecruitPost("Data Scientist", "Analyze data", "writer", "date"),
            RecruitPost("UX Designer", "Design user experiences", "writer", "date"),
            RecruitPost("Data Scientist", "Analyze data", "writer", "date"),
            RecruitPost("UX Designer", "Design user experiences", "writer", "date"),
            RecruitPost("Data Scientist", "Analyze data", "writer", "date"),
            RecruitPost("UX Designer", "Design user experiences", "writer", "date"),
            RecruitPost("Data Scientist", "Analyze data", "writer", "date"),
            RecruitPost("UX Designer", "Design user experiences", "writer", "date"),
            RecruitPost("Data Scientist", "Analyze data", "writer", "date"),
            RecruitPost("UX Designer", "Design user experiences", "writer", "date"),
            RecruitPost("Data Scientist", "Analyze data", "writer", "date"),
            RecruitPost("UX Designer", "Design user experiences", "writer", "date"),
            RecruitPost("Data Scientist", "Analyze data", "writer", "date"),
            RecruitPost("UX Designer", "Design user experiences", "writer", "date"),
            RecruitPost("Data Scientist", "Analyze data", "writer", "date"),
            RecruitPost("UX Designer", "Design user experiences", "writer", "date"),
            RecruitPost("Data Scientist", "Analyze data", "writer", "date"),
            RecruitPost("UX Designer", "Design user experiences", "writer", "date"),
            RecruitPost("Data Scientist", "Analyze data", "writer", "date"),
            RecruitPost("UX Designer", "Design user experiences", "writer", "date"),
        )/*

        val recruitPostViewModel =
            ViewModelProvider(this).get(RecruitPostViewModel::class.java)

        _binding = FragmentRecruitpostBinding.inflate(inflater, container, false)
        val root: View = binding.root*/

        adapter = RecruitPostAdapter(jobPostings)
        recyclerView.adapter = adapter


        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}