package com.heiligbasil.movietvdelight.viewmodel

import androidx.databinding.Observable
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.heiligbasil.movietvdelight.model.local.LocalRepository
import com.heiligbasil.movietvdelight.model.entities.MovieEssentials
import com.heiligbasil.movietvdelight.model.remote.RemoteRepository

class BrowseViewModel(
    private val localRepository: LocalRepository,
    private val remoteRepository: RemoteRepository
) : ViewModel(), Observable {

    val localMovies = localRepository.movies
    val remoteMovies: LiveData<List<MovieEssentials>> = remoteRepository.getMtrMutableLiveData()
    private lateinit var movie: MovieEssentials

    fun init(movie: MovieEssentials) {
        this.movie = movie
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {}

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {}
}