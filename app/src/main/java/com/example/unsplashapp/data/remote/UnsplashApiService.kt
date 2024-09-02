package com.example.unsplashapp.data.remote

import retrofit2.Retrofit
import retrofit2.create

interface UnsplashApiService {
    companion object {
        operator fun invoke(retrofit: Retrofit): UnsplashApiService = retrofit.create()
    }
}