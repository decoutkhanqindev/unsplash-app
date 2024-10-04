package com.example.unsplashapp.di

import com.example.unsplashapp.data.remote.repository.UnsplashRepository
import com.example.unsplashapp.data.remote.repository.UnsplashRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/*
@Module: This annotation tells Hilt that the class it's attached to is a Hilt module.
Modules are responsible for providing dependencies that Hilt cannot create by itself
(e.g., interfaces, classes with multiple constructors, or classes from external libraries).

@InstallIn(): This annotation specifies the component that the module should be installed in.

SingletonComponent::class: Components are containers for dependencies, and the SingletonComponent
is the top-level component in Hilt, meaning dependencies provided by this module will be available
throughout the entire application's lifecycle.
*/

@Module
@InstallIn(SingletonComponent::class)
interface UnsplashRepositoryModule {
  @Binds
  @Singleton
  fun bindUnsplashRepository(repositoryImpl: UnsplashRepositoryImpl) : UnsplashRepository
}