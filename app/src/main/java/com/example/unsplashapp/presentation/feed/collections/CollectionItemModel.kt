package com.example.unsplashapp.presentation.feed.collections

import com.example.unsplashapp.data.remote.response.CollectionItemResponse

// model to display data on UI
data class CollectionItemModel(
    val id: String, val title: String, val description: String, val coverPhoto: String
)