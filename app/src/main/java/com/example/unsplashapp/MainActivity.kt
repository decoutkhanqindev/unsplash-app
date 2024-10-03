package com.example.unsplashapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.example.unsplashapp.databinding.ActivityMainBinding
import com.example.unsplashapp.presentation.feed.FeedsFragment
import dagger.hilt.android.AndroidEntryPoint

/*
@AndroidEntryPoint is a Hilt annotation that you can use on Android classes
that need to receive dependencies from Hilt.
Here's how it works:
Tells Hilt to provide dependencies: When you annotate an Android class with @AndroidEntryPoint,
you're telling Hilt that this class needs to participate in dependency injection. Hilt will generate
a dependency container (a component) for that class and provide dependencies to it.
Supported classes: You can use @AndroidEntryPoint on a variety of Android classes, including:
- Activity
- Fragment
- View
- Service
- BroadcastReceiver
*/

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
  private val binding: ActivityMainBinding by lazy(LazyThreadSafetyMode.NONE) {
    ActivityMainBinding.inflate(layoutInflater)
  }
  
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(binding.root)
    
    if (savedInstanceState == null) {
      supportFragmentManager.commit {
        setReorderingAllowed(true)
        addToBackStack(null)
        add<FeedsFragment>(
          containerViewId = binding.main.id, tag = FeedsFragment::class.simpleName
        )
      }
    }
  }
}
