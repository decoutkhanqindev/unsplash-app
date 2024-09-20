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
import com.example.unsplashapp.data.remote.response.SearchPhotosResponse
import com.example.unsplashapp.data.remote.response.SearchUsersResponse
import com.example.unsplashapp.presentation.feed.photos.model.PhotoItemModel
import com.example.unsplashapp.presentation.feed.photos.model.PhotoItemModel.Companion.toPhotoItemModel
import com.example.unsplashapp.presentation.search.users.model.UserItemModel
import com.example.unsplashapp.presentation.search.users.model.UserItemModel.Companion.toUserItemModel
import com.example.unsplashapp.presentation.search.utils.Debounce.debounce
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers

@Suppress("UNCHECKED_CAST")
class SearchViewModel(private val unsplashApiService: UnsplashApiService) : ViewModel() {
  private val _searchQuery: MutableLiveData<String> = MutableLiveData<String>("")
  private val searchQuery: LiveData<String> get() = _searchQuery
  
  internal val searchPhotosLiveData: LiveData<List<PhotoItemModel>> = searchQuery.debounce(
    650L, viewModelScope
  ) // -> avoid multiple requests, after 650L request starts
    .distinctUntilChanged() // -> if next value changes but equals with previous value -> not request
    .switchMap { query: String ->
      if (query.isNotBlank()) {
        liveData(context = viewModelScope.coroutineContext + Dispatchers.IO) {
          val modelItems: List<PhotoItemModel> = searchItems(query)
          emit(modelItems)
        }
      } else {
        liveData { emit(emptyList()) }
      }
    }
  
  internal val searchUsersLiveData: LiveData<List<UserItemModel>> = searchQuery.debounce(
    650L, viewModelScope
  ) // -> avoid multiple requests, after 650L request starts
    .distinctUntilChanged() // -> if next value changes but equals with previous value -> not request
    .switchMap { query: String ->
      if (query.isNotBlank()) {
        liveData(context = viewModelScope.coroutineContext + Dispatchers.IO) {
          val modelItems: List<UserItemModel> = searchItems(query)
          emit(modelItems)
        }
      } else {
        liveData { emit(emptyList()) }
      }
    }
  
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
  
  private suspend fun searchPhotos(query: String): List<PhotoItemModel> {
    return try {
      val responseItem: SearchPhotosResponse = unsplashApiService.searchPhotos(
        query = query, page = 1, perPage = 30
      )
      responseItem.results.map { it.toPhotoItemModel() }
    } catch (cancel: CancellationException) {
      throw cancel
    } catch (e: Exception) {
      Log.d("SearchViewModel", "searchPhotos: ${e.message}")
      emptyList()
    }
  }
  
  private suspend fun searchUsers(query: String): List<UserItemModel> {
    return try {
      val responseItem: SearchUsersResponse = unsplashApiService.searchUsers(
        query = query, page = 1, perPage = 30
      )
      responseItem.results.map { it.toUserItemModel() }
    } catch (cancel: CancellationException) {
      throw cancel
    } catch (e: Exception) {
      Log.d("SearchViewModel", "searchUsers: ${e.message}")
      emptyList()
    }
  }
  
  fun setQuery(query: String) {
    _searchQuery.value = query
  }
}