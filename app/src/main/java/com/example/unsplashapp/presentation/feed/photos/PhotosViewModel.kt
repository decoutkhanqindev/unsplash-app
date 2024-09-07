package com.example.unsplashapp.presentation.feed.photos

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.unsplashapp.data.remote.UnsplashApiService
import com.example.unsplashapp.data.remote.response.PhotoItemResponse
import com.example.unsplashapp.presentation.feed.photos.model.PhotoItemModel
import com.example.unsplashapp.presentation.feed.photos.model.PhotoItemModel.Companion.toPhotoItemModel
import com.example.unsplashapp.presentation.feed.state.FeedsNextPageState
import com.example.unsplashapp.presentation.feed.state.FeedsUiState
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.launch

class PhotosViewModel(private val unsplashApiService: UnsplashApiService) : ViewModel() {
    private var _photosUiState: MutableLiveData<FeedsUiState<PhotoItemModel>> =
        MutableLiveData<FeedsUiState<PhotoItemModel>>(FeedsUiState.FirstPageLoading)
    internal val photosUiState: LiveData<FeedsUiState<PhotoItemModel>> get() = _photosUiState

    companion object {
        private const val PER_PAGE: Int = 30
    }

    init {
        loadFirstPage()
    }

    private fun loadFirstPage() {
        viewModelScope.launch {
            _photosUiState.value = FeedsUiState.FirstPageLoading

            try {
                val responseItems: List<PhotoItemResponse> =
                    unsplashApiService.getPhotos(page = 1, perPage = PER_PAGE)
                val modelItems: List<PhotoItemModel> = responseItems.map { it.toPhotoItemModel() }

                _photosUiState.value = FeedsUiState.Content(
                    items = modelItems,
                    currentPage = 1,
                    nextPageState = if (modelItems.size < PER_PAGE) {
                        FeedsNextPageState.NO_MORE_ITEMS
                    } else {
                        FeedsNextPageState.IDLE
                    }
                )
            } catch (cancel: CancellationException) {
                throw cancel
            } catch (e: Exception) {
                _photosUiState.value = FeedsUiState.FirstPageError
                Log.d("PhotosViewModel", "loadFirstPage: $e")
            }
        }
    }

    internal fun loadNextPage() {
        when (val currentSate: FeedsUiState<PhotoItemModel> = _photosUiState.value!!) {
            FeedsUiState.FirstPageLoading, FeedsUiState.FirstPageError -> return

            is FeedsUiState.Content -> {
                when (currentSate.nextPageState) {
                    FeedsNextPageState.NO_MORE_ITEMS, FeedsNextPageState.LOADING -> return

                    FeedsNextPageState.ERROR -> loadFirstPage()

                    FeedsNextPageState.IDLE -> {
                        loadNextPageInternal(currentState = currentSate)
                    }
                }
            }
        }
    }

    private fun loadNextPageInternal(currentState: FeedsUiState.Content<PhotoItemModel>) {
        viewModelScope.launch {
            _photosUiState.value = currentState.copy(nextPageState = FeedsNextPageState.LOADING)
            val nextPage: Int = currentState.currentPage + 1

            try {
                val responseItems: List<PhotoItemResponse> =
                    unsplashApiService.getPhotos(page = nextPage, perPage = PER_PAGE)
                val nextPageModelItems: List<PhotoItemModel> =
                    responseItems.map { it.toPhotoItemModel() }

                _photosUiState.value = currentState.copy(
                    items = currentState.items + nextPageModelItems,
                    currentPage = nextPage,
                    nextPageState = if (nextPageModelItems.size < PER_PAGE) {
                        FeedsNextPageState.NO_MORE_ITEMS
                    } else {
                        FeedsNextPageState.IDLE
                    }
                )
            } catch (cancel: CancellationException) {
                throw cancel
            } catch (e: Exception) {
                _photosUiState.value = currentState.copy(nextPageState = FeedsNextPageState.ERROR)
                Log.d("PhotosViewModel", "loadNextPageInternal: ${e.message}")
            }
        }
    }
}