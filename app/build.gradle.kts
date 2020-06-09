
plugins {
    id(BuildPlugins.ANDROID_APPLICATION)
    id(BuildPlugins.KOTLIN_ANDROID)
    id(BuildPlugins.KOTLIN_ANDROID_EXTENSION)
    id(BuildPlugins.KOTLIN_KAPT)
}

android {
    compileSdkVersion(App.COMPILE_SDK)

    defaultConfig {
        applicationId = App.APPLICATION_ID
        minSdkVersion(App.MIN_SDK)
        targetSdkVersion(App.TARGET_SDK)

        versionCode = App.VERSION_CODE
        versionName = App.VERSION_NAME
//        testInstrumentationRunner = App.TEST_INSTRUMENTATION_RUNNER

    }

    buildTypes {
        getByName(BuildType.RELEASE) {
            isMinifyEnabled = BuildTypeRelease.isMinifyEnabled
            proguardFiles("proguard-android.txt", "proguard-rules.pro")
        }

        getByName(BuildType.DEBUG) {
            isMinifyEnabled = BuildTypeDebug.isMinifyEnabled
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = JavaVersion.VERSION_1_8
        }

        dataBinding.isEnabled = true

    }


    withGroovyBuilder {
        "kotlinOptions"{
            setProperty("jvmTarget","1.8")
        }
    }

}


dependencies {

    implementation(Libs.KOTLIN)
    implementation(Libs.APPCOMPAT)
    implementation(Libs.CONSTRAINT)

    implementation(Libs.CORE_KTX)
    implementation(Libs.FRAGMENT_KTX)
    implementation(Libs.VIEWMODEL_KTX)
    implementation(Libs.LIVEDATA_KTX)
    implementation(Libs.RUNTIME_KTX)
    implementation(Libs.MATERIAL)
    implementation(Libs.TIMBER)
    implementation(Libs.DELEGATE)
    implementation(Libs.FRAGMENT_NAV)

    implementation(Libs.GLIDE)
    annotationProcessor(Libs.GLIDE_COMPILER)
    implementation(Libs.GLIDE_PALETTE)

    implementation(Libs.GSON)

    implementation(Libs.DAGGER)
    implementation(Libs.DAGGER_ANDROID)
    implementation(Libs.DAGGER_ANDROID_SUPPORT)
    kapt(Libs.DAGGER_ANNOTATION)
    kapt(Libs.DAGGER_COMPILER)

    implementation(Libs.RETROFIT)
    implementation(Libs.RETROFIT_MOSHI)

    implementation(Libs.CORE_COROUTINE)
    implementation(Libs.ANDROID_COROUTINE)

    implementation(Libs.CIRCLE_IMAGE_VIEW)

    implementation(Libs.ROOM)
    kapt(Libs.ROOM_COMPILER)
    implementation(Libs.ROOM_KTX)

    implementation(Libs.STORE)

    implementation(Libs.STETHO)
    implementation(Libs.STETHO_OKHTTP)


}