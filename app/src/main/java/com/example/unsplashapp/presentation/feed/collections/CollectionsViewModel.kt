package com.example.unsplashapp.presentation.feed.collections

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.unsplashapp.data.remote.UnsplashApiService
import com.example.unsplashapp.data.remote.response.CollectionItemResponse
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.launch

class CollectionsViewModel(private val unsplashApiService: UnsplashApiService) : ViewModel() {
    private var _uiState: MutableLiveData<CollectionsUiState> =
        MutableLiveData<CollectionsUiState>(CollectionsUiState.FirstPageLoading)
    internal val uiState: LiveData<CollectionsUiState> get() = uiState

    companion object {
        private const val PER_PAGE = 30
    }

    init {
//        viewModelScope.launch {// -> testing to call api
//            repeat(10) {
//                val result: Result<Int> = kotlin.runCatching {
//                    UnsplashServiceLocator.unsplashApiService.getCollections(
//                        page = it + 1, perPage = 30
//                    ).size
//                }
//                println(">>>>> page=${it + 1}, perPage=$result")
//            }
//        }
        loadFirstPage()
    }

    private fun loadFirstPage() {
        viewModelScope.launch {
            _uiState.value = CollectionsUiState.FirstPageLoading

            try {
                // call api
                val responseItems: List<CollectionItemResponse> = // response models
                    unsplashApiService.getCollections(page = 1, perPage = PER_PAGE)
                // map CollectionItemResponse to CollectionItemModel
                val modelItems: List<CollectionItemModel> = // ui models
                    responseItems.map { it.toCollectionItemModel() }

                _uiState.value = CollectionsUiState.Content(
                    items = modelItems,
                    currentPage = 1,
                    nextPageState = if (modelItems.size < PER_PAGE) {
                        CollectionsNextPageState.NO_MORE_ITEMS
                    } else {
                        CollectionsNextPageState.IDLE
                    }
                )
            } catch (cancel: CancellationException) {
                throw cancel
            } catch (e: Exception) {
                _uiState.value = CollectionsUiState.FirstPageError
            }
        }
    }

    internal fun loadNextPage() {
        // check current state -> to accept load more or not
        when (val currentState: CollectionsUiState = _uiState.value!!) {
            CollectionsUiState.FirstPageError, CollectionsUiState.FirstPageLoading -> return

            is CollectionsUiState.Content -> {
                when (currentState.nextPageState) {
                    CollectionsNextPageState.NO_MORE_ITEMS -> return // -> don't do anything

                    CollectionsNextPageState.LOADING -> return // -> has in progress request -> don't do anything -> avoid duplicated request

                    CollectionsNextPageState.ERROR -> loadFirstPage() // if error -> retry

                    CollectionsNextPageState.IDLE -> {
                        loadNextPageInternal(currentState)
                    }
                }
            }
        }
    }

    private fun loadNextPageInternal(currentState: CollectionsUiState.Content) {
        viewModelScope.launch {
            _uiState.value = currentState.copy(nextPageState = CollectionsNextPageState.LOADING)
            val nextPage: Int = currentState.currentPage + 1

            try {
                // call api
                val responseItems: List<CollectionItemResponse> = // response models
                    unsplashApiService.getCollections(
                        page = nextPage, perPage = PER_PAGE
                    )
                // map CollectionItemResponse to CollectionItemModel
                val nextPageModelItems: List<CollectionItemModel> = // ui models
                    responseItems.map { it.toCollectionItemModel() }

                _uiState.value = currentState.copy(
                    items = currentState.items + nextPageModelItems, // old items + new items
                    currentPage = nextPage,
                    nextPageState = if (nextPageModelItems.size < PER_PAGE) {
                        CollectionsNextPageState.NO_MORE_ITEMS
                    } else {
                        CollectionsNextPageState.IDLE
                    }
                )
            } catch (cancel: CancellationException) {
                throw cancel
            } catch (e: Exception) {
                _uiState.value = currentState.copy(nextPageState = CollectionsNextPageState.ERROR)
            }
        }
    }

    // map CollectionItemResponse to CollectionItemModel
    private fun CollectionItemResponse.toCollectionItemModel(): CollectionItemModel =
        CollectionItemModel(
            id = id,
            title = title,
            description = description.orEmpty(),
            coverPhotoUrl = coverPhoto.urls.regular
        )
}