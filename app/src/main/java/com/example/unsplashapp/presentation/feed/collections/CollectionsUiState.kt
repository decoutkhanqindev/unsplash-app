package com.example.unsplashapp.presentation.feed.collections

sealed interface CollectionsUiState {
    data object FirstPageLoading: CollectionsUiState
    data object FirstPageError: CollectionsUiState
    data class Content(
        val items: List<CollectionItemModel>,
        val currentPage: Int,
        val nextPageState: CollectionsNextPageState
    ): CollectionsUiState
}