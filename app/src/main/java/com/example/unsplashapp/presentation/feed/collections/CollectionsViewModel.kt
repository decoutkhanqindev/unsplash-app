package com.example.unsplashapp.presentation.feed.collections

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.unsplashapp.data.remote.UnsplashApiService
import com.example.unsplashapp.data.remote.response.CollectionItemResponse
import com.example.unsplashapp.presentation.feed.collections.model.CollectionItemModel
import com.example.unsplashapp.presentation.feed.collections.model.CollectionItemModel.Companion.toCollectionItemModel
import com.example.unsplashapp.presentation.feed.state.FeedsNextPageState
import com.example.unsplashapp.presentation.feed.state.FeedsUiState
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.launch

class CollectionsViewModel(private val unsplashApiService: UnsplashApiService) : ViewModel() {
  private var _collectionsUiState: MutableLiveData<FeedsUiState<CollectionItemModel>> =
    MutableLiveData<FeedsUiState<CollectionItemModel>>(FeedsUiState.FirstPageLoading)
  internal val collectionsUiState: LiveData<FeedsUiState<CollectionItemModel>> get() = _collectionsUiState

  companion object {
    private const val PER_PAGE: Int = 30
  }

  init {
    loadFirstPage()
  }

  private fun loadFirstPage() {
    viewModelScope.launch {
      _collectionsUiState.value = FeedsUiState.FirstPageLoading

      try {
        // call api
        val responseItems: List<CollectionItemResponse> = // response models
          unsplashApiService.getCollections(page = 1, perPage = PER_PAGE)
        // convert CollectionItemResponse to CollectionItemModel
        val modelItems: List<CollectionItemModel> = // ui models
          responseItems.map { it.toCollectionItemModel() }

        _collectionsUiState.value = FeedsUiState.Content(
          items = modelItems, currentPage = 1, nextPageState = if (modelItems.size < PER_PAGE) {
            FeedsNextPageState.NO_MORE_ITEMS
          } else {
            FeedsNextPageState.IDLE
          }
        )
      } catch (cancel: CancellationException) {
        throw cancel
      } catch (e: Exception) {
        _collectionsUiState.value = FeedsUiState.FirstPageError
      }
    }
  }

  internal fun loadNextPage() {
    // check current state -> to accept load more or not
    when (val currentState: FeedsUiState<CollectionItemModel> = _collectionsUiState.value!!) {
      FeedsUiState.FirstPageError, FeedsUiState.FirstPageLoading -> return

      is FeedsUiState.Content -> {
        when (currentState.nextPageState) {
          FeedsNextPageState.NO_MORE_ITEMS -> return // -> don't do anything

          FeedsNextPageState.LOADING -> return // -> has in progress request -> don't do anything -> avoid duplicated request

          FeedsNextPageState.ERROR -> loadFirstPage() // if error -> retry

          FeedsNextPageState.IDLE -> { // -> no loading or error
            loadNextPageInternal(currentState = currentState)
          }
        }
      }
    }
  }

  private fun loadNextPageInternal(currentState: FeedsUiState.Content<CollectionItemModel>) {
    viewModelScope.launch {
      _collectionsUiState.value = currentState.copy(nextPageState = FeedsNextPageState.LOADING)
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

        _collectionsUiState.value = currentState.copy(
          items = currentState.items + nextPageModelItems, // old items + new items
          currentPage = nextPage, nextPageState = if (nextPageModelItems.size < PER_PAGE) {
            FeedsNextPageState.NO_MORE_ITEMS
          } else {
            FeedsNextPageState.IDLE
          }
        )
      } catch (cancel: CancellationException) {
        throw cancel
      } catch (e: Exception) {
        _collectionsUiState.value = currentState.copy(nextPageState = FeedsNextPageState.ERROR)
      }
    }
  }
}