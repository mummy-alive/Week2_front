package com.example.bottomnavigationviewtest

import android.app.Application
import com.kakao.sdk.common.KakaoSdk

class GlobalApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        // Kakao SDK 초기화
        KakaoSdk.init(this, "00c4d79d62c937febca56680acdb32fd")
    }
}