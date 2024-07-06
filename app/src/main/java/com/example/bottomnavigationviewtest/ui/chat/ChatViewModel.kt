<<<<<<<< HEAD:app/src/main/java/com/example/bottomnavigationviewtest/ui/main/notifications/NotificationsViewModel.kt
package com.example.bottomnavigationviewtest.ui.main.notifications
========
package com.example.bottomnavigationviewtest.ui.chat
>>>>>>>> my-profile:app/src/main/java/com/example/bottomnavigationviewtest/ui/chat/ChatViewModel.kt

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ChatViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is notifications Fragment"
    }
    val text: LiveData<String> = _text
}