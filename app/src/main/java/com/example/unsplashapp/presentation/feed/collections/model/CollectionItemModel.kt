package com.example.unsplashapp.presentation.feed.collections.model

import com.example.unsplashapp.data.remote.response.CollectionItemResponse
import java.io.Serializable

// model to display data on UI for collection
data class CollectionItemModel(
  val id: String,
  val title: String,
  val description: String,
  val coverPhotoUrl: String,
  val user: CollectionItemResponse.User,
  val previewPhotos: List<CollectionItemResponse.PreviewPhoto>
) : Serializable {
  companion object {
    fun CollectionItemResponse.toCollectionItemModel(): CollectionItemModel = CollectionItemModel(
      id = id,
      title = title,
      description = description ?: "No description...",
      coverPhotoUrl = coverPhoto.urls.regular,
      user = user,
      previewPhotos = previewPhotos
    )
  }
}