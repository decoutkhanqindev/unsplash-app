package com.example.unsplashapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.fragment.app.add
import com.example.unsplashapp.databinding.ActivityMainBinding
import com.example.unsplashapp.presentation.feed.FeedsFragment

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