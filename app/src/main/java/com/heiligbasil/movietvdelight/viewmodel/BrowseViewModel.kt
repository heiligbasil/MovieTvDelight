package com.heiligbasil.movietvdelight.viewmodel

import androidx.databinding.Observable
import androidx.lifecycle.ViewModel
import com.heiligbasil.movietvdelight.model.MovieRepository
import com.heiligbasil.movietvdelight.model.entities.MovieEssentials

class BrowseViewModel(private val repository: MovieRepository) : ViewModel(), Observable {
    val movies = repository.movies
    private lateinit var movie: MovieEssentials

    fun init(movie: MovieEssentials) {
        this.movie = movie
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {}

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {}
}