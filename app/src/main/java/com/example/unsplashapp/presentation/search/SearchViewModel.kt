package com.example.unsplashapp.presentation.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.example.unsplashapp.data.remote.UnsplashApiService
import com.example.unsplashapp.data.remote.response.PhotoItemResponse
import com.example.unsplashapp.presentation.feed.photos.model.PhotoItemModel
import com.example.unsplashapp.presentation.feed.photos.model.PhotoItemModel.Companion.toPhotoItemModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers

class SearchViewModel(private val unsplashApiService: UnsplashApiService) : ViewModel() {
    private var _queryString: MutableLiveData<String> = MutableLiveData<String>("")
    private val queryString: LiveData<String> get() = _queryString

    internal val searchPhotoLiveData: LiveData<List<PhotoItemModel>> =
        queryString.switchMap { liveDataValue: String -> // handle to observer a new value and cancel a old value
            // -> ex: 1 2 3 <-> stop at 3 so cancel value 1 2 -> 3 is a new value
            liveData(context = viewModelScope.coroutineContext + Dispatchers.IO) {
                try {
                    val responseItems: List<PhotoItemResponse> = unsplashApiService.searchPhotos(
                        query = liveDataValue, page = 1, perPage = 30
                    )
                    val modelItems: List<PhotoItemModel> =
                        responseItems.map { it.toPhotoItemModel() }
                    emit(modelItems)
                } catch (cancel: CancellationException) {
                    throw cancel
                } catch (e: Exception) {
                    emit(emptyList())
                    Log.d("SearchViewModel", "searchPhotoLiveData: ${e.message}")
                }
            }
        }

    fun searchQuery(query: String) {
        _queryString.value = query
    }
}