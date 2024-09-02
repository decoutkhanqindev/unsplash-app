package com.example.unsplashapp.presentation.feed

import android.os.Bundle
import android.view.View
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.unsplashapp.R
import com.example.unsplashapp.core.base.BaseFragment
import com.example.unsplashapp.databinding.FragmentFeedsBinding
import com.example.unsplashapp.presentation.search.SearchFragment

class FeedsFragment : BaseFragment<FragmentFeedsBinding>(
    inflate = FragmentFeedsBinding::inflate
) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.searchButton.setOnClickListener {
            parentFragmentManager.commit {
                setReorderingAllowed(true)
                addToBackStack(null)
                replace<SearchFragment>(
                    containerViewId = R.id.main, tag = SearchFragment::class.simpleName
                )
            }
        }
    }
}