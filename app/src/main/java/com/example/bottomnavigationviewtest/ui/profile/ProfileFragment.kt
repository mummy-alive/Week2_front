package com.example.bottomnavigationviewtest.ui.profile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.bottomnavigationviewtest.R
import com.example.bottomnavigationviewtest.databinding.FragmentProfileBinding
import com.example.bottomnavigationviewtest.viewmodels.ProfileViewModel

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private lateinit var profileViewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        profileViewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)

        // 이메일을 통해 프로필 조회
        val email = "mummyee1024@gmail.com" // 여기에 실제로 조회할 이메일을 구현

        profileViewModel.fetchProfileByEmail(email)

        profileViewModel.profile.observe(viewLifecycleOwner, Observer { profile ->
            profile?.let {
                binding.textEmail.text = it.email
                binding.textClass.text = it.class_tag.toString()
                binding.textMbti.text = it.mbti
                binding.textInterest.text = it.interest
                // binding-tech_tag
            }
        })

        val scrapBtn: TextView = binding.textMenuScrap
        scrapBtn.setOnClickListener {
            Log.d("scrap", "click scrap")
            findNavController().navigate(R.id.action_profileFragment_to_scrapFragment)
        }

        val blockBtn: TextView = binding.textMenuBlock
        blockBtn.setOnClickListener {
            Log.d("block", "click block")
            findNavController().navigate(R.id.action_profileFragment_to_blockFragment)
        }

        val likeBtn: TextView = binding.textMenuLike
        likeBtn.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_likeFragment)
        }


        val myPostBtn: TextView = binding.textMenuMyPost
        myPostBtn.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_myPostFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
