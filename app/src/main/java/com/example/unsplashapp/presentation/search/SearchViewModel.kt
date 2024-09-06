package com.example.unsplashapp.presentation.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.room.Query
import com.example.unsplashapp.data.remote.UnsplashApiService

class SearchViewModel(private val unsplashApiService: UnsplashApiService) : ViewModel() {
    private var _queryString: MutableLiveData<String> = MutableLiveData<String>("")
    internal val queryString: LiveData<String> get() = _queryString

    fun searchQuery(query: String) {
        _queryString.value = query
    }
}