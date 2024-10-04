package com.example.unsplashapp.data.remote.interceptor

import com.example.unsplashapp.di.UnsplashClientIdQualifier
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthorizationInterceptor @Inject constructor(
  @UnsplashClientIdQualifier private val clientId: String
) : Interceptor {
  override fun intercept(chain: Interceptor.Chain): Response =
    chain.request()
      .newBuilder()
      .addHeader(name = "Authorization", value = "Client-ID $clientId")
      .build()
      .let(chain::proceed)
}