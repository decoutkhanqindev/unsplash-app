package com.example.unsplashapp.presentation.feed

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.unsplashapp.presentation.feed.state.FeedsNextPageState
import com.example.unsplashapp.presentation.feed.state.FeedsUiState
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.launch

class FeedsViewModel<T>(
  private val getItems: suspend (page: Int, perPage: Int) -> List<T>
) : ViewModel() {
  private var _feedsUiState: MutableLiveData<FeedsUiState<T>> =
    MutableLiveData<FeedsUiState<T>>(FeedsUiState.FirstPageLoading)
  val feedsUiState: LiveData<FeedsUiState<T>> get() = _feedsUiState
  
  init {
    loadFirstPage()
  }
  
  private fun loadFirstPage() {
    viewModelScope.launch {
      _feedsUiState.value = FeedsUiState.FirstPageLoading
      
      try {
        val modelItems: List<T> = getItems(1, 30)
        
        _feedsUiState.value = FeedsUiState.Content(
          items = modelItems, currentPage = 1, nextPageState = if (modelItems.size < 30) {
            FeedsNextPageState.NO_MORE_ITEMS
          } else {
            FeedsNextPageState.IDLE
          }
        )
      } catch (cancel: CancellationException) {
        throw cancel
      } catch (exception: Exception) {
        _feedsUiState.value = FeedsUiState.FirstPageError
        Log.d("FeedsViewModel", "loadFirstPage: $exception")
      }
    }
  }
  
  fun loadNextPage() {
    when (val currentState: FeedsUiState<T>? = _feedsUiState.value) { // check current state
      FeedsUiState.FirstPageLoading, FeedsUiState.FirstPageError -> return
      
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
      
      else -> {
        throw IllegalArgumentException()
      }
    }
  }
  
  private fun loadNextPageInternal(currentState: FeedsUiState.Content<T>) {
    viewModelScope.launch {
      _feedsUiState.value = currentState.copy(nextPageState = FeedsNextPageState.LOADING)
      val nextPage: Int = currentState.currentPage + 1
      
      try {
        val newModelItems: List<T> = getItems(nextPage, 30)
        
        _feedsUiState.value = currentState.copy(
          items = currentState.items + newModelItems,
          currentPage = nextPage,
          nextPageState = if (newModelItems.size < 30) {
            FeedsNextPageState.NO_MORE_ITEMS
          } else {
            FeedsNextPageState.IDLE
          }
        )
      } catch (cancel: CancellationException) {
        throw cancel
      } catch (exception: Exception) {
        _feedsUiState.value = currentState.copy(nextPageState = FeedsNextPageState.ERROR)
        Log.d("FeedsViewModel", "loadNextPageInternal: ${exception.message}")
      }
    }
  }
}