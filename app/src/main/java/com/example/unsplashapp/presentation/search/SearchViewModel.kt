package com.example.unsplashapp.presentation.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.example.unsplashapp.data.remote.UnsplashApiService
import com.example.unsplashapp.data.remote.response.SearchPhotoItemResponse
import com.example.unsplashapp.presentation.feed.photos.model.PhotoItemModel
import com.example.unsplashapp.presentation.feed.photos.model.PhotoItemModel.Companion.toPhotoItemModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers

class SearchViewModel(private val unsplashApiService: UnsplashApiService) : ViewModel() {
    private val _searchQuery: MutableLiveData<String> = MutableLiveData<String>("")
    private val searchQuery: LiveData<String> get() = _searchQuery

    internal val searchPhotosLiveData: LiveData<List<PhotoItemModel>> =
        searchQuery.switchMap { query: String ->
            if (query.isNotBlank()) {
                liveData(context = viewModelScope.coroutineContext + Dispatchers.IO) {
                    val modelItems: List<PhotoItemModel> = searchPhotos(query)
                    emit(modelItems)
                }
            } else {
                liveData { emit(emptyList()) }
            }
        }

    private suspend fun searchPhotos(query: String): List<PhotoItemModel> {
        return try {
            val responseItem: SearchPhotoItemResponse = unsplashApiService.searchPhotos(
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

    fun setQuery(query: String) {
        _searchQuery.value = query
    }
}