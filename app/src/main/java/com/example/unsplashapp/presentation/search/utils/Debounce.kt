package com.example.unsplashapp.presentation.search.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

object Debounce {
    fun <T> LiveData<T>.debounce(duration: Long, coroutineScope: CoroutineScope): LiveData<T> {
        val mediatorLiveData: MediatorLiveData<T> = MediatorLiveData<T>()
        var job: Job? = null
        mediatorLiveData.addSource(this) { value: T ->
            job?.cancel()
            job = coroutineScope.launch {
                delay(duration)
                mediatorLiveData.value = value
            }
        }
        return mediatorLiveData
    }
}