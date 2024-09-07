package com.example.unsplashapp.presentation.search.users

import com.example.unsplashapp.core.base.BaseFragment
import com.example.unsplashapp.databinding.FragmentSearchUsersBinding

class SearchUsersFragment : BaseFragment<FragmentSearchUsersBinding>(
	inflate = FragmentSearchUsersBinding::inflate
) {
	companion object {
		fun newInstance(): SearchUsersFragment = SearchUsersFragment()
	}
}