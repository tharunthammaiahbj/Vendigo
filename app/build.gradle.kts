plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    //downloading kapt
    kotlin("kapt")
    //dagger-hilt
    id("com.google.dagger.hilt.android")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.vendigo"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.vendigo"
        minSdk = 27
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {


    //dagger hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
    implementation(libs.androidx.hilt.navigation.compose)

    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    //firebase//
     implementation(libs.firebase.auth)
     // Import the BoM for the Firebase platform
     implementation(platform(libs.firebase.bom))
     //noinspection UseTomlInstead
     implementation("com.google.firebase:firebase-auth")
    //noinspection UseTomlInstead
    implementation("com.google.firebase:firebase-analytics")

    //coroutine
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.play.services)

    //coil
    implementation(libs.coil.compose)

    //viewmodel lifecycle scope

    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.compose)


    //lifecycle
    implementation(libs.androidx.lifecycle.runtime.ktx.v240)

    //retrofit
    implementation(libs.retrofit)

    //Json Converter
    implementation(libs.converter.gson)

    //OkHttp
    implementation(libs.okhttp)

    //Room
//    val room_version = "2.6.1"
    implementation(libs.androidx.room.runtime)
    annotationProcessor(libs.androidx.room.compiler)

    // To use Kotlin annotation processing tool (kapt)
    kapt(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)

    //Google Fonts
    implementation(libs.androidx.ui.text.google.fonts)

    //splash screen
    implementation(libs.androidx.core.splashscreen)

    //Material3
    implementation(libs.material3)
    implementation(libs.androidx.foundation)
    implementation("androidx.compose.material3:material3-window-size-class:1.2.1")
    implementation(libs.androidx.material3.adaptive.navigation.suite)
    implementation(libs.androidx.material.icons.extended)

    //inbuilt implementations

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)


}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}