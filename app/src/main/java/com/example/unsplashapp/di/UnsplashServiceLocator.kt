package com.example.unsplashapp.di

import android.app.Application
import androidx.annotation.MainThread
import com.example.unsplashapp.BuildConfig
import com.example.unsplashapp.UnsplashApplication
import com.example.unsplashapp.data.remote.UnsplashApiService
import com.example.unsplashapp.data.remote.interceptor.AuthorizationInterceptor
import com.example.unsplashapp.data.remote.repository.UnsplashRepository
import com.example.unsplashapp.data.remote.repository.UnsplashRepositoryImpl
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

private object UnsplashServiceLocator {
  private const val UNSPLASH_BASE_URL = "https://api.unsplash.com/"
  
  // @MainThread
  private var _application: UnsplashApplication? = null
  
  @MainThread // -> indicate that a particular method or piece of code should be executed on the main thread.
  fun initWith(application: UnsplashApplication) {
    _application = application
  }
  
  // indicate that a function or property should be accessed or executed on the main thread.
  // Using @get:MainThread ensures that the annotated function or property is accessed on the main thread,
  // helping to avoid such issues.
//  @get:MainThread
//  val application: UnsplashApplication
//    get() = checkNotNull(_application) {
//      "UnsplashServiceLocator must be initialized. " + "Call UnsplashServiceLocator.initWith(this) in your Application class."
//    }
  
  fun provideApplication(): Application = checkNotNull(_application)
  
  // setting up an HttpLoggingInterceptor for logging HTTP requests and responses.

//  private val httpLoggingInterceptor: HttpLoggingInterceptor
//    get() = HttpLoggingInterceptor().apply {
//      level = if (BuildConfig.DEBUG) {
//        HttpLoggingInterceptor.Level.BODY
//      } else {
//        HttpLoggingInterceptor.Level.NONE
//      }
//    }
  
  private fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
    HttpLoggingInterceptor().apply {
      level = if (BuildConfig.DEBUG) {
        HttpLoggingInterceptor.Level.BODY
      } else {
        HttpLoggingInterceptor.Level.NONE
      }
    }
  
  // all requests made using that client will include the necessary authorization headers to access the Unsplash API

//  private val authorizationInterceptor: AuthorizationInterceptor
//    get() = AuthorizationInterceptor(
//      clientId = BuildConfig.UNSPLASH_ACCESS_KEY
//    )
  
  private fun provideAuthorizationInterceptor(): AuthorizationInterceptor =
    AuthorizationInterceptor(clientId = BuildConfig.UNSPLASH_ACCESS_KEY)
  
  // OkHttpClient making network requests and handling network interactions (Variety of HTTP Methods,
  // Handling Request Bodies, Setting Headers, Parsing Response Bodies, Handling Response Codes, ....)

//  val okHttpClient: OkHttpClient by lazy {
//    OkHttpClient.Builder().connectTimeout(30, TimeUnit.SECONDS).readTimeout(30, TimeUnit.SECONDS)
//      .writeTimeout(30, TimeUnit.SECONDS)
//      .addNetworkInterceptor(provideHttpLoggingInterceptor()) // -> logging HTTP requests and responses.
//      .addInterceptor(provideAuthorizationInterceptor()) // -> add header is  Client-ID: YOUR ACCESS KEY
//      .build()
//  }
  
  internal fun provideOkHttpClient(): OkHttpClient =
    OkHttpClient.Builder().connectTimeout(30, TimeUnit.SECONDS).readTimeout(30, TimeUnit.SECONDS)
      .writeTimeout(30, TimeUnit.SECONDS)
      .addNetworkInterceptor(provideHttpLoggingInterceptor()) // -> logging HTTP requests and responses.
      .addInterceptor(provideAuthorizationInterceptor()) // -> add header is  Client-ID: YOUR ACCESS KEY
      .build()

//  private val moshi: Moshi by lazy {
//    Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
//  }
  
  private fun provideMoshi(): Moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()

//  private val retrofit: Retrofit by lazy {
//    Retrofit.Builder().baseUrl(UNSPLASH_BASE_URL).client(provideOkHttpClient())
//      .addConverterFactory(MoshiConverterFactory.create(provideMoshi())).build()
//  }
  
  private fun provideRetrofit(): Retrofit =
    Retrofit.Builder().baseUrl(UNSPLASH_BASE_URL).client(provideOkHttpClient())
      .addCallAdapterFactory(RxJava3CallAdapterFactory.create()) // Convert data to be able to use RxJava3 operators
      .addConverterFactory(MoshiConverterFactory.create(provideMoshi())).build()

//  val unsplashApiService: UnsplashApiService by lazy {
//    UnsplashApiService(provideRetrofit()) // -> UnsplashApiService.invoke(retrofit)
//  }
  
//  private fun provideUnsplashService(): UnsplashApiService =
//    UnsplashApiService(provideRetrofit()) // -> UnsplashApiService.invoke(retrofit)
  
  private fun provideUnsplashService(): UnsplashApiService = provideRetrofit().create()
  
  fun provideUnsplashRepository(): UnsplashRepository =
    UnsplashRepositoryImpl(provideUnsplashService())
}