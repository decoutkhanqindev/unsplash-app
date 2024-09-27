package com.example.unsplashapp.presentation.search

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.unsplashapp.data.remote.repository.UnsplashRepository
import com.example.unsplashapp.data.remote.response.PhotoItemResponse
import com.example.unsplashapp.data.remote.response.SearchPhotosResponse
import com.example.unsplashapp.data.remote.response.SearchUsersResponse
import com.example.unsplashapp.data.remote.response.UserItemResponse
import com.example.unsplashapp.presentation.feed.photos.model.PhotoItemModel
import com.example.unsplashapp.presentation.feed.photos.model.PhotoItemModel.Companion.toPhotoItemModel
import com.example.unsplashapp.presentation.search.users.model.UserItemModel
import com.example.unsplashapp.presentation.search.users.model.UserItemModel.Companion.toUserItemModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableSource
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject
import kotlinx.coroutines.rx2.rxObservable
import java.util.concurrent.TimeUnit

class SearchViewModel(private val repository: UnsplashRepository) : ViewModel() {
//  private val _searchQuery: MutableLiveData<String> = MutableLiveData<String>("")
//  private val searchQuery: LiveData<String> get() = _searchQuery
//
//  internal val searchPhotosLiveData: LiveData<List<PhotoItemModel>> =
//    generateSearchItemsLiveData<PhotoItemModel>()
//
//  internal val searchUsersLiveData: LiveData<List<UserItemModel>> =
//    generateSearchItemsLiveData<UserItemModel>()
  
  private val _searchQuerySubject: BehaviorSubject<String> = BehaviorSubject.createDefault("")
  private val searchQuerySubject: BehaviorSubject<String> get() = _searchQuerySubject
  
  private val _searchPhotosSubject: BehaviorSubject<List<PhotoItemModel>> = BehaviorSubject.create()
  val searchPhotosSubject: Observable<List<PhotoItemModel>> get() = _searchPhotosSubject.hide()
  
  private val _searchUsersSubject: BehaviorSubject<List<UserItemModel>> = BehaviorSubject.create()
  val searchUsersSubject: Observable<List<UserItemModel>> get() = _searchUsersSubject.hide()
  
  private val searchPhotosDisposable: Disposable =
    generateSearchItemsObservable<PhotoItemModel>().subscribe(
      /* onNext = */ { _searchPhotosSubject.onNext(it) },
      /* onError = */ { Log.d("SearchViewModel", "searchPhotosDisposable:  $it") }
    )
  private val searchUsersDisposable: Disposable =
    generateSearchItemsObservable<UserItemModel>().subscribe(
      /* onNext = */ { _searchUsersSubject.onNext(it) },
      /* onError = */ { Log.d("SearchViewModel", "searchUsersDisposable:  $it") }
    )
  
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
//
  // reified is check type at runtime.
  @Suppress("UNCHECKED_CAST")
  private inline fun <reified T> generateSearchItemsObservable(): Observable<List<T>> =
    searchQuerySubject.debounce(650L, TimeUnit.MILLISECONDS).distinctUntilChanged()
      .switchMap { query: String ->
        rxObservable<List<T>> { searchItems<T>(query) } as ObservableSource<List<T>>
      }.onErrorReturn { e: Throwable ->
        Log.d("SearchViewModel", "generateSearchItemsObservable: ${e.message}")
        _searchQuerySubject.onError(e)
        emptyList()
      }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
  
  
  @Suppress("UNCHECKED_CAST")
  // reified is check type at at runtime.
  private suspend inline fun <reified T> searchItems(query: String): Single<List<T>> =
    when (T::class) {
      PhotoItemModel::class -> repository.searchPhotosByRxJava(query, 1, 30)
        .map { response: SearchPhotosResponse ->
          response.results.map { result: PhotoItemResponse ->
            result.toPhotoItemModel()
          }
        } as Single<List<T>>
      
      UserItemModel::class -> repository.searchUsersByRxJava(query, 1, 30)
        .map { response: SearchUsersResponse ->
          response.results.map { result: UserItemResponse ->
            result.toUserItemModel()
          }
        } as Single<List<T>>
      
      else -> throw IllegalArgumentException("Unsupported type")
    }.onErrorReturn { e: Throwable ->
      Log.d("SearchViewModel", "searchItems: ${e.message}")
      emptyList()
    }
  
  fun setQuery(query: String) {
    _searchQuerySubject.onNext(query)
  }
  
  override fun onCleared() {
    searchPhotosDisposable.dispose()
    searchUsersDisposable.dispose()
    super.onCleared()
  }
}