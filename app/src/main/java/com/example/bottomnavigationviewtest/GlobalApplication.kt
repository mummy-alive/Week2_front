package com.example.bottomnavigationviewtest

import android.app.Application
import android.content.Context
import com.kakao.sdk.common.KakaoSdk

class GlobalApplication : Application() {
    init {
        instance = this
    }

    companion object {
        private var instance: GlobalApplication? = null

        fun applicationContext(): Context {
            return instance!!.applicationContext
        }
    }


    override fun onCreate() {
        super.onCreate()

        // Kakao SDK 초기화
        KakaoSdk.init(this, "00c4d79d62c937febca56680acdb32fd")
    }
}