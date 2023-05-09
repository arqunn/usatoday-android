// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
    }
    dependencies {
        classpath(Plugins.NAVIGATION_SAFE_ARGS_CLASSPATH)
    }
}

plugins {
    id(Plugins.ANDROID_APPLICATION) version Versions.GradlePlugin.ANDROID apply false
    id(Plugins.ANDROID_LIBRARY) version Versions.GradlePlugin.ANDROID apply false
    id(Plugins.KOTLIN_ANDROID) version Versions.GradlePlugin.KOTLIN apply false
    id(Plugins.HILT_ANDROID) version Versions.GradlePlugin.HILT apply false
}