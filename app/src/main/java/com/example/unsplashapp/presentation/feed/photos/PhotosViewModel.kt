package com.example.unsplashapp.presentation.feed.photos

import android.provider.ContactsContract.Contacts.Photo
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.unsplashapp.data.remote.UnsplashApiService
import com.example.unsplashapp.data.remote.response.PhotoItemResponse
import com.example.unsplashapp.presentation.feed.photos.model.PhotoItemModel
import com.example.unsplashapp.presentation.feed.photos.state.PhotosNextPageState
import com.example.unsplashapp.presentation.feed.photos.state.PhotosUiState
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.launch

class PhotosViewModel(private val unsplashApiService: UnsplashApiService) : ViewModel() {
    private var _uiSate: MutableLiveData<PhotosUiState> =
        MutableLiveData<PhotosUiState>(PhotosUiState.FirstPageLoading)
    private val uiState: LiveData<PhotosUiState> get() = _uiSate

    companion object {
        private const val PER_PAGE: Int = 30
    }

    init {
        loadFirstPage()
    }

    private fun loadFirstPage() {
        viewModelScope.launch {
            _uiSate.value = PhotosUiState.FirstPageLoading

            try {
                val responseItems: List<PhotoItemResponse> =
                    unsplashApiService.getPhotos(page = 1, perPage = PER_PAGE)
                val modelItems: List<PhotoItemModel> = responseItems.map { it.toPhotoItemModel() }

                _uiSate.value = PhotosUiState.Content(
                    items = modelItems,
                    currentPage = 1,
                    nextPageState = if (modelItems.size < PER_PAGE) {
                        PhotosNextPageState.NO_MORE_ITEMS
                    } else {
                        PhotosNextPageState.IDLE
                    }
                )
            } catch (cancel: CancellationException) {
                throw cancel
            } catch (e: Exception) {
                _uiSate.value = PhotosUiState.FirstPageError
                Log.d("PhotosViewModel", "loadFirstPage: ${e.message}")
            }
        }
    }

    internal fun loadNextPage() {
        when (val currentSate: PhotosUiState = _uiSate.value!!) {
            PhotosUiState.FirstPageLoading, PhotosUiState.FirstPageError -> return
            is PhotosUiState.Content -> {
                when (currentSate.nextPageState) {
                    PhotosNextPageState.NO_MORE_ITEMS, PhotosNextPageState.LOADING -> return
                    PhotosNextPageState.ERROR -> loadFirstPage()
                    PhotosNextPageState.IDLE -> {
                        loadNextPageInternal(currentState = currentSate)
                    }
                }
            }
        }
    }

    private fun loadNextPageInternal(currentState: PhotosUiState.Content) {
        viewModelScope.launch {
            _uiSate.value = currentState.copy(nextPageState = PhotosNextPageState.LOADING)
            val nextPage: Int = currentState.currentPage + 1

            try {
                val responseItems: List<PhotoItemResponse> =
                    unsplashApiService.getPhotos(page = nextPage, perPage = PER_PAGE)
                val nextPageModelItems: List<PhotoItemModel> =
                    responseItems.map { it.toPhotoItemModel() }

                _uiSate.value = currentState.copy(
                    items = currentState.items + nextPageModelItems,
                    currentPage = nextPage,
                    nextPageState = if (nextPageModelItems.size < PER_PAGE) {
                        PhotosNextPageState.NO_MORE_ITEMS
                    } else {
                        PhotosNextPageState.IDLE
                    }
                )
            } catch (cancel: CancellationException) {
                throw cancel
            } catch (e: Exception) {
                _uiSate.value = currentState.copy(nextPageState = PhotosNextPageState.ERROR)
                Log.d("PhotosViewModel", "loadNextPageInternal: ${e.message}")
            }
        }
    }

    private fun PhotoItemResponse.toPhotoItemModel(): PhotoItemModel = PhotoItemModel(
        id = id, description = description, urls = urls
    )
}