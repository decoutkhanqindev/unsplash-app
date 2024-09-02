package com.example.unsplashapp

import android.app.Application

class UnsplashApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        UnsplashServiceLocator.initWith(application = this)
    }
}