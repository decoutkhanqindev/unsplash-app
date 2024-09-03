package com.example.unsplashapp.presentation.feed.collections.model

import com.example.unsplashapp.data.remote.response.CollectionItemResponse.PreviewPhoto

// model to display data on UI
data class CollectionItemModel(
    val id: String,
    val title: String,
    val description: String,
    val coverPhotoUrl: String,
    val previewPhotos: List<PreviewPhoto>
)