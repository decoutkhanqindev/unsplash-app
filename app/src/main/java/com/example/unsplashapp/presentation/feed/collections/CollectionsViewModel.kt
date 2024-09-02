package com.example.unsplashapp.presentation.feed.collections

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.unsplashapp.UnsplashServiceLocator
import kotlinx.coroutines.launch

class CollectionsViewModel : ViewModel() {
    init {
        viewModelScope.launch {
            repeat(10) {
                val result: Result<Int> = kotlin.runCatching {
                    UnsplashServiceLocator.unsplashApiService.getCollections(
                        page = it + 1, perPage = 30
                    ).size
                }
                println(">>>>> page=${it + 1}, perPage=$result")
            }
        }
    }
}