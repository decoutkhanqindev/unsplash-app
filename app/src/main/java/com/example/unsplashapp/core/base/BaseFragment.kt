package com.example.unsplashapp.core.base

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VB : ViewBinding>(
  private val inflate: (LayoutInflater, ViewGroup?, Boolean) -> VB
) : Fragment() {

  private var _binding: VB? = null
  protected val binding: VB get() = _binding!!

  protected val logTag: String by lazy(LazyThreadSafetyMode.PUBLICATION) { this::class.java.simpleName }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    lifecycle.addObserver(object : LifecycleEventObserver {
      override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        Log.d(logTag, ">>> source=$source, event=$event")
      }
    })
  }

  @CallSuper
  // indicate that when a method annotated with @CallSuper is overridden in a subclass,
  // the overriding method must call the superclass's implementation of that method.
  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
  ): View? {
    _binding = inflate(inflater, container, false)
    return _binding!!.root
  }

  @CallSuper
  override fun onDestroyView() {
    _binding = null
    super.onDestroyView()
  }
}