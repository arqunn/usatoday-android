plugins {
    id(Plugins.ANDROID_APPLICATION)
    id(Plugins.KOTLIN_ANDROID)
    kotlin(Plugins.KOTLIN_KAPT)
    id(Plugins.NAVIGATION_SAFE_ARGS)
    id(Plugins.HILT_ANDROID)
    id(Plugins.KOTLIN_PARCELIZE)
}
@Suppress("UnstableApiUsage")
android {
    namespace = ConfigData.namespace
    compileSdk = ConfigData.compileSdk

    defaultConfig {
        applicationId = ConfigData.appId
        minSdk = ConfigData.minSdk
        targetSdk = ConfigData.targetSdk
        versionCode = ConfigData.versionCode
        versionName = ConfigData.versionName
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
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
        jvmTarget = Versions.javaVersion.toString()
    }
    buildFeatures {
        dataBinding = true
    }
}

dependencies {

    appCompat()
    material()
    activity()
    fragment()
    lifecycle()
    constraintLayout()
    recyclerView()
    room()
    navigation()
    coroutine()
    hilt()

    testImplementations()
}