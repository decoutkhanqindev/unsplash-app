package com.example.unsplashapp.presentation.feed.state

sealed interface FeedsUiState<out T> {
	data object FirstPageLoading : FeedsUiState<Nothing>
	data object FirstPageError : FeedsUiState<Nothing>
	data class Content<T>(
		val items: List<T>, val currentPage: Int, val nextPageState: FeedsNextPageState
	) : FeedsUiState<T>
}