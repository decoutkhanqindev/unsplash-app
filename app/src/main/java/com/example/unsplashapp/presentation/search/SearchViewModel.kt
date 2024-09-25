package com.example.unsplashapp.presentation.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.distinctUntilChanged
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.example.unsplashapp.data.remote.UnsplashApiService
import com.example.unsplashapp.presentation.feed.photos.model.PhotoItemModel
import com.example.unsplashapp.presentation.feed.photos.model.PhotoItemModel.Companion.toPhotoItemModel
import com.example.unsplashapp.presentation.search.users.model.UserItemModel
import com.example.unsplashapp.presentation.search.users.model.UserItemModel.Companion.toUserItemModel
import com.example.unsplashapp.presentation.search.utils.Debounce.debounce
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers

class SearchViewModel(private val unsplashApiService: UnsplashApiService) : ViewModel() {
  private val _searchQuery: MutableLiveData<String> = MutableLiveData<String>("")
  private val searchQuery: LiveData<String> get() = _searchQuery
  
  val searchPhotosLiveData: LiveData<List<PhotoItemModel>> =
    generateSearchItemsLiveData<PhotoItemModel>()
  
  val searchUsersLiveData: LiveData<List<UserItemModel>> =
    generateSearchItemsLiveData<UserItemModel>()
  
  // reified is check type at runtime.
  private inline fun <reified T> generateSearchItemsLiveData(): LiveData<List<T>> =
    searchQuery.debounce(650L, viewModelScope).distinctUntilChanged().switchMap { query: String ->
      if (query.isNotBlank()) {
        liveData(context = viewModelScope.coroutineContext + Dispatchers.IO) {
          val modelItems: List<T> = searchItems<T>(query)
          emit(modelItems)
        }
      } else {
        liveData { emit(emptyList()) }
      }
    }
  
  @Suppress("UNCHECKED_CAST")
  // reified is check type at at runtime.
  private suspend inline fun <reified T> searchItems(query: String): List<T> {
    return try {
      val responseItems: List<T> = when (T::class) {
        PhotoItemModel::class -> {
          unsplashApiService.searchPhotos(
            query, 1, 30
          ).results.map { it.toPhotoItemModel() } as List<T>
        }
        
        UserItemModel::class -> {
          unsplashApiService.searchUsers(
            query, 1, 30
          ).results.map { it.toUserItemModel() } as List<T>
        }
        
        else -> {
          throw IllegalArgumentException("Unsupported type")
        }
      }
      responseItems
    } catch (cancel: CancellationException) {
      throw cancel
    } catch (e: Exception) {
      Log.d("SearchViewModel", "searchItems: ${e.message}")
      emptyList()
    }
  }
  
  fun setQuery(query: String) {
    _searchQuery.value = query
  }
}