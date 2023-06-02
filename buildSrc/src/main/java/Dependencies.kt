import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.appCompat() {
    implementation(Libs.ANDROID_APP_COMPAT)
    implementation(Libs.ANDROID_CORE_KTX)
}

fun DependencyHandler.activity() = implementation(Libs.ANDROID_ACTIVITY)
fun DependencyHandler.fragment() = implementation(Libs.ANDROID_FRAGMENT)
fun DependencyHandler.constraintLayout() = implementation(Libs.ANDROID_CONSTRAINT_LAYOUT)
fun DependencyHandler.recyclerView() = implementation(Libs.ANDROID_RECYCLER_VIEW)
fun DependencyHandler.material() = implementation(Libs.GOOGLE_MATERIAL)
fun DependencyHandler.glide() = implementation(Libs.GLIDE)
fun DependencyHandler.stateLayout() = implementation(Libs.STATE_LAYOUT)

fun DependencyHandler.lifecycle() {
    implementation(Libs.ANDROID_VIEW_MODEL)
    implementation(Libs.ANDROID_LIFECYCLE_RUNTIME)
}

fun DependencyHandler.room() {
    implementation(Libs.ROOM_RUNTIME)
    kapt(Libs.ROOM_COMPILER)
    implementation(Libs.ROOM_KTX)
}

fun DependencyHandler.navigation() {
    implementation(Libs.NAVIGATION)
    implementation(Libs.NAVIGATION_UI)
}

fun DependencyHandler.coroutine() {
    implementation(Libs.COROUTINES_CORE)
    implementation(Libs.COROUTINES_ANDROID)
}

fun DependencyHandler.hilt() {
    implementation(Libs.HILT_ANDROID)
    kapt(Libs.HILT_ANDROID_COMPILER)
}

fun DependencyHandler.retrofit() {
    implementation(Libs.NETWORK_RETROFIT)
    implementation(Libs.NETWORK_RETROFIT_CONVERTER_GSON)
}

fun DependencyHandler.okHttp() {
    implementation(Libs.NETWORK_OKHTTP)
    implementation(Libs.NETWORK_OKHTTP_LOGGING)
}

fun DependencyHandler.testImplementations() {
    testImplementation(Libs.JUNIT)
    androidTestImplementation(Libs.JUNIT_EXT)
    androidTestImplementation(Libs.ESPRESSO)
}

@Suppress("detekt.UnusedPrivateMember")
private fun DependencyHandler.implementation(dependencyNotation: Any): Dependency? =
    add("implementation", dependencyNotation)

@Suppress("detekt.UnusedPrivateMember")
private fun DependencyHandler.api(dependencyNotation: Any): Dependency? =
    add("api", dependencyNotation)

@Suppress("detekt.UnusedPrivateMember")
private fun DependencyHandler.kapt(dependencyNotation: Any): Dependency? =
    add("kapt", dependencyNotation)

private fun DependencyHandler.testImplementation(dependencyNotation: Any): Dependency? =
    add("testImplementation", dependencyNotation)

private fun DependencyHandler.androidTestImplementation(dependencyNotation: Any): Dependency? =
    add("androidTestImplementation", dependencyNotation)
