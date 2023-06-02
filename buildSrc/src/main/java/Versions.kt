import org.gradle.api.JavaVersion

object Versions {
    val javaVersion = JavaVersion.VERSION_1_8

    object GradlePlugin {
        const val ANDROID = "7.4.2"
        const val KOTLIN = "1.8.0"
        const val NAV_SAFE_ARGS = "2.5.3"
        const val HILT = "2.44"
    }

    object Kotlin {
        const val COROUTINE = "1.6.4"
    }

    object AndroidX {
        const val CORE = "1.10.0"
        const val APP_COMPAT = "1.6.1"
        const val RECYCLERVIEW = "1.3.0"
        const val CONSTRAINT_LAYOUT = "2.1.4"
        const val ACTIVITY = "1.7.0"
        const val FRAGMENT = "1.5.6"
        const val LIFECYCLE = "2.6.1"
        const val ROOM = "2.4.3"
        const val NAVIGATION = "2.5.3"
    }

    object Google {
        const val MATERIAL = "1.8.0"
        const val HILT = "2.44"
    }

    object Retrofit {
        const val RETROFIT = "2.9.0"
        const val GSON_CONVERTER = "2.9.0"
    }

    object OKHttp {
        const val OKHTTP = "4.8.1"
        const val OKHTTP_LOGGING = "4.8.1"
    }

    object Other {
        const val GLIDE = "4.11.0"
        const val STATE_LAYOUT = "1.5.0"
    }

    object Test {
        const val JUNIT = "4.13.2"
        const val JUNIT_EXT = "1.1.5"
        const val ESPRESSO = "3.5.1"
    }
}