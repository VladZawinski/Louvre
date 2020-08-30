
object App {
    const val COMPILE_SDK = 29
    const val MIN_SDK = 21
    const val TARGET_SDK = 29
    const val APPLICATION_ID = "non.shahad.stayhomegallery"
    const val VERSION_CODE = 1
    const val VERSION_NAME = "1.0"

    const val KOTLIN_VERSION = "1.3.61"
    const val GRADLE_VERSION = "3.5.3"

    const val JITPACK = "https://jitpack.io"
    const val JFROG = "https://oss.jfrog.org/libs-snapshot"
}

object Versions {
    const val KTX = "1.2.0"
    const val CONSTRAINT = "1.1.3"
    const val APPCOMPAT = "1.1.0"
    const val MATERIAL = "1.2.0-alpha05"
    const val LEGACY = "1.0.0"
    const val TIMBER = "4.7.1"
    const val DELEGATE = "4.3.0"
    const val FRAGMENT_NAV = "3.2.0"
    const val GLIDE = "4.11.0"
    const val GLIDE_PALETTE = "2.1.2"
    const val DAGGER = "2.26"
    const val COROUTINE = "1.3.5"
    const val CIRCLE_IMAGE_VIEW = "3.1.0"
    const val LIFECYCLE = "2.2.0"
    const val GSON = "2.8.6"
    const val RETROFIT = "2.7.1"
    const val ROOM = "2.2.5"
    const val STORE = "4.0.0-alpha03"
    const val STETHO = "1.5.1"
    const val FRAGMENT_KTX = "1.2.4"
    const val COROUTINE_CACHE = "0.3.0"
}

object BuildPlugins {
    const val KOTLIN = "org.jetbrains.kotlin:kotlin-gradle-plugin:${App.KOTLIN_VERSION}"
    const val GRADLE = "com.android.tools.build:gradle:${App.GRADLE_VERSION}"

    const val ANDROID_APPLICATION = "com.android.application"
    const val KOTLIN_ANDROID = "kotlin-android"
    const val KOTLIN_ANDROID_EXTENSION = "kotlin-android-extensions"
    const val KOTLIN_KAPT = "kotlin-kapt"
}

object Libs {

    const val KOTLIN = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${App.KOTLIN_VERSION}"

    const val CORE_KTX = "androidx.core:core-ktx:${Versions.KTX}"
    const val FRAGMENT_KTX = "androidx.fragment:fragment-ktx:${Versions.FRAGMENT_KTX}"
    const val VIEWMODEL_KTX = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.LIFECYCLE}"
    const val LIVEDATA_KTX = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.LIFECYCLE}"
    const val RUNTIME_KTX = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.LIFECYCLE}"


    const val CONSTRAINT = "androidx.constraintlayout:constraintlayout:${Versions.CONSTRAINT}"
    const val APPCOMPAT = "androidx.appcompat:appcompat:${Versions.APPCOMPAT}"
    const val MATERIAL = "com.google.android.material:material:${Versions.MATERIAL}"
    const val TIMBER = "com.jakewharton.timber:timber:${Versions.TIMBER}"

    const val DELEGATE = "com.hannesdorfmann:adapterdelegates4-kotlin-dsl:${Versions.DELEGATE}"
    const val FRAGMENT_NAV = "com.ncapdevi:frag-nav:${Versions.FRAGMENT_NAV}"
    const val LEGACY = "androidx.legacy:legacy-support-v4:"

    const val GLIDE = "com.github.bumptech.glide:glide:${Versions.GLIDE}"
    const val GLIDE_COMPILER = "com.github.bumptech.glide:compiler:${Versions.GLIDE}"
    const val GLIDE_PALETTE = "com.github.florent37:glidepalette:${Versions.GLIDE_PALETTE}"

    const val DAGGER = "com.google.dagger:dagger:${Versions.DAGGER}"
    const val DAGGER_COMPILER = "com.google.dagger:dagger-compiler:${Versions.DAGGER}"
    const val DAGGER_ANDROID = "com.google.dagger:dagger-android:${Versions.DAGGER}"
    const val DAGGER_ANDROID_SUPPORT = "com.google.dagger:dagger-android-support:${Versions.DAGGER}"
    const val DAGGER_ANNOTATION = "com.google.dagger:dagger-android-processor:${Versions.DAGGER}"

    const val RETROFIT = "com.squareup.retrofit2:retrofit:${Versions.RETROFIT}"
    const val RETROFIT_MOSHI = "com.squareup.retrofit2:converter-moshi:${Versions.RETROFIT}"

    const val CORE_COROUTINE = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.COROUTINE}"
    const val ANDROID_COROUTINE = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.COROUTINE}"

    const val CIRCLE_IMAGE_VIEW = "de.hdodenhof:circleimageview:${Versions.CIRCLE_IMAGE_VIEW}"

    const val GSON = "com.google.code.gson:gson:${Versions.GSON}"

    const val ROOM = "androidx.room:room-runtime:${Versions.ROOM}"
    const val ROOM_COMPILER = "androidx.room:room-compiler:${Versions.ROOM}"
    const val ROOM_KTX = "androidx.room:room-ktx:${Versions.ROOM}"

    const val STORE = "com.dropbox.mobile.store:store4:${Versions.STORE}"

    const val STETHO = "com.facebook.stetho:stetho:${Versions.STETHO}"
    const val STETHO_OKHTTP = "com.facebook.stetho:stetho-okhttp3:${Versions.STETHO}"


    const val COROUTINE_CACHE = "com.github.diefferson:CoroutinesCache:${Versions.COROUTINE_CACHE}"

}

interface BuildType {

    companion object {
        const val RELEASE = "release"
        const val DEBUG = "debug"
    }

    val isMinifyEnabled: Boolean
}

object BuildTypeDebug : BuildType {
    override val isMinifyEnabled = false
}

object BuildTypeRelease : BuildType {
    override val isMinifyEnabled = false
}
