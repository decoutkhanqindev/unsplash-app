package com.example.unsplashapp.presentation.feed.photos.state

import com.example.unsplashapp.data.remote.response.PhotoItemResponse

sealed interface PhotosUiState {
    data object FirstPageLoading : PhotosUiState
    data object FirstPageError : PhotosUiState
    data class Content(
        val items: List<PhotoItemResponse>,
        val currentPage: Int,
        val nextPageState: PhotosNextPageState
    ) : PhotosUiState
}