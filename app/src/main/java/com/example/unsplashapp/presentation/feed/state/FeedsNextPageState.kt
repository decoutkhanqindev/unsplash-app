package com.example.unsplashapp.presentation.feed.state

enum class FeedsNextPageState {
  LOADING, // -> load more a next page state is loading
  ERROR, // -> load more a next page state is error
  IDLE, // -> load more a next page state is normal -> no loading and no error
  NO_MORE_ITEMS, // -> load more a next page state is done -> it scrolled a last element -> done loading all elements
}