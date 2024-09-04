package com.example.unsplashapp.presentation.feed.photos.state

import com.example.unsplashapp.presentation.feed.photos.model.PhotoItemModel

sealed interface PhotosUiState {
    data object FirstPageLoading : PhotosUiState
    data object FirstPageError : PhotosUiState
    data class Content(
        val items: List<PhotoItemModel>,
        val currentPage: Int,
        val nextPageState: PhotosNextPageState
    ) : PhotosUiState
}