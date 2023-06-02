object Libs {
    // Coroutines
    const val COROUTINES_CORE =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.Kotlin.COROUTINE}"
    const val COROUTINES_ANDROID =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.Kotlin.COROUTINE}"

    // AndroidX
    const val ANDROID_CORE_KTX = "androidx.core:core-ktx:${Versions.AndroidX.CORE}"
    const val ANDROID_APP_COMPAT = "androidx.appcompat:appcompat:${Versions.AndroidX.APP_COMPAT}"
    const val ANDROID_ACTIVITY = "androidx.activity:activity-ktx:${Versions.AndroidX.ACTIVITY}"
    const val ANDROID_FRAGMENT = "androidx.fragment:fragment-ktx:${Versions.AndroidX.FRAGMENT}"

    // AndroidX - LifeCycle
    const val ANDROID_VIEW_MODEL =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.AndroidX.LIFECYCLE}"
    const val ANDROID_LIFECYCLE_RUNTIME =
        "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.AndroidX.LIFECYCLE}"

    // AndroidX - Room
    const val ROOM_RUNTIME = "androidx.room:room-runtime:${Versions.AndroidX.ROOM}"
    const val ROOM_COMPILER = "androidx.room:room-compiler:${Versions.AndroidX.ROOM}"
    const val ROOM_KTX = "androidx.room:room-ktx:${Versions.AndroidX.ROOM}"

    // AndroidX - Navigation
    const val NAVIGATION =
        "androidx.navigation:navigation-fragment-ktx:${Versions.AndroidX.NAVIGATION}"
    const val NAVIGATION_UI =
        "androidx.navigation:navigation-ui-ktx:${Versions.AndroidX.NAVIGATION}"

    // AndroidX - View
    const val ANDROID_RECYCLER_VIEW =
        "androidx.recyclerview:recyclerview:${Versions.AndroidX.RECYCLERVIEW}"
    const val ANDROID_CONSTRAINT_LAYOUT =
        "androidx.constraintlayout:constraintlayout:${Versions.AndroidX.CONSTRAINT_LAYOUT}"

    // Google
    const val GOOGLE_MATERIAL = "com.google.android.material:material:${Versions.Google.MATERIAL}"

    // Google - Dagger-Hilt
    const val HILT_ANDROID = "com.google.dagger:hilt-android:${Versions.Google.HILT}"
    const val HILT_ANDROID_COMPILER =
        "com.google.dagger:hilt-android-compiler:${Versions.Google.HILT}"

    // Test
    const val JUNIT = "junit:junit:${Versions.Test.JUNIT}"

    // AndroidX - Test
    const val JUNIT_EXT = "androidx.test.ext:junit:${Versions.Test.JUNIT_EXT}"
    const val ESPRESSO = "androidx.test.espresso:espresso-core:${Versions.Test.ESPRESSO}"

    // Network
    const val NETWORK_RETROFIT = "com.squareup.retrofit2:retrofit:${Versions.Retrofit.RETROFIT}"
    const val NETWORK_RETROFIT_CONVERTER_GSON =
        "com.squareup.retrofit2:converter-gson:${Versions.Retrofit.GSON_CONVERTER}"

    const val NETWORK_OKHTTP = "com.squareup.okhttp3:okhttp:${Versions.OKHttp.OKHTTP}"
    const val NETWORK_OKHTTP_LOGGING =
        "com.squareup.okhttp3:logging-interceptor:${Versions.OKHttp.OKHTTP_LOGGING}"

    // Other
    const val GLIDE = "com.github.bumptech.glide:glide:${Versions.Other.GLIDE}"
    const val STATE_LAYOUT = "com.github.erkutaras:StateLayout:${Versions.Other.STATE_LAYOUT}"
}