package com.example.unsplashapp.presentation.feed.photos.model

import com.example.unsplashapp.data.remote.response.PhotoItemResponse

data class PhotoItemModel(
    val id: String, val description: String?, val urls: PhotoItemResponse.Urls
)