package com.heiligbasil.movietvdelight.viewmodel

import android.os.Parcelable
import androidx.databinding.Observable
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.heiligbasil.movietvdelight.model.entities.MovieEssentials
import com.heiligbasil.movietvdelight.model.local.LocalRepository
import com.heiligbasil.movietvdelight.model.remote.RemoteRepository
import kotlinx.coroutines.launch

class SavedViewModel(private val localRepository: LocalRepository) : ViewModel() {
    //var listState: Parcelable? = null

    fun getSavedMovies() = localRepository.saved

    fun updateMovie(movie: MovieEssentials) = viewModelScope.launch {
        localRepository.update(movie)
    }
}