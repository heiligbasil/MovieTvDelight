package com.heiligbasil.movietvdelight.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.heiligbasil.movietvdelight.model.MovieRepository
import java.lang.IllegalArgumentException

class BrowseViewModelFactory(private val repository: MovieRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(BrowseViewModel::class.java)) {
            return BrowseViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}