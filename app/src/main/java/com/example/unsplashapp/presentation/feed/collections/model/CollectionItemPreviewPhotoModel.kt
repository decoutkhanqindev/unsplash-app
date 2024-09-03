package com.example.unsplashapp.presentation.feed.collections.model

import com.example.unsplashapp.data.remote.response.UrlsResponse

data class CollectionItemPreviewPhotoModel(
    val id: String, val createdAt: String, val urls: UrlsResponse
)