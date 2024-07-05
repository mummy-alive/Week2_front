package com.example.bottomnavigationviewtest.ui.main.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.bottomnavigationviewtest.R
import com.example.bottomnavigationviewtest.databinding.FragmentMatchingBinding
import com.example.bottomnavigationviewtest.databinding.FragmentProfileBinding
import com.example.bottomnavigationviewtest.ui.matching.ProfileViewModel

class ProfileFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var _binding: FragmentProfileBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val matchingViewModel =
            ViewModelProvider(this).get(ProfileViewModel::class.java)

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textProfile
        matchingViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

}