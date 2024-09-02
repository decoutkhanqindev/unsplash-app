package com.example.unsplashapp

import androidx.annotation.MainThread
import com.example.unsplashapp.data.remote.UnsplashApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

object UnsplashServiceLocator {
    const val BASE_URL: String = "https://api.unsplash.com"

    // @MainThread
    private var _application: UnsplashApplication? = null

    @MainThread // -> indicate that a particular method or piece of code should be executed on the main thread.
    fun initWith(application: UnsplashApplication) {
        _application = application
    }

    @get:MainThread
    // indicate that a function or property should be accessed or executed on the main thread.
    // Using @get:MainThread ensures that the annotated function or property is accessed on the main thread,
    // helping to avoid such issues.
    val application: UnsplashApplication
        get() = checkNotNull(_application) {
            "UnsplashServiceLocator must be initialized. " + "Call UnsplashServiceLocator.initWith(this) in your Application class."
        }

    private val moshi: Moshi by lazy {
        Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
    }

    // setting up an HttpLoggingInterceptor for logging HTTP requests and responses.
    private val httpLoggingInterceptor: HttpLoggingInterceptor
        get() = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    val okHttpClient: OkHttpClient by lazy { TODO() }

    private val retrofit: Retrofit by lazy { TODO() }

    val unsplashService: UnsplashApiService by lazy { TODO() }
}