package com.example.unsplashapp.di

import android.content.Context
import androidx.annotation.MainThread
import com.bumptech.glide.Glide
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.module.AppGlideModule
import com.example.unsplashapp.BuildConfig
import com.example.unsplashapp.UnsplashApplication
import com.example.unsplashapp.data.remote.UnsplashApiService
import com.example.unsplashapp.data.remote.interceptor.AuthorizationInterceptor
import com.example.unsplashapp.data.remote.repository.UnsplashRepository
import com.example.unsplashapp.data.remote.repository.UnsplashRepositoryImpl
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.InputStream
import java.util.concurrent.TimeUnit

const val UNSPLASH_BASE_URL = "https://api.unsplash.com/"

object UnsplashServiceLocator {
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
    AuthorizationInterceptor(
      clientId = BuildConfig.UNSPLASH_ACCESS_KEY
    )
  
  // OkHttpClient making network requests and handling network interactions (Variety of HTTP Methods,
  // Handling Request Bodies, Setting Headers, Parsing Response Bodies, Handling Response Codes, ....)

//  val okHttpClient: OkHttpClient by lazy {
//    OkHttpClient.Builder().connectTimeout(30, TimeUnit.SECONDS).readTimeout(30, TimeUnit.SECONDS)
//      .writeTimeout(30, TimeUnit.SECONDS)
//      .addNetworkInterceptor(provideHttpLoggingInterceptor()) // -> logging HTTP requests and responses.
//      .addInterceptor(provideAuthorizationInterceptor()) // -> add header is  Client-ID: YOUR ACCESS KEY
//      .build()
//  }
  
  private fun provideOkHttpClient(): OkHttpClient =
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
      .addConverterFactory(MoshiConverterFactory.create(provideMoshi())).build()

//  val unsplashApiService: UnsplashApiService by lazy {
//    UnsplashApiService(provideRetrofit()) // -> UnsplashApiService.invoke(retrofit)
//  }
  
  fun provideUnsplashService(): UnsplashApiService =
    UnsplashApiService(provideRetrofit()) // -> UnsplashApiService.invoke(retrofit)
  
  fun provideUnsplashRepository(): UnsplashRepository =
    UnsplashRepositoryImpl(provideUnsplashService())
  
  @GlideModule
  class UnsplashGlideModule : AppGlideModule() {
    // replace Glide's default network library (HttpURLConnection) with OkHttpClient to load images from the internet.
    
    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
      super.registerComponents(context, glide, registry)
      
      // registry.replace: Replace Glide's default loader (HttpURLConnection) with OkHttpUrlLoader,
      // using the OkHttpClient you configured in UnsplashServiceLocator.
      registry.replace(
        GlideUrl::class.java,
        InputStream::class.java,
        OkHttpUrlLoader.Factory(provideOkHttpClient())
      )
    }
  }
}