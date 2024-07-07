plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.example.bottomnavigationviewtest"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.bottomnavigationviewtest"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.androidx.annotation)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation (libs.v2.all) // 전체 모듈 설치, 2.11.0 버전부터 지원
    implementation (libs.v2.user) // 카카오 로그인 API 모듈
    implementation (libs.v2.share) // 카카오톡 공유 API 모듈
    implementation (libs.v2.talk )// 카카오톡 채널, 카카오톡 소셜, 카카오톡 메시지 API 모듈
    implementation(libs.v2.friend)// 피커 API 모듈
    implementation (libs.v2.navi) // 카카오내비 API 모듈
    implementation (libs.v2.cert) // 카카오톡 인증 서 비스 API 모듈

    // 백엔드 통신용
    implementation (libs.retrofit)
    implementation (libs.converter.gson)
}