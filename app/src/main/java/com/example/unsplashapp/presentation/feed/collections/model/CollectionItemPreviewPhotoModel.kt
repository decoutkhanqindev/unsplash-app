package com.example.unsplashapp.presentation.feed.collections.model

import com.example.unsplashapp.data.remote.response.CollectionItemResponse

// model to display data on UI for collection item preview photo
data class CollectionItemPreviewPhotoModel(
  val id: String, val createdAt: String, val urls: CollectionItemResponse.PreviewPhoto.Urls
) {
  companion object {
    // convert PreviewPhoto to CollectionItemPreviewPhotoModel
    fun CollectionItemResponse.PreviewPhoto.toCollectionItemPreviewPhotoModel(): CollectionItemPreviewPhotoModel =
      CollectionItemPreviewPhotoModel(id = id, createdAt = createdAt, urls = urls)
  }
}