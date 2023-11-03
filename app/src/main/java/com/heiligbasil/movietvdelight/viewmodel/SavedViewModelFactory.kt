package com.heiligbasil.movietvdelight.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.heiligbasil.movietvdelight.model.local.LocalRepository
import com.heiligbasil.movietvdelight.model.remote.RemoteRepository

class SavedViewModelFactory(private val localRepository: LocalRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SavedViewModel::class.java)) {
            return SavedViewModel(localRepository) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}