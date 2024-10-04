package com.example.unsplashapp.presentation.search

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.unsplashapp.data.remote.repository.UnsplashRepository
import com.example.unsplashapp.presentation.feed.photos.model.PhotoItemModel
import com.example.unsplashapp.presentation.search.users.model.UserItemModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val repository: UnsplashRepository) :
  ViewModel() {

//  private val _searchQuery: MutableLiveData<String> = MutableLiveData<String>("")
//  private val searchQuery: LiveData<String> get() = _searchQuery
//
//  internal val searchPhotosLiveData: LiveData<List<PhotoItemModel>> =
//    generateSearchItemsLiveData<PhotoItemModel>()
//
//  internal val searchUsersLiveData: LiveData<List<UserItemModel>> =
//    generateSearchItemsLiveData<UserItemModel>()
  
  //  // reified is check type at runtime.
//  private inline fun <reified T> generateSearchItemsLiveData(): LiveData<List<T>> =
//    searchQuery.debounce(650L, viewModelScope).distinctUntilChanged().switchMap { query: String ->
//      if (query.isNotBlank()) {
//        liveData(context = viewModelScope.coroutineContext + Dispatchers.IO) {
//          val modelItems: List<T> = searchItems<T>(query)
//          emit(modelItems)
//        }
//      } else {
//        liveData { emit(emptyList()) }
//      }
//    }
  
  private val _searchQuerySubject: BehaviorSubject<String> = BehaviorSubject.createDefault("")
  private val searchQuerySubject: BehaviorSubject<String> get() = _searchQuerySubject
  
  private val _searchPhotosSubject: BehaviorSubject<List<PhotoItemModel>> = BehaviorSubject.create()
  val searchPhotosSubject: Observable<List<PhotoItemModel>> get() = _searchPhotosSubject.hide()
  
  private val _searchUsersSubject: BehaviorSubject<List<UserItemModel>> = BehaviorSubject.create()
  val searchUsersSubject: Observable<List<UserItemModel>> get() = _searchUsersSubject.hide()
  
  private val disposables = CompositeDisposable()
  
  private val searchPhotosDisposable: Disposable =
    generateSearchItemsObservable<PhotoItemModel>().observeOn(AndroidSchedulers.mainThread())
      .subscribe(
        /* onNext = */ { item: List<PhotoItemModel> -> _searchPhotosSubject.onNext(item) },
        /* onError = */ { throwable: Throwable -> Log.d("SearchViewModel", "searchPhotosDisposable:  ${throwable.message}") }
      )
  
  private val searchUsersDisposable: Disposable =
    generateSearchItemsObservable<UserItemModel>().observeOn(AndroidSchedulers.mainThread())
      .subscribe(
        /* onNext = */ { item: List<UserItemModel> -> _searchUsersSubject.onNext(item) },
        /* onError = */ { throwable: Throwable -> Log.d("SearchViewModel", "searchUsersDisposable:  ${throwable.message}") }
      )
  
  // reified is check type at runtime.
  private inline fun <reified T> generateSearchItemsObservable(): Observable<List<T>> =
    searchQuerySubject.debounce(650L, TimeUnit.MILLISECONDS)
      .distinctUntilChanged()
      .switchMap { query: String -> searchItems<T>(query).toObservable() }
      .onErrorReturn { e: Throwable ->
        Log.d("SearchViewModel", "generateSearchItemsObservable: ${e.message}")
        emptyList()
      }.subscribeOn(Schedulers.io())
  
  @Suppress("UNCHECKED_CAST")
  // reified is check type at at runtime.
  private inline fun <reified T> searchItems(query: String): Single<List<T>> = when (T::class) {
    
    PhotoItemModel::class -> repository.searchPhotos(query, 1, 30) as Single<List<T>>
    
    UserItemModel::class -> repository.searchUsers(query, 1, 30) as Single<List<T>>
    
    else -> throw IllegalArgumentException("Unsupported type")
  }.onErrorReturn { e: Throwable ->
    Log.d("SearchViewModel", "searchItems: ${e.message}")
    emptyList()
  }
  
  fun setQuery(query: String) {
    _searchQuerySubject.onNext(query)
    disposables.addAll(searchPhotosDisposable, searchUsersDisposable)
  }
  
  override fun onCleared() {
    disposables.clear()
    super.onCleared()
  }
}