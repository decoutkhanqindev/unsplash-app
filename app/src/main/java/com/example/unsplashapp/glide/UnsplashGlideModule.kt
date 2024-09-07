package com.example.unsplashapp.glide

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.module.AppGlideModule
import com.example.unsplashapp.UnsplashServiceLocator
import java.io.InputStream

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
      OkHttpUrlLoader.Factory(UnsplashServiceLocator.okHttpClient)
    )
  }
}