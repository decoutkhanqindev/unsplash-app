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

@Binds annotation is used in Dagger Hilt modules to tell Hilt how to provide an interface or abstract
class by binding it to a concrete implementation. It's a way to declare dependencies when you have
an interface and a single implementation of that interface.

@Singleton: This annotation indicates that the dependency provided by this function should be a singleton.
A singleton means that only one instance of the dependency will be created and shared throughout the
entire application's lifecycle.
*/

@Module
@InstallIn(SingletonComponent::class)
interface UnsplashRepositoryModule {
  @Binds
  @Singleton
  fun bindUnsplashRepository(repositoryImpl: UnsplashRepositoryImpl) : UnsplashRepository
}