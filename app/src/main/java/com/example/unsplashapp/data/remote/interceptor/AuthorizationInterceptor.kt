package com.example.unsplashapp.data.remote.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class AuthorizationInterceptor(private val clientId: String) : Interceptor {
  override fun intercept(chain: Interceptor.Chain): Response =
    chain.request().newBuilder().addHeader(
      // name = "Authorization", value = "Client-ID ${BuildConfig.UNSPLASH_ACCESS_KEY}"
      name = "Authorization", value = "Client-ID $clientId"
    ).build().let(chain::proceed)
}