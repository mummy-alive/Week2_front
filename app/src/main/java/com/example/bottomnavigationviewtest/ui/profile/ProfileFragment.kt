package com.example.bottomnavigationviewtest.ui.profile

import MyPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.bottomnavigationviewtest.R
import com.example.bottomnavigationviewtest.databinding.FragmentProfileBinding
import com.example.bottomnavigationviewtest.viewmodels.ProfileViewModel

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private var profileViewModel: ProfileViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        profileViewModel.profile.observe(viewLifecycleOwner, Observer { profile ->
            if (profile != null) {
                bindProfileData(profile)
            } else {
                Toast.makeText(requireContext(), "프로필을 가져올 수 없습니다.", Toast.LENGTH_SHORT).show()
        })

        binding.textMenuScrap.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_scrapFragment)
        }

        binding.textMenuBlock.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_blockFragment)
        }

        binding.textMenuLike.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_likeFragment)
        }


        binding.textMenuMyPost.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_myPostFragment)
        }

        binding.textMenuLogout.setOnClickListener {
            // 로그아웃 로직을 여기에 추가합니다.
            Toast.makeText(requireContext(), "로그아웃 되었습니다.", Toast.LENGTH_SHORT).show()
        }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

