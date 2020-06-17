package com.heiligbasil.movietvdelight.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.heiligbasil.movietvdelight.model.local.LocalRepository
import com.heiligbasil.movietvdelight.model.remote.RemoteRepository

class SearchViewModelFactory(
    private val localRepository: LocalRepository,
    private val remoteRepository: RemoteRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchViewModel::class.java)) {
            return SearchViewModel(localRepository, remoteRepository) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}