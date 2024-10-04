package com.example.unsplashapp.presentation.feed

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.unsplashapp.presentation.feed.state.FeedsNextPageState
import com.example.unsplashapp.presentation.feed.state.FeedsUiState
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject

class FeedsViewModel<T>(
  private val getItems: (page: Int, perPage: Int) -> Single<List<T>>
) : ViewModel() {
  
  private var _feedsUiSubject: BehaviorSubject<FeedsUiState<T>> = BehaviorSubject.create()
  val feedsUiSubject: Observable<FeedsUiState<T>> get() = _feedsUiSubject.hide()
  
  // CompositeDisposable is a handy utility class in RxJava that helps you
  // manage multiple disposable objects (subscriptions) in a convenient way.
  private val disposables = CompositeDisposable()
  
  init {
    loadFirstPage()
  }
  
  private fun loadFirstPage() {
    _feedsUiSubject.onNext(FeedsUiState.FirstPageLoading)
    val disposable: Disposable =
      getItems(1, 30)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
          /* onSuccess = */
          { modelItems: List<T> ->
            _feedsUiSubject.onNext(
              FeedsUiState.Content(
                items = modelItems,
                currentPage = 1,
                nextPageState = if (modelItems.size < 30) {
                  FeedsNextPageState.NO_MORE_ITEMS
                } else {
                  FeedsNextPageState.IDLE
                }
              )
            )
          },
          /* onError = */
          { throwable: Throwable ->
            _feedsUiSubject.onNext(FeedsUiState.FirstPageError)
            Log.d("FeedsViewModel", "loadNextPageInternal: ${throwable.message}")
          },
        )
    disposables.add(disposable)
  }
  
  fun loadNextPage() {
    when (val currentState: FeedsUiState<T>? = _feedsUiSubject.value) { // check current state
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
      
      else -> throw IllegalArgumentException()
    }
  }
  
  private fun loadNextPageInternal(currentState: FeedsUiState.Content<T>) {
    _feedsUiSubject.onNext(currentState.copy(nextPageState = FeedsNextPageState.LOADING))
    val disposable: Disposable =
      getItems(1, 30)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
          /* onSuccess = */
          { nextModelItems: List<T> ->
            _feedsUiSubject.onNext(
              currentState.copy(
                items = currentState.items + nextModelItems,
                currentPage = currentState.currentPage + 1,
                nextPageState = if (nextModelItems.size < 30) {
                  FeedsNextPageState.NO_MORE_ITEMS
                } else {
                  FeedsNextPageState.IDLE
                }
              )
            )
          },
          /* onError = */
          { throwable: Throwable ->
            _feedsUiSubject.onNext(FeedsUiState.FirstPageError)
            Log.d("FeedsViewModel", "loadNextPageInternal: ${throwable.message}")
          },
        )
    disposables.add(disposable)
  }
  
  override fun onCleared() {
    disposables.clear() // will destroy if viewmodel destroyed
    super.onCleared()
  }
}

//class FeedsViewModel<T>(
//  private val getItems: suspend (page: Int, perPage: Int) -> List<T>
//) : ViewModel() {
//
//  private var _feedsUiSubject: MutableLiveData<FeedsUiState<T>> =
//    MutableLiveData<FeedsUiState<T>>(FeedsUiState.FirstPageLoading)
//  val feedsUiSubject: LiveData<FeedsUiState<T>> get() = _feedsUiSubject
//
//  init {
//    loadFirstPage()
//  }
//
//  private fun loadFirstPage() {
//    viewModelScope.launch {
//      _feedsUiSubject.value = FeedsUiState.FirstPageLoading
//
//      try {
//        val modelItems: List<T> = getItems(1, 30)
//
//        _feedsUiSubject.value = FeedsUiState.Content(
//          items = modelItems,
//          currentPage = 1,
//          nextPageState = if (modelItems.size < 30) {
//            FeedsNextPageState.NO_MORE_ITEMS
//          } else {
//            FeedsNextPageState.IDLE
//          }
//        )
//      } catch (cancel: CancellationException) {
//        throw cancel
//      } catch (exception: Exception) {
//        _feedsUiSubject.value = FeedsUiState.FirstPageError
//        Log.d("FeedsViewModel", "loadFirstPage: $exception")
//      }
//    }
//  }
//
//  fun loadNextPage() {
//    when (val currentState: FeedsUiState<T>? = _feedsUiSubject.value) { // check current state
//      FeedsUiState.FirstPageLoading, FeedsUiState.FirstPageError -> return
//
//      is FeedsUiState.Content -> {
//        when (currentState.nextPageState) {
//          FeedsNextPageState.NO_MORE_ITEMS -> return // -> don't do anything
//          FeedsNextPageState.LOADING -> return // -> has in progress request -> don't do anything -> avoid duplicated request
//          FeedsNextPageState.ERROR -> loadFirstPage() // if error -> retry
//          FeedsNextPageState.IDLE -> { // -> no loading or error
//            loadNextPageInternal(currentState = currentState)
//          }
//        }
//      }
//
//      else -> throw IllegalArgumentException()
//    }
//  }
//
//  private fun loadNextPageInternal(currentState: FeedsUiState.Content<T>) {
//    viewModelScope.launch {
//      _feedsUiSubject.value = currentState.copy(nextPageState = FeedsNextPageState.LOADING)
//      val nextPage: Int = currentState.currentPage + 1
//
//      try {
//        val newModelItems: List<T> = getItems(nextPage, 30)
//
//        _feedsUiSubject.value = currentState.copy(
//          items = currentState.items + newModelItems,
//          currentPage = nextPage,
//          nextPageState = if (newModelItems.size < 30) {
//            FeedsNextPageState.NO_MORE_ITEMS
//          } else {
//            FeedsNextPageState.IDLE
//          }
//        )
//      } catch (cancel: CancellationException) {
//        throw cancel
//      } catch (exception: Exception) {
//        _feedsUiSubject.value = currentState.copy(nextPageState = FeedsNextPageState.ERROR)
//        Log.d("FeedsViewModel", "loadNextPageInternal: ${exception.message}")
//      }
//    }
//  }
//}