## APIs

- https://unsplash.com/documentation#public-authentication
- https://unsplash.com/documentation#list-collections
- https://unsplash.com/documentation#search-photos

## Screenshots

- ![Feeds](https://github.com/user-attachments/assets/38a0f44d-5e92-4883-ab1c-251fbf34d695)
- ![Search](https://github.com/user-attachments/assets/626674de-e543-4541-b711-1b056ffccedf)

## TODO

- [x] Create `CollectionItemResponse`.
- [x] Add API endpoint to `UnsplashApiService`.
- [x] Create `AuthorizationInterceptor` and update `UnsplashServiceLocator`.
- [x] Create `CollectionsViewModel`, test API endpoint.
- [x] Setup views
  - [x] Setup views in `FeedsFragment`.
  - [x] Setup views in `FeedCollectionsFragment`.
- [x] Add `CollectionsUiState`, add logics in `CollectionsViewModel`.
- [x] Connect `FeedCollectionsFragment` with `CollectionsViewModel`.
  - [x] Observe ui state and call ViewModel's methods.
  - [x] Add `CollectionItemAdapter`.
  - [x] Config `Glide` to load image.
