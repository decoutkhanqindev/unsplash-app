package com.example.unsplashapp.presentation.search.photos

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.unsplashapp.core.base.BaseFragment
import com.example.unsplashapp.databinding.FragmentSearchPhotosBinding
import com.example.unsplashapp.presentation.feed.photos.adapter.PhotoItemAdapter

class SearchPhotosFragment : BaseFragment<FragmentSearchPhotosBinding>(
    inflate = FragmentSearchPhotosBinding::inflate
) {
    private val photoItemAdapter: PhotoItemAdapter by lazy(LazyThreadSafetyMode.NONE) {
        PhotoItemAdapter(requestManager = Glide.with(this))
    }

    companion object {
        fun newInstance(): SearchPhotosFragment = SearchPhotosFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpViews()
    }

    private fun setUpViews() {
        binding.searchPhotosRecyclerView.run {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = photoItemAdapter
        }
    }
}