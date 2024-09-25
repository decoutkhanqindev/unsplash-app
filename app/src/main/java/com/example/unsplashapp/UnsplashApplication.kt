package com.example.unsplashapp

import android.app.Application
import com.example.unsplashapp.di.UnsplashServiceLocator

class UnsplashApplication : Application() {
  override fun onCreate() {
    super.onCreate()
    UnsplashServiceLocator.initWith(application = this)
  }
}