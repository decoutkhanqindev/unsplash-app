package com.example.unsplashapp.di

import com.example.unsplashapp.BuildConfig
import com.example.unsplashapp.data.remote.UnsplashApiService
import com.example.unsplashapp.data.remote.interceptor.AuthorizationInterceptor
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/*
@Module: This annotation tells Hilt that the class it's attached to is a Hilt module.
Modules are responsible for providing dependencies that Hilt cannot create by itself
(e.g., interfaces, classes with multiple constructors, or classes from external libraries).

@InstallIn(): This annotation specifies the component that the module should be installed in.

@SingletonComponent::class: Components are containers for dependencies, and the SingletonComponent
is the top-level component in Hilt, meaning dependencies provided by this module will be available
throughout the entire application's lifecycle.

@Provides: This annotation tells Hilt that the function it's attached to is a provider function.
Provider functions are responsible for creating and returning instances of dependencies.

@Singleton: This annotation indicates that the dependency provided by this function should be a singleton.
A singleton means that only one instance of the dependency will be created and shared throughout the
entire application's lifecycle.
*/

@Module
@InstallIn(SingletonComponent::class)
object UnsplashNetworkModule {
  private const val UNSPLASH_BASE_URL = "https://api.unsplash.com/"
  
  @Provides
  @Singleton
  fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
    HttpLoggingInterceptor().apply {
      level = if (BuildConfig.DEBUG) {
        HttpLoggingInterceptor.Level.BODY
      } else {
        HttpLoggingInterceptor.Level.NONE
      }
    }
  
  @Provides
  @Singleton
  fun provideAuthorizationInterceptor(): AuthorizationInterceptor =
    AuthorizationInterceptor(clientId = BuildConfig.UNSPLASH_ACCESS_KEY)
  
  @Provides
  @Singleton
  fun provideOkHttpClient(
    httpLoggingInterceptor: HttpLoggingInterceptor,
    authorizationInterceptor: AuthorizationInterceptor
  ): OkHttpClient =
    OkHttpClient.Builder()
      .connectTimeout(30, TimeUnit.SECONDS)
      .readTimeout(30, TimeUnit.SECONDS)
      .writeTimeout(30, TimeUnit.SECONDS)
      .addNetworkInterceptor(httpLoggingInterceptor)
      .addInterceptor(authorizationInterceptor)
      .build()
  
  @Provides
  @Singleton
  fun provideMoshi(): Moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
  
  @Provides
  @Singleton
  fun provideRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit =
    Retrofit.Builder()
      .baseUrl(UNSPLASH_BASE_URL)
      .client(okHttpClient)
      .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
      .addConverterFactory(MoshiConverterFactory.create(moshi))
      .build()
  
  @Provides
  @Singleton
  fun provideUnsplashService(retrofit: Retrofit): UnsplashApiService = retrofit.create()
}