package com.example.unsplashapp.presentation.feed.collections

import com.example.unsplashapp.data.remote.response.CollectionItemResponse

sealed interface CollectionsUiState {
    data object FirstPageLoading: CollectionsUiState
    data object FirstPageError: CollectionsUiState
    data class Content(val items: List<CollectionItemModel>): CollectionsUiState
}