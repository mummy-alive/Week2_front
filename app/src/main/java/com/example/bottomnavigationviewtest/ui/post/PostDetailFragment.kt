package com.example.bottomnavigationviewtest.ui.postdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.bottomnavigationviewtest.databinding.FragmentPostDetailBinding

class PostDetailFragment : Fragment() {
/*
    private var _binding: FragmentPostDetailBinding? = null
    private val binding get() = _binding!!
    private val args: PostDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPostDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val post = args.post

        binding.titleTextView.text = post.title
        binding.writerTextView.text = post.writer.name
        binding.createdAtTextView.text = post.created_at.split("T")[0]
        binding.contentTextView.text = post.content
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }*/
}
