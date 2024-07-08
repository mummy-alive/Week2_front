package com.example.bottomnavigationviewtest.ui.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bottomnavigationviewtest.R
import com.example.bottomnavigationviewtest.adapter.ChatAdapter
import com.example.bottomnavigationviewtest.databinding.FragmentChatBinding
import com.example.bottomnavigationviewtest.viewmodels.ChatViewModel
import com.example.bottomnavigationviewtest.viewmodels.PostViewModel

class ChatFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ChatAdapter

    private var _binding: FragmentChatBinding? = null
    private lateinit var postViewModel: ChatViewModel

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChatBinding.inflate(inflater, container, false)
        val view = binding.root
        recyclerView = binding.chatRecycler
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = ChatAdapter(emptyList())
        recyclerView.adapter = adapter

        postViewModel = ViewModelProvider(this).get(ChatViewModel::class.java)
        postViewModel.allChats.observe(viewLifecycleOwner, Observer { chats ->
            chats?.let {
                adapter.updateData(it)
            }
        })

        // 데이터 가져오기
        postViewModel.fetchChats()

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}