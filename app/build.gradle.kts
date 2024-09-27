import java.util.Properties

plugins {
  alias(libs.plugins.android.application)
  alias(libs.plugins.jetbrains.kotlin.android)
  id("com.google.devtools.ksp")
}

android {
  namespace = "com.example.unsplashapp"
  compileSdk = 34
  
  defaultConfig {
    applicationId = "com.example.unsplashapp"
    minSdk = 24
    targetSdk = 34
    versionCode = 1
    versionName = "1.0"
    
    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }
  
  buildTypes {
    debug {
      // -> read local.properties file containing UNSPLASH_ACCESS_KEY
      val properties: Properties = Properties().apply {
        load(rootProject.file("local.properties").inputStream())
      }
      val unsplashAccessKey: String = checkNotNull(properties.getProperty("UNSPLASH_ACCESS_KEY")) {
        "UNSPLASH_ACCESS_KEY is not set in local.properties"
      }
      
      // -> write UNSPLASH_ACCESS_KEY to build gradle
      buildConfigField(
        type = "String", name = "UNSPLASH_ACCESS_KEY", value = "\"$unsplashAccessKey\""
      )
    }
    
    release {
      isMinifyEnabled = false
      proguardFiles(
        getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
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
    buildConfig = true
  }
}

dependencies {
  
  implementation(libs.androidx.core.ktx)
  implementation(libs.androidx.appcompat)
  implementation(libs.material)
  implementation(libs.androidx.activity)
  implementation(libs.androidx.constraintlayout)
  testImplementation(libs.junit)
  androidTestImplementation(libs.androidx.junit)
  androidTestImplementation(libs.androidx.espresso.core)
  
  implementation(libs.androidx.lifecycle.viewmodel.ktx)
  implementation(libs.androidx.activity.ktx)
  implementation(libs.androidx.fragment.ktx)
  implementation(libs.androidx.lifecycle.livedata.ktx)
  implementation(libs.androidx.lifecycle.runtime.ktx)
  implementation(libs.androidx.lifecycle.viewmodel.savedstate)
  
  // Glide
  implementation(libs.glide)
  implementation(libs.okhttp3.integration)
  ksp(libs.ksp)
  
  // Room
  implementation(libs.androidx.room.runtime)
  implementation(libs.androidx.room.ktx) // Suspend Function and Coroutines Flow
  // KSP or KAPT
  // kapt: kotlin annotation processing tool
  ksp(libs.androidx.room.compiler)
  
  // Moshi
  implementation(libs.moshi)
  implementation(libs.moshi.kotlin)
  
  // OkHttp
  implementation(platform(libs.okhttp.bom))
  implementation(libs.okhttp)
  implementation(libs.logging.interceptor)
  
  // Gson
  implementation(libs.gson)
  
  // Retrofit
  implementation(libs.retrofit)
  implementation(libs.converter.gson)
  implementation(libs.converter.moshi)
  implementation(libs.rxjava3.retrofit.adapter)
  
  // RxJava3 and RxAndroid
  implementation(libs.rxjava)
  implementation(libs.rxandroid)
  implementation(libs.kotlinx.coroutines.rx2)
}